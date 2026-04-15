package br.com.carde.api.presentation.controller;

import br.com.carde.api.application.vehicle.*;
import br.com.carde.api.domain.model.Vehicle;
import br.com.carde.api.domain.model.VehicleQrCode;
import br.com.carde.api.presentation.dto.request.VehicleRequest;
import br.com.carde.api.presentation.dto.response.VehicleQrCodeResponse;
import br.com.carde.api.presentation.dto.response.VehicleResponse;
import br.com.carde.api.presentation.mapper.VehicleResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/vehicles")
@RequiredArgsConstructor
@Tag(name = "Admin — Vehicles", description = "CRUD de veículos (requer JWT)")
@SecurityRequirement(name = "bearerAuth")
public class AdminVehicleController {

    private final GetAllVehiclesUseCase getAllVehicles;
    private final CreateVehicleUseCase createVehicle;
    private final UpdateVehicleUseCase updateVehicle;
    private final DeleteVehicleUseCase deleteVehicle;
    private final GenerateVehicleQrCodeUseCase generateQrCode;
    private final GetVehicleQrCodeUseCase getVehicleQrCode;
    private final VehicleResponseMapper mapper;

    @GetMapping
    @Operation(summary = "Lista todos os veículos")
    public ResponseEntity<List<VehicleResponse>> listAll() {
        List<VehicleResponse> result = getAllVehicles
                .execute(null, PageRequest.of(0, 200))
                .map(mapper::toDetail)
                .getContent();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Operation(summary = "Cria veículo")
    public ResponseEntity<VehicleResponse> create(@Valid @RequestBody VehicleRequest request) {
        Vehicle created = createVehicle.execute(request);
        VehicleResponse response = mapper.toDetail(created);
        return ResponseEntity.created(URI.create("/api/v1/vehicles/" + created.id()))
                .body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza veículo")
    public ResponseEntity<VehicleResponse> update(
            @PathVariable String id,
            @Valid @RequestBody VehicleRequest request) {
        Vehicle updated = updateVehicle.execute(id, request);
        return ResponseEntity.ok(mapper.toDetail(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove veículo")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        deleteVehicle.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/qr-code")
    @Operation(summary = "Gera QR Code para o veículo (idempotente — retorna existente se já gerado)")
    public ResponseEntity<VehicleQrCodeResponse> generateQrCode(@PathVariable String id) {
        VehicleQrCode qr = generateQrCode.execute(id);
        return ResponseEntity.ok(toQrResponse(qr));
    }

    @GetMapping("/{id}/qr-code")
    @Operation(summary = "Retorna QR Code do veículo (404 se ainda não gerado)")
    public ResponseEntity<VehicleQrCodeResponse> getQrCode(@PathVariable String id) {
        return getVehicleQrCode.execute(id)
                .map(qr -> ResponseEntity.ok(toQrResponse(qr)))
                .orElse(ResponseEntity.notFound().build());
    }

    private VehicleQrCodeResponse toQrResponse(VehicleQrCode qr) {
        return new VehicleQrCodeResponse(qr.id(), qr.vehicleId(), qr.qrValue(), qr.imageUrl());
    }
}
