package br.com.carde.api.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    // Buckets em memória por (IP + endpoint-key)
    // Suficiente para instância única (free tier Koyeb)
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String ip = resolveClientIp(request);
        String bucketKey = resolveBucketKey(request.getRequestURI());
        int capacity = resolveCapacity(request.getRequestURI());

        Bucket bucket = buckets.computeIfAbsent(
                ip + ":" + bucketKey,
                k -> buildBucket(capacity)
        );

        if (bucket.tryConsume(1)) {
            chain.doFilter(request, response);
        } else {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.setHeader("Retry-After", "60");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(
                    "{\"status\":429,\"title\":\"Too Many Requests\"," +
                    "\"detail\":\"Limite de requisições atingido. Tente novamente em 60 segundos.\"}"
            );
        }
    }

    private String resolveBucketKey(String uri) {
        if (uri.matches("/api/v1/vehicles/[^/]+")) return "vehicle-detail";
        if (uri.startsWith("/api/v1/vehicles"))     return "vehicles";
        if (uri.startsWith("/api/v1/events"))       return "events";
        return "default";
    }

    private int resolveCapacity(String uri) {
        if (uri.matches("/api/v1/vehicles/[^/]+")) return 200; // QR scan path
        if (uri.startsWith("/api/v1/vehicles"))    return 100;
        if (uri.startsWith("/api/v1/events"))      return 60;
        return 30;
    }

    private Bucket buildBucket(int capacity) {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(capacity, Refill.intervally(capacity, Duration.ofMinutes(1))))
                .build();
    }

    private String resolveClientIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.isBlank()) {
            return forwarded.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}
