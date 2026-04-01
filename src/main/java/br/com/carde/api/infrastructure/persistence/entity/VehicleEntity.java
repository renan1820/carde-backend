package br.com.carde.api.infrastructure.persistence.entity;

import br.com.carde.api.domain.model.VehicleCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
public class VehicleEntity {

    @Id
    private String id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, length = 100)
    private String brand;

    @Column(nullable = false)
    private int year;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private VehicleCategory category;

    @Column(name = "short_description", nullable = false, length = 500)
    private String shortDescription;

    @Column(name = "full_history", nullable = false, columnDefinition = "TEXT")
    private String fullHistory;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "vehicle_images",
            joinColumns = @JoinColumn(name = "vehicle_id"))
    @Column(name = "url", length = 1000)
    @OrderColumn(name = "sort_order")
    private List<String> imageUrls = new ArrayList<>();

    @Column(name = "engine_sound_url", length = 1000)
    private String engineSoundUrl;

    @Column(name = "display_order", nullable = false)
    private int displayOrder;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL,
               fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("sortOrder ASC")
    private List<VehicleSpecEntity> specs = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;
}
