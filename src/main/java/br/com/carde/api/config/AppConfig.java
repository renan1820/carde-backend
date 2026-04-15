package br.com.carde.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "carde")
public record AppConfig(Cdn cdn, App app, Qr qr) {

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

    public record App(String baseUrl) {
        /** URL pública do veículo codificada no QR Code. */
        public String vehicleUrl(String vehicleId) {
            return baseUrl + "/vehicles/" + vehicleId;
        }
    }

    public record Qr(String apiUrl, String size, String format) {
        /** Monta a URL GET do api.qrserver.com que serve a imagem PNG diretamente. */
        public String buildImageUrl(String encodedValue) {
            return apiUrl + "?data=" + encodedValue
                    + "&size=" + size
                    + "&format=" + format;
        }
    }
}
