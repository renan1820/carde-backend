package br.com.carde.api.presentation.controller;

import br.com.carde.api.application.vehicle.GetAllVehiclesUseCase;
import br.com.carde.api.application.vehicle.GetVehicleByIdUseCase;
import br.com.carde.api.domain.model.Vehicle;
import br.com.carde.api.domain.model.VehicleCategory;
import br.com.carde.api.presentation.dto.response.PagedResponse;
import br.com.carde.api.presentation.dto.response.VehicleListItemResponse;
import br.com.carde.api.presentation.dto.response.VehicleResponse;
import br.com.carde.api.presentation.mapper.VehicleResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
@Validated
@Tag(name = "Vehicles", description = "Acervo de veículos do Museu CARDE")
public class VehicleController {

    private final GetAllVehiclesUseCase getAllVehicles;
    private final GetVehicleByIdUseCase getVehicleById;
    private final VehicleResponseMapper mapper;

    @GetMapping
    @Operation(summary = "Lista veículos", description = "Retorna lista paginada. Filtra por categoria via ?category=classic")
    public ResponseEntity<PagedResponse<VehicleListItemResponse>> listVehicles(
            @RequestParam(required = false) VehicleCategory category,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "20") @Min(1) @Max(50) int size
    ) {
        Page<Vehicle> vehicles = getAllVehicles.execute(category, PageRequest.of(page, size));
        Page<VehicleListItemResponse> dtos = vehicles.map(mapper::toListItem);
        return ResponseEntity.ok(PagedResponse.of(dtos));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Detalhe do veículo", description = "Retorna dados completos incluindo histórico e fichas técnicas")
    public ResponseEntity<VehicleResponse> getVehicle(
            @PathVariable @Pattern(regexp = "^[a-zA-Z0-9_-]{1,36}$",
                                   message = "ID inválido") String id
    ) {
        Vehicle vehicle = getVehicleById.execute(id);
        return ResponseEntity.ok(mapper.toDetail(vehicle));
    }
}
