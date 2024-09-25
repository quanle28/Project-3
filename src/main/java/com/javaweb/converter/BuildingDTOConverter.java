package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingSearchResponse toBuildingSearchResponse (BuildingEntity item) {
        BuildingSearchResponse building = modelMapper.map(item, BuildingSearchResponse.class);
        building.setAddress(item.getStreet()+","+item.getWard()+","+item.getDistrict());
        List<RentAreaEntity> listRentareaEntity = item.getRentarea();
        String rentResult = listRentareaEntity.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(", "));
        building.setRentArea(rentResult);
        return building;
    }
}
