package backend.com.comercial.repository.jpa.mapper;

import backend.com.comercial.domain.model.*;
import backend.com.comercial.repository.jpa.entity.*;
import backend.com.shared.model.Cliente;
import backend.com.shared.model.EspecificacionTecnica;
import backend.com.shared.model.Tela;
import backend.com.shared.model.Accesorio;
import backend.com.shared.model.Proveedor;
import backend.com.shared.model.Vendedor;
import backend.com.shared.valueobjects.DocumentNumber;
import backend.com.shared.valueobjects.Money;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SolicitudCostosMapper {

    public SolicitudCostos toDomain(SolicitudCostosJpaEntity entity) {
        if (entity == null)
            return null;

        return new SolicitudCostos(
                entity.getId(),
                new DocumentNumber(entity.getNumero()),
                entity.getEstado(),
                entity.getCliente() != null ? entity.getCliente().getId() : null,
                entity.getVendedor() != null ? entity.getVendedor().getId() : null,
                entity.getEspecificacionTecnica() != null ? entity.getEspecificacionTecnica().getId() : null,
                entity.getArticuloDescripcion(),
                entity.getEsMuestra(),
                entity.getCantidad(),
                entity.getFecha(),
                new Money(entity.getCostoTotalCalculado(), entity.getMonedaCostoTotal()),
                entity.getTelas().stream().map(this::toTelaDomain).collect(Collectors.toList()),
                entity.getAccesorios().stream().map(this::toAccesorioDomain).collect(Collectors.toList()),
                toCostoFijoDomain(entity.getCostoFijo()));
    }

    private SCOSTela toTelaDomain(SCOSTelaJpaEntity entity) {
        if (entity == null)
            return null;
        return new SCOSTela(
                entity.getTela() != null ? entity.getTela().getId() : null,
                entity.getDescripcion(),
                entity.getProveedor() != null ? entity.getProveedor().getId() : null,
                entity.getConsumo(),
                entity.getUnidadMedida(),
                new Money(entity.getPrecioUnitario(), entity.getMonedaPrecioUnitario()));
    }

    private SCOSAccesorio toAccesorioDomain(SCOSAccesorioJpaEntity entity) {
        if (entity == null)
            return null;
        return new SCOSAccesorio(
                entity.getAccesorio() != null ? entity.getAccesorio().getId() : null,
                entity.getDescripcion(),
                entity.getProveedor() != null ? entity.getProveedor().getId() : null,
                entity.getConsumo(),
                entity.getUnidadMedida(),
                new Money(entity.getPrecioUnitario(), entity.getMonedaPrecioUnitario()));
    }

    private SCOSCostoFijo toCostoFijoDomain(SCOSCostoFijoJpaEntity entity) {
        if (entity == null)
            return null;
        return new SCOSCostoFijo(
                new Money(entity.getHilo(), "CLP"),
                new Money(entity.getManoObraSimple(), "CLP"),
                new Money(entity.getManoObraGratificacion(), "CLP"),
                new Money(entity.getEtiqueta(), "CLP"),
                new Money(entity.getEmbalaje(), "CLP"),
                new Money(entity.getFlete(), "CLP"));
    }

    public SolicitudCostosJpaEntity toEntity(SolicitudCostos domain) {
        if (domain == null)
            return null;

        SolicitudCostosJpaEntity entity = new SolicitudCostosJpaEntity();
        if (domain.getId() != null) {
            entity.setId(domain.getId());
        }
        entity.setNumero(domain.getNumero().getValue());
        entity.setEstado(domain.getEstado());
        entity.setArticuloDescripcion(domain.getArticuloDescripcion());
        entity.setEsMuestra(domain.getEsMuestra());
        entity.setCantidad(domain.getCantidad());
        entity.setFecha(domain.getFecha());

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
        if (domain.getEspecificacionTecnicaId() != null) {
            EspecificacionTecnica et = new EspecificacionTecnica();
            et.setId(domain.getEspecificacionTecnicaId());
            entity.setEspecificacionTecnica(et);
        }

        if (domain.getCostoTotalCalculado() != null) {
            entity.setCostoTotalCalculado(domain.getCostoTotalCalculado().getAmount());
            entity.setMonedaCostoTotal(domain.getCostoTotalCalculado().getCurrency());
        }

        domain.getTelas().forEach(t -> {
            SCOSTelaJpaEntity te = new SCOSTelaJpaEntity();
            if (t.getTelaId() != null) {
                Tela telaResource = new Tela();
                telaResource.setId(t.getTelaId());
                te.setTela(telaResource);
            }
            te.setDescripcion(t.getDescripcion());
            if (t.getProveedorId() != null) {
                Proveedor p = new Proveedor();
                p.setId(t.getProveedorId());
                te.setProveedor(p);
            }
            te.setConsumo(t.getConsumo());
            te.setUnidadMedida(t.getUnidadMedida());
            if (t.getPrecioUnitario() != null) {
                te.setPrecioUnitario(t.getPrecioUnitario().getAmount());
                te.setMonedaPrecioUnitario(t.getPrecioUnitario().getCurrency());
            }
            if (t.getPrecioUnitario() != null && t.getConsumo() != null) {
                te.setCostoTotal(t.getPrecioUnitario().getAmount().multiply(t.getConsumo()));
                te.setMonedaCostoTotal(t.getPrecioUnitario().getCurrency());
            }
            entity.addTela(te);
        });

        domain.getAccesorios().forEach(a -> {
            SCOSAccesorioJpaEntity ae = new SCOSAccesorioJpaEntity();
            if (a.getAccesorioId() != null) {
                Accesorio acc = new Accesorio();
                acc.setId(a.getAccesorioId());
                ae.setAccesorio(acc);
            }
            ae.setDescripcion(a.getDescripcion());
            if (a.getProveedorId() != null) {
                Proveedor p = new Proveedor();
                p.setId(a.getProveedorId());
                ae.setProveedor(p);
            }
            ae.setConsumo(a.getConsumo());
            ae.setUnidadMedida(a.getUnidadMedida());
            if (a.getPrecioUnitario() != null) {
                ae.setPrecioUnitario(a.getPrecioUnitario().getAmount());
                ae.setMonedaPrecioUnitario(a.getPrecioUnitario().getCurrency());
            }
            if (a.getPrecioUnitario() != null && a.getConsumo() != null) {
                ae.setCostoTotal(a.getPrecioUnitario().getAmount().multiply(a.getConsumo()));
                ae.setMonedaCostoTotal(a.getPrecioUnitario().getCurrency());
            }
            entity.addAccesorio(ae);
        });

        if (domain.getCostoFijo() != null) {
            SCOSCostoFijoJpaEntity fe = new SCOSCostoFijoJpaEntity();
            fe.setHilo(domain.getCostoFijo().getHilo().getAmount());
            fe.setManoObraSimple(domain.getCostoFijo().getManoObraSimple().getAmount());
            fe.setManoObraGratificacion(domain.getCostoFijo().getManoObraGratificacion().getAmount());
            fe.setEtiqueta(domain.getCostoFijo().getEtiqueta().getAmount());
            fe.setEmbalaje(domain.getCostoFijo().getEmbalaje().getAmount());
            fe.setFlete(domain.getCostoFijo().getFlete().getAmount());
            fe.setTotal(domain.getCostoFijo().getTotal().getAmount());
            fe.setMonedaTotal(domain.getCostoFijo().getTotal().getCurrency());
            entity.setCostoFijo(fe);
        }

        return entity;
    }
}
