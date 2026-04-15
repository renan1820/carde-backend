package br.com.carde.api.application.vehicle;

import br.com.carde.api.domain.model.VehicleQrCode;
import br.com.carde.api.domain.repository.VehicleQrCodeRepository;
import br.com.carde.api.domain.repository.VehicleRepository;
import br.com.carde.api.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetVehicleQrCodeUseCase {

    private final VehicleRepository vehicleRepository;
    private final VehicleQrCodeRepository qrCodeRepository;

    public Optional<VehicleQrCode> execute(String vehicleId) {
        if (!vehicleRepository.existsById(vehicleId)) {
            throw new ResourceNotFoundException("Vehicle", vehicleId);
        }
        return qrCodeRepository.findByVehicleId(vehicleId);
    }
}
