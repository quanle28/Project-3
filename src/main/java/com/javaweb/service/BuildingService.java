package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.EditBuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;

import java.util.List;

public interface BuildingService {
    ResponseDTO listStaffs(Long buildingId);

    List<BuildingSearchResponse> findBuildingByRequest(BuildingDTO buildingDTO);

    void addOrUpdateBuilding (EditBuildingDTO editBuildingDTO);

    void deleteBuilding (List<Long> ids);

    void updateAssignment(AssignmentBuildingDTO assignmentBuildingDTO);

    EditBuildingDTO toEditBuildingDTO(Long id);
}
