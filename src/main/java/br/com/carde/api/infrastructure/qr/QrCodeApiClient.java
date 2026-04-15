package br.com.carde.api.infrastructure.qr;

import br.com.carde.api.config.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Constrói a URL de imagem do QR Code usando o serviço gratuito api.qrserver.com.
 * A URL GET já serve a imagem PNG diretamente — não é necessário fazer download
 * no backend. Apenas a URL composta é armazenada no banco.
 */
@Component
@RequiredArgsConstructor
public class QrCodeApiClient {

    private final AppConfig appConfig;

    public String buildImageUrl(String qrValue) {
        String encoded = URLEncoder.encode(qrValue, StandardCharsets.UTF_8);
        return appConfig.qr().buildImageUrl(encoded);
    }
}
