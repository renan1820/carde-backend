package br.com.carde.api.application.vehicle;

import br.com.carde.api.config.AppConfig;
import br.com.carde.api.domain.model.VehicleQrCode;
import br.com.carde.api.domain.repository.VehicleQrCodeRepository;
import br.com.carde.api.domain.repository.VehicleRepository;
import br.com.carde.api.exception.ResourceNotFoundException;
import br.com.carde.api.infrastructure.qr.QrCodeApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenerateVehicleQrCodeUseCase {

    private final VehicleRepository vehicleRepository;
    private final VehicleQrCodeRepository qrCodeRepository;
    private final QrCodeApiClient qrApiClient;
    private final AppConfig appConfig;

    @Transactional
    public VehicleQrCode execute(String vehicleId) {
        if (!vehicleRepository.existsById(vehicleId)) {
            throw new ResourceNotFoundException("Vehicle", vehicleId);
        }

        // Idempotente: retorna QR existente sem criar duplicata
        return qrCodeRepository.findByVehicleId(vehicleId)
                .orElseGet(() -> generateNew(vehicleId));
    }

    private VehicleQrCode generateNew(String vehicleId) {
        String qrValue = appConfig.app().vehicleUrl(vehicleId);

        // Segunda linha de defesa além do UNIQUE do banco
        if (qrCodeRepository.existsByQrValue(qrValue)) {
            throw new IllegalStateException(
                    "QR value já existe para outro veículo: " + qrValue);
        }

        String imageUrl = qrApiClient.buildImageUrl(qrValue);
        VehicleQrCode qrCode = new VehicleQrCode(
                UUID.randomUUID().toString(),
                vehicleId,
                qrValue,
                imageUrl,
                null
        );
        return qrCodeRepository.save(qrCode);
    }
}
