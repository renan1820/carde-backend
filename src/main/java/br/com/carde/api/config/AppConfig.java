package br.com.carde.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "carde")
public record AppConfig(Cdn cdn) {

    public record Cdn(String baseUrl) {
        /**
         * Se o path já for uma URL absoluta (ex: Unsplash, SoundHelix),
         * retorna como está. Caso contrário, prefixa com o CDN base URL.
         */
        public String resolve(String path) {
            if (path == null || path.isBlank()) return null;
            if (path.startsWith("http://") || path.startsWith("https://")) return path;
            return baseUrl + "/" + path;
        }
    }
}
