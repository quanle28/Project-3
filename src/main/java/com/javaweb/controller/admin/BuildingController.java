package com.javaweb.controller.admin;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.District;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.EditBuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {

    @Autowired
    private UserService userService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private BuildingRepository buildingRepository;

    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute BuildingDTO buildingDTO, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("searchBuilding", buildingDTO);
        List<BuildingSearchResponse> result = buildingService.findBuildingByRequest(buildingDTO);
        // Da lay dlieu duoi db len

        mav.addObject("searchResponses", result);
        mav.addObject("listStaffs", userService.getStaffs());
        mav.addObject("listDistricts", District.type());
        mav.addObject("listTypeCodes", TypeCode.type());
        return mav;
    }

    @RequestMapping(value = "/admin/building-edit", method = RequestMethod.GET)
    public ModelAndView buildingAdd(@ModelAttribute("editBuilding") EditBuildingDTO editBuildingDTO, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("listDistricts", District.type());
        mav.addObject("listTypeCodes", TypeCode.type());
        return mav;
    }

    @RequestMapping(value = "/admin/building-edit-{id}", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/building/edit");
        // xuong db tim id theo id
        EditBuildingDTO editBuildingDTO = buildingService.toEditBuildingDTO(id);
//        editBuildingDTO.setId(id);
//        editBuildingDTO.setName("Quan");
        mav.addObject("editBuilding", editBuildingDTO);
        mav.addObject("listDistricts", District.type());
        mav.addObject("listTypeCodes", TypeCode.type());
        return mav;
    }

}
