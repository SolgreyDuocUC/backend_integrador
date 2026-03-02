package backend.com.adquisiciones.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "solicitud_compra")
@Getter
@Setter
public class SolicitudCompraJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, precision = 20, nullable = false)
    private Long numero;

    private Long tenantId;

    private Long notaVentaId;

    @Column(length = 30)
    private String estado;

    private LocalDate fechaEmision;

    @OneToMany(mappedBy = "solicitudCompra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemSCJpaEntity> items = new ArrayList<>();

    public void addItem(ItemSCJpaEntity item) {
        items.add(item);
        item.setSolicitudCompra(this);
    }
}
