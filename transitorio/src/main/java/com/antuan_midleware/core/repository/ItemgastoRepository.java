package com.antuan_midleware.core.repository;

import com.antuan_midleware.core.model.Itemgasto;
import com.antuan_midleware.core.model.ItemgastoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemgastoRepository extends JpaRepository<Itemgasto, ItemgastoId> {
}
