package com.javaweb.repository.custom.impl;

import com.javaweb.entity.AssignBuildingEntity;
import com.javaweb.repository.custom.AssignmentBuildingRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AssignmentBuildingRepositoryCustomImpl implements AssignmentBuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<AssignBuildingEntity> findByBuildingId(Long buildingId) {
        String sql = "SELECT * FROM assignmentbuilding WHERE buildingid = "+buildingId;
        Query query = entityManager.createNativeQuery(sql, AssignBuildingEntity.class);
        return query.getResultList();
    }
}
