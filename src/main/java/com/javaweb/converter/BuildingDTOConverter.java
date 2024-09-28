package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.EditBuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.RentAreaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    public BuildingSearchResponse toBuildingSearchResponse (BuildingEntity item) {
        BuildingSearchResponse building = modelMapper.map(item, BuildingSearchResponse.class);
        building.setAddress(item.getStreet()+", "+item.getWard()+", "+item.getDistrict());
        List<RentAreaEntity> listRentareaEntity = item.getRentarea();
        String rentResult = listRentareaEntity.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(", "));
        building.setRentArea(rentResult);
        return building;
    }

    public List<RentAreaEntity> addRentAreaConverter(EditBuildingDTO editBuildingDTO, BuildingEntity buildingEntity){
        // Xử lý khu vực cho thuê
        List<RentAreaEntity> areaEntityList = new ArrayList<>();
        if (editBuildingDTO.getRentArea() != null && !editBuildingDTO.getRentArea().isEmpty()) {
            String[] rentAreas = editBuildingDTO.getRentArea().trim().split(",");
            for (String rentArea : rentAreas) {
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setValue(rentArea);
                rentAreaEntity.setBuilding(buildingEntity);
                areaEntityList.add(rentAreaEntity);
            }
        }
        return areaEntityList;
    }

    public EditBuildingDTO getEditBuildingDTO(BuildingEntity buildingEntity, Long id) {
        EditBuildingDTO editBuildingDTO = modelMapper.map(buildingEntity, EditBuildingDTO.class);
        String typeCode = buildingEntity.getTypeCode();
        List<String> typecodeList = Arrays.stream(typeCode.replaceAll("[\\[\\]]", "").split(", "))
                .collect(Collectors.toList());
        editBuildingDTO.setTypeCode(typecodeList);
        List<RentAreaEntity> rentAreaEntityList = rentAreaRepository.findByBuildingId(id);
        String rentResult = rentAreaEntityList.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(", "));
        editBuildingDTO.setRentArea(rentResult);
        editBuildingDTO.setImage(buildingEntity.getImage());
        return editBuildingDTO;
    }
}
