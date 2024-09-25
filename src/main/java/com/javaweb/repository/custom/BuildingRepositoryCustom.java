package com.javaweb.repository.custom;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> findBuildingByRequest(BuildingDTO buildingDTO);
}
