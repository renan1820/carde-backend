package br.com.carde.api.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "vehicle_qr_codes")
@Getter
@Setter
public class VehicleQrCodeEntity {

    @Id
    private String id;

    @Column(name = "vehicle_id", nullable = false, unique = true, length = 36)
    private String vehicleId;

    @Column(name = "qr_value", nullable = false, unique = true, length = 2000)
    private String qrValue;

    @Column(name = "image_url", nullable = false, length = 2000)
    private String imageUrl;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;
}
