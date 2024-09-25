package com.javaweb.repository;

import com.javaweb.entity.AssignBuildingEntity;
import com.javaweb.repository.custom.AssignmentBuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentBuildingRepository extends JpaRepository<AssignBuildingEntity, Long> , AssignmentBuildingRepositoryCustom {

}
