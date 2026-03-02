package backend.com.produccion.infrastructure.persistence.jpa.mapper;

import backend.com.produccion.domain.model.Costeo;
import backend.com.produccion.infrastructure.persistence.jpa.entity.CosteoJpaEntity;
import backend.com.shared.valueobjects.DocumentNumber;
import backend.com.shared.valueobjects.Money;
import org.springframework.stereotype.Component;

@Component
public class CosteoMapper {

    public Costeo toDomain(CosteoJpaEntity entity) {
        if (entity == null)
            return null;

        return new Costeo(
                entity.getId(),
                new DocumentNumber(entity.getNumero()),
                entity.getSolicitudCostosId(),
                new Money(entity.getCostoHilos(), "CLP"),
                new Money(entity.getCostoManoObra(), "CLP"),
                new Money(entity.getCostoEtiquetas(), "CLP"),
                new Money(entity.getCostoEmbalaje(), "CLP"),
                new Money(entity.getCostoFlete(), "CLP"),
                entity.getPorcentajeCostoFijo(),
                new Money(entity.getPrecioCinta1(), "CLP"),
                entity.getCantidadCinta1(),
                new Money(entity.getPrecioCinta2(), "CLP"),
                entity.getCantidadCinta2(),
                new Money(entity.getVivoReflectivo(), "CLP"),
                entity.getCantidadVivo(),
                new Money(entity.getCostoTotalMateriaPrima(), "CLP"),
                entity.getMargenBrutoSugerido(),
                new Money(entity.getPrecioVentaSugerido(), "CLP"));
    }

    public CosteoJpaEntity toJpaEntity(Costeo domain) {
        if (domain == null)
            return null;

        CosteoJpaEntity entity = new CosteoJpaEntity();
        entity.setId(domain.getId());
        entity.setNumero(domain.getNumero().getValue());
        entity.setSolicitudCostosId(domain.getSolicitudCostosId());
        entity.setCostoHilos(domain.getCostoHilos().getAmount());
        entity.setCostoManoObra(domain.getCostoManoObra().getAmount());
        entity.setCostoEtiquetas(domain.getCostoEtiquetas().getAmount());
        entity.setCostoEmbalaje(domain.getCostoEmbalaje().getAmount());
        entity.setCostoFlete(domain.getCostoFlete().getAmount());
        entity.setPorcentajeCostoFijo(domain.getPorcentajeCostoFijo());
        entity.setPrecioCinta1(domain.getPrecioCinta1().getAmount());
        entity.setCantidadCinta1(domain.getCantidadCinta1());
        entity.setPrecioCinta2(domain.getPrecioCinta2().getAmount());
        entity.setCantidadCinta2(domain.getCantidadCinta2());
        entity.setVivoReflectivo(domain.getVivoReflectivo().getAmount());
        entity.setCantidadVivo(domain.getCantidadVivo());
        entity.setCostoTotalMateriaPrima(domain.getCostoTotalMateriaPrima().getAmount());
        entity.setMargenBrutoSugerido(domain.getMargenBrutoSugerido());
        entity.setPrecioVentaSugerido(domain.getPrecioVentaSugerido().getAmount());

        return entity;
    }
}
