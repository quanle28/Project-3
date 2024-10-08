package com.javaweb.repository;

import com.javaweb.entity.RentAreaEntity;
import com.javaweb.repository.custom.RentAreaRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentAreaRopository extends JpaRepository<RentAreaEntity, Long> , RentAreaRepositoryCustom {
    List<RentAreaEntity> findValueByBuildingId(Long buildingId);

    void deleteByBuildingId(Long buildingId);
}
