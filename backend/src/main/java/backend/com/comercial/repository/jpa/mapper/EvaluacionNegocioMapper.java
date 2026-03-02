package backend.com.comercial.repository.jpa.mapper;

import backend.com.comercial.domain.model.EstadoEVN;
import backend.com.comercial.domain.model.EvaluacionNegocio;
import backend.com.comercial.domain.model.ItemEVN;
import backend.com.comercial.repository.jpa.entity.EvaluacionNegocioItemJpaEntity;
import backend.com.comercial.repository.jpa.entity.EvaluacionNegocioJpaEntity;
import backend.com.shared.model.Cliente;
import backend.com.shared.model.Producto;
import backend.com.shared.model.Proveedor;
import backend.com.shared.model.Vendedor;
import backend.com.shared.valueobjects.DocumentNumber;
import backend.com.shared.valueobjects.Money;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EvaluacionNegocioMapper {

    public EvaluacionNegocio toDomain(EvaluacionNegocioJpaEntity entity) {
        if (entity == null)
            return null;

        return new EvaluacionNegocio(
                entity.getId(),
                new DocumentNumber(entity.getNumero()),
                entity.getCliente() != null ? entity.getCliente().getId() : null,
                entity.getVendedor() != null ? entity.getVendedor().getId() : null,
                EstadoEVN.valueOf(entity.getEstado()),
                entity.getFechaEvaluacion(),
                new Money(entity.getMontoTotal(), entity.getMonedaMontoTotal()),
                entity.getMargenGanancia(),
                new Money(entity.getRentabilidadEsperada(), entity.getMonedaRentabilidad()),
                new Money(entity.getGarantiaSeriedad(), "CLP"),
                new Money(entity.getGarantiaFiel(), "CLP"),
                new Money(entity.getFleteEspecial(), "CLP"),
                new Money(entity.getModificacionPrenda(), "CLP"),
                new Money(entity.getTomaTallaje(), "CLP"),
                new Money(entity.getCertificacion(), "CLP"),
                new Money(entity.getMuestrasFisicas(), "CLP"),
                entity.getItems().stream().map(this::toItemDomain).collect(Collectors.toList()),
                entity.getCosteoId(),
                entity.getSolicitudCotizacionId());
    }

    private ItemEVN toItemDomain(EvaluacionNegocioItemJpaEntity entity) {
        if (entity == null)
            return null;
        return new ItemEVN(
                entity.getProducto() != null ? entity.getProducto().getId() : null,
                entity.getProveedor() != null ? entity.getProveedor().getId() : null,
                entity.getCantidad(),
                new Money(entity.getPrecioUnitario(), entity.getMonedaPrecioUnitario()),
                new Money(entity.getCostoUnitario(), entity.getMonedaCostoUnitario()),
                entity.getTipoItem(),
                entity.getReferenciaTexto(),
                entity.getMargenItem(),
                new Money(entity.getTotal(), entity.getMonedaTotal()));
    }

    public EvaluacionNegocioJpaEntity toEntity(EvaluacionNegocio domain) {
        if (domain == null)
            return null;

        EvaluacionNegocioJpaEntity entity = new EvaluacionNegocioJpaEntity();
        if (domain.getId() != null) {
            entity.setId(domain.getId());
        }
        entity.setNumero(domain.getNumero().getValue());
        entity.setEstado(domain.getEstado().name());
        entity.setFechaEvaluacion(domain.getFechaEvaluacion());

        if (domain.getClienteId() != null) {
            Cliente c = new Cliente();
            c.setId(domain.getClienteId());
            entity.setCliente(c);
        }
        if (domain.getVendedorId() != null) {
            Vendedor v = new Vendedor();
            v.setId(domain.getVendedorId());
            entity.setVendedor(v);
        }

        if (domain.getMontoTotal() != null) {
            entity.setMontoTotal(domain.getMontoTotal().getAmount());
            entity.setMonedaMontoTotal(domain.getMontoTotal().getCurrency());
        }
        entity.setMargenGanancia(domain.getMargenGanancia());
        if (domain.getRentabilidadEsperada() != null) {
            entity.setRentabilidadEsperada(domain.getRentabilidadEsperada().getAmount());
            entity.setMonedaRentabilidad(domain.getRentabilidadEsperada().getCurrency());
        }

        // Costos extras
        if (domain.getGarantiaSeriedad() != null)
            entity.setGarantiaSeriedad(domain.getGarantiaSeriedad().getAmount());
        if (domain.getGarantiaFiel() != null)
            entity.setGarantiaFiel(domain.getGarantiaFiel().getAmount());
        if (domain.getFleteEspecial() != null)
            entity.setFleteEspecial(domain.getFleteEspecial().getAmount());
        if (domain.getModificacionPrenda() != null)
            entity.setModificacionPrenda(domain.getModificacionPrenda().getAmount());
        if (domain.getTomaTallaje() != null)
            entity.setTomaTallaje(domain.getTomaTallaje().getAmount());
        if (domain.getCertificacion() != null)
            entity.setCertificacion(domain.getCertificacion().getAmount());
        if (domain.getMuestrasFisicas() != null)
            entity.setMuestrasFisicas(domain.getMuestrasFisicas().getAmount());

        entity.setCosteoId(domain.getCosteoId());
        entity.setSolicitudCotizacionId(domain.getSolicitudCotizacionId());

        domain.getItems().forEach(itemDomain -> {
            EvaluacionNegocioItemJpaEntity itemEntity = new EvaluacionNegocioItemJpaEntity();
            if (itemDomain.getProductoId() != null) {
                Producto p = new Producto();
                p.setId(itemDomain.getProductoId());
                itemEntity.setProducto(p);
            }
            if (itemDomain.getProveedorId() != null) {
                Proveedor prov = new Proveedor();
                prov.setId(itemDomain.getProveedorId());
                itemEntity.setProveedor(prov);
            }
            itemEntity.setCantidad(itemDomain.getCantidad());
            if (itemDomain.getPrecioUnitario() != null) {
                itemEntity.setPrecioUnitario(itemDomain.getPrecioUnitario().getAmount());
                itemEntity.setMonedaPrecioUnitario(itemDomain.getPrecioUnitario().getCurrency());
            }
            if (itemDomain.getCostoUnitario() != null) {
                itemEntity.setCostoUnitario(itemDomain.getCostoUnitario().getAmount());
                itemEntity.setMonedaCostoUnitario(itemDomain.getCostoUnitario().getCurrency());
            }
            itemEntity.setTipoItem(itemDomain.getTipoItem());
            itemEntity.setReferenciaTexto(itemDomain.getReferenciaTexto());
            itemEntity.setMargenItem(itemDomain.getMargenItem());
            if (itemDomain.getTotal() != null) {
                itemEntity.setTotal(itemDomain.getTotal().getAmount());
                itemEntity.setMonedaTotal(itemDomain.getTotal().getCurrency());
            }
            entity.addItem(itemEntity);
        });

        return entity;
    }
}
