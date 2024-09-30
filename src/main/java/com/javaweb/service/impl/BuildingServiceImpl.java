package com.javaweb.service.impl;

import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.EditBuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.utils.UploadFileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuildingDTOConverter buildingDTOConverter;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UploadFileUtils uploadFileUtils;

    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        BuildingEntity building = buildingRepository.findById(buildingId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> staffAssignment = building.getUserEntities();
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for (UserEntity it : staffs) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(it.getFullName());
            staffResponseDTO.setStaffId(it.getId());
            if(staffAssignment.contains(it)){
                staffResponseDTO.setChecked("checked");
            }else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("Success");
        return responseDTO;
    }

    @Override
    public List<BuildingSearchResponse> findBuildingByRequest(BuildingDTO buildingDTO) {
        List<BuildingEntity> buildingEntities = buildingRepository.findBuildingByRequest(buildingDTO);
        List<BuildingSearchResponse> result = new ArrayList<>();
        for (BuildingEntity item : buildingEntities) {
            BuildingSearchResponse building = buildingDTOConverter.toBuildingSearchResponse(item);
            result.add(building);
        }
        return result;
    }

    @Override
    @Transactional
    public void addOrUpdateBuilding(EditBuildingDTO editBuildingDTO) {
        BuildingEntity buildingEntity;
        // Nếu có ID, cập nhật tòa nhà hiện tại
        if (editBuildingDTO.getId() != null && editBuildingDTO.getImage() != null) {
            buildingEntity = buildingRepository.findById(editBuildingDTO.getId()).get();
            modelMapper.map(editBuildingDTO, buildingEntity);
        } else {
            // Nếu không có ID, tạo mới tòa nhà
            buildingEntity = modelMapper.map(editBuildingDTO, BuildingEntity.class);
        }
        List<RentAreaEntity> areaEntityList = buildingDTOConverter.setRentAreaConverter(editBuildingDTO, buildingEntity);
        buildingEntity.setRentarea(areaEntityList);
        saveThumbnail(editBuildingDTO, buildingEntity);
        buildingRepository.save(buildingEntity);

    }

    private void saveThumbnail(EditBuildingDTO editBuildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + editBuildingDTO.getImageName();
        if (null != editBuildingDTO.getImageBase64()) {
            if (null != buildingEntity.getImage()) {
                if (!path.equals(buildingEntity.getImage())) {
                    File file = new File("C://home/office" + buildingEntity.getImage());
                    file.delete();
                }
            }
            String cleanBase64 = editBuildingDTO.getImageBase64().replaceAll("[^A-Za-z0-9+/=]", "");
            while (cleanBase64.length() % 4 != 0) {
                cleanBase64 += "=";
            }
            byte[] bytes = Base64.getDecoder().decode(cleanBase64);
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setImage(path);
        }
    }


    @Override
    @Transactional
    public void deleteBuilding(List<Long> ids) {
        buildingRepository.deleteByIdIn(ids);
    }

    @Override
    @Transactional
    public void updateAssignment(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        List<UserEntity> userEntityList = new ArrayList<>();
        for (Long it : assignmentBuildingDTO.getStaffs()){
            userEntityList.add(userRepository.findById(it).get());
        }
        buildingEntity.setUserEntities(userEntityList);
    }

    @Override
    public EditBuildingDTO toEditBuildingDTO(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        return buildingDTOConverter.getEditBuildingDTO(buildingEntity, id);
    }
}




