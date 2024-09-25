package com.javaweb.repository.custom;

import com.javaweb.entity.AssignBuildingEntity;

import java.util.List;

public interface AssignmentBuildingRepositoryCustom {
    List<AssignBuildingEntity> findByBuildingId(Long buildingId);
}
