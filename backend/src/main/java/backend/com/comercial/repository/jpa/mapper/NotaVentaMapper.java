package backend.com.comercial.repository.jpa.mapper;

import backend.com.comercial.domain.model.EstadoNV;
import backend.com.comercial.domain.model.ItemNV;
import backend.com.comercial.domain.model.ItemNVTalla;
import backend.com.comercial.domain.model.NotaVenta;
import backend.com.comercial.repository.jpa.entity.NotaVentaItemJpaEntity;
import backend.com.comercial.repository.jpa.entity.NotaVentaItemTallaJpaEntity;
import backend.com.comercial.repository.jpa.entity.NotaVentaJpaEntity;
import backend.com.shared.model.Cliente;
import backend.com.shared.model.Producto;
import backend.com.shared.model.Proveedor;
import backend.com.shared.model.Vendedor;
import backend.com.shared.valueobjects.DocumentNumber;
import backend.com.shared.valueobjects.Money;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class NotaVentaMapper {

    public NotaVenta toDomain(NotaVentaJpaEntity entity) {
        if (entity == null)
            return null;

        return new NotaVenta(
                entity.getId(),
                new DocumentNumber(entity.getNumero()),
                entity.getEvaluacionNegocioId(),
                entity.getCliente() != null ? entity.getCliente().getId() : null,
                entity.getVendedor() != null ? entity.getVendedor().getId() : null,
                EstadoNV.valueOf(entity.getEstado()),
                entity.getEsKit(),
                entity.getFechaEmision(),
                entity.getFechaEntregaEstimada(),
                new Money(entity.getMontoSubtotal(), entity.getMonedaSubtotal()),
                new Money(entity.getMontoIva(), entity.getMonedaIva()),
                new Money(entity.getMontoTotal(), entity.getMonedaTotal()),
                entity.getItems().stream().map(this::toItemDomain).collect(Collectors.toList()));
    }

    private ItemNV toItemDomain(NotaVentaItemJpaEntity entity) {
        if (entity == null)
            return null;
        return new ItemNV(
                entity.getNroItem(),
                entity.getProducto() != null ? entity.getProducto().getId() : null,
                entity.getModelo(),
                entity.getTela(),
                entity.getComposicion(),
                entity.getColor(),
                entity.getTalla(),
                entity.getGenero(),
                entity.getCodigo(),
                entity.getProveedor() != null ? entity.getProveedor().getId() : null,
                entity.getLlevaLogo(),
                entity.getItemType(),
                entity.getIsPersonalized(),
                entity.getDetalleOt(),
                entity.getLogoDetalle(),
                entity.getCantidad(),
                new Money(entity.getPrecioUnitario(), entity.getMonedaPrecioUnitario()),
                entity.getTallas().stream().map(this::toTallaDomain).collect(Collectors.toList()));
    }

    private ItemNVTalla toTallaDomain(NotaVentaItemTallaJpaEntity entity) {
        if (entity == null)
            return null;
        return new ItemNVTalla(entity.getTalla(), entity.getCantidad());
    }

    public NotaVentaJpaEntity toEntity(NotaVenta domain) {
        if (domain == null)
            return null;

        NotaVentaJpaEntity entity = new NotaVentaJpaEntity();
        if (domain.getId() != null) {
            entity.setId(domain.getId());
        }
        entity.setNumero(domain.getNumero().getValue());
        entity.setEvaluacionNegocioId(domain.getEvaluacionNegocioId());
        entity.setEstado(domain.getEstado().name());
        entity.setEsKit(domain.getEsKit());
        entity.setFechaEmision(domain.getFechaEmision());
        entity.setFechaEntregaEstimada(domain.getFechaEntregaEstimada());

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

        if (domain.getMontoSubtotal() != null) {
            entity.setMontoSubtotal(domain.getMontoSubtotal().getAmount());
            entity.setMonedaSubtotal(domain.getMontoSubtotal().getCurrency());
        }
        if (domain.getMontoIva() != null) {
            entity.setMontoIva(domain.getMontoIva().getAmount());
            entity.setMonedaIva(domain.getMontoIva().getCurrency());
        }
        if (domain.getMontoTotal() != null) {
            entity.setMontoTotal(domain.getMontoTotal().getAmount());
            entity.setMonedaTotal(domain.getMontoTotal().getCurrency());
        }

        domain.getItems().forEach(itemDomain -> {
            NotaVentaItemJpaEntity itemEntity = new NotaVentaItemJpaEntity();
            itemEntity.setNroItem(itemDomain.getNroItem());
            if (itemDomain.getProductoId() != null) {
                Producto p = new Producto();
                p.setId(itemDomain.getProductoId());
                itemEntity.setProducto(p);
            }
            itemEntity.setModelo(itemDomain.getModelo());
            itemEntity.setTela(itemDomain.getTela());
            itemEntity.setComposicion(itemDomain.getComposicion());
            itemEntity.setColor(itemDomain.getColor());
            itemEntity.setTalla(itemDomain.getTalla());
            itemEntity.setGenero(itemDomain.getGenero());
            itemEntity.setCodigo(itemDomain.getCodigo());
            if (itemDomain.getProveedorId() != null) {
                Proveedor prov = new Proveedor();
                prov.setId(itemDomain.getProveedorId());
                itemEntity.setProveedor(prov);
            }
            itemEntity.setLlevaLogo(itemDomain.getLlevaLogo());
            itemEntity.setItemType(itemDomain.getItemType());
            itemEntity.setIsPersonalized(itemDomain.getIsPersonalized());
            itemEntity.setDetalleOt(itemDomain.getDetalleOt());
            itemEntity.setLogoDetalle(itemDomain.getLogoDetalle());
            itemEntity.setCantidad(itemDomain.getCantidad());

            if (itemDomain.getPrecioUnitario() != null) {
                itemEntity.setPrecioUnitario(itemDomain.getPrecioUnitario().getAmount());
                itemEntity.setMonedaPrecioUnitario(itemDomain.getPrecioUnitario().getCurrency());
            }
            if (itemDomain.getTotal() != null) {
                itemEntity.setTotal(itemDomain.getTotal().getAmount());
                itemEntity.setMonedaTotal(itemDomain.getTotal().getCurrency());
            }

            itemDomain.getTallas().forEach(tallaDomain -> {
                NotaVentaItemTallaJpaEntity te = new NotaVentaItemTallaJpaEntity();
                te.setTalla(tallaDomain.getTalla());
                te.setCantidad(tallaDomain.getCantidad());
                itemEntity.addTalla(te);
            });

            entity.addItem(itemEntity);
        });

        return entity;
    }
}
