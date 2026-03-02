package backend.com.comercial.repository.jpa.spring;

import backend.com.comercial.repository.jpa.entity.EvaluacionNegocioJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluacionNegocioJpaRepository extends JpaRepository<EvaluacionNegocioJpaEntity, Long> {
    List<EvaluacionNegocioJpaEntity> findByEstado(String estado);
}


