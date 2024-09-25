package com.javaweb.repository.custom.impl;

import com.javaweb.entity.RentAreaEntity;
import com.javaweb.repository.custom.RentAreaRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RentAreaRepositoryCustomImpl implements RentAreaRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<RentAreaEntity> findByBuildingId(Long buildingId) {
        String sql = "SELECT * FROM rentarea WHERE buildingid = "+buildingId;
        Query query = entityManager.createNativeQuery(sql, RentAreaEntity.class);
        return query.getResultList();
    }
}
