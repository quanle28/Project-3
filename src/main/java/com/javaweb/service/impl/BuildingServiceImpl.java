package com.javaweb.service.impl;

import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.entity.AssignBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.EditBuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRopository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.utils.UploadFileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Base64;
import java.util.stream.Collectors;

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
    private RentAreaRopository rentAreaRepository;

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

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
        if (editBuildingDTO.getId() != null) {
            buildingEntity = buildingRepository.findById(editBuildingDTO.getId()).get();
            List<RentAreaEntity> rentAll = rentAreaRepository.findByBuildingId(editBuildingDTO.getId());
            for (RentAreaEntity rentAreaEntity : rentAll) {
                rentAreaRepository.delete(rentAreaEntity);
            }
            modelMapper.map(editBuildingDTO, buildingEntity);
        } else {
            // Nếu không có ID, tạo mới tòa nhà
            buildingEntity = modelMapper.map(editBuildingDTO, BuildingEntity.class);
        }
        saveThumbnail(editBuildingDTO, buildingEntity);
        buildingRepository.save(buildingEntity);
        // Xử lý khu vực cho thuê
        if (editBuildingDTO.getRentArea() != null && !editBuildingDTO.getRentArea().isEmpty()) {
            String[] rentAreas = editBuildingDTO.getRentArea().trim().split(",");
            for (String rentArea : rentAreas) {
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setValue(rentArea);
                rentAreaEntity.setBuilding(buildingEntity);
                rentAreaRepository.save(rentAreaEntity);
            }
        }
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
        if (ids != null && !ids.isEmpty()) {
            ids.forEach(id -> {
                    rentAreaRepository.deleteByBuildingId(id);
                    assignmentBuildingRepository.deleteByBuildingIdId(id);
                    buildingRepository.deleteById(id);
            });
        }
    }

    @Override
    @Transactional
    public void updateAssignment(AssignmentBuildingDTO assignmentBuildingDTO) {
        List<Long> assignmentId = assignmentBuildingDTO.getStaffs();
        List<AssignBuildingEntity> listAssignment = new ArrayList<>();
        for (Long id : assignmentId) {
            AssignBuildingEntity assignBuildingEntity = new AssignBuildingEntity();
            UserEntity user = userRepository.findById(id).get();
            assignBuildingEntity.setUserId(user);
            BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
            assignBuildingEntity.setBuildingId(buildingEntity);
            listAssignment.add(assignBuildingEntity);
        }
        assignmentBuildingRepository.saveAll(listAssignment);

    }

    @Override
    @Transactional
    public void deleteAssignment(AssignmentBuildingDTO assignmentBuildingDTO) {
        List<AssignBuildingEntity> assignBuildingEntityList = assignmentBuildingRepository.findByBuildingId(assignmentBuildingDTO.getBuildingId());
        for (AssignBuildingEntity assignBuildingEntity : assignBuildingEntityList) {
            assignmentBuildingRepository.delete(assignBuildingEntity);
        }
//        assignBuildingEntities.forEach(assignmentBuildingRepository::delete);
    }

    @Override
    public EditBuildingDTO toEditBuildingDTO(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        EditBuildingDTO editBuildingDTO = modelMapper.map(buildingEntity, EditBuildingDTO.class);
        String typecode = buildingEntity.getTypeCode();  // Assuming 'getTypecode()' returns a string
        List<String> typecodeList = Arrays.stream(typecode.replaceAll("[\\[\\]]", "").split(", "))
                                            .collect(Collectors.toList());
        editBuildingDTO.setTypeCode(typecodeList);
        List<RentAreaEntity> rentAreaEntityList = rentAreaRepository.findByBuildingId(id);
        String rentResult = rentAreaEntityList.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(", "));
        editBuildingDTO.setRentArea(rentResult);
        editBuildingDTO.setImage(buildingEntity.getImage());
        return editBuildingDTO;
    }
}




