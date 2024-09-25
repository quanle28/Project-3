package com.javaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "district")
    private String district;

    @Column(name = "ward")
    private String ward;

    @Column(name = "street")
    private String street;

    @Column(name = "structure")
    private String structure;

    @Column(name = "numberofbasement")
    private Long numberOfBasement;

    @Column(name = "floorarea")
    private Long floorArea;

    @Column(name = "direction")
    private String direction;

    @Column(name = "level")
    private Long level;

    @Column(name = "rentprice")
    private Long rentPrice;

    @Column(name = "rentpricedescription")
    private String rentPriceDescription;

    @Column(name = "servicefee")
    private Long serviceFee;

    @Column(name = "carfee")
    private Long carFee;

    @Column(name = "motofee")
    private Long motorbikeFee;

    @Column(name = "overtimefee")
    private Long overtimeFee;

    @Column(name = "electricityfee")
    private Long electricityFee;

    @Column(name = "deposit")
    private Long deposit;

    @Column(name = "payment")
    private Long payment;

    @Column(name = "renttime")
    private String rentTime;

    @Column(name = "decorationtime")
    private String decorationTime;

    @Column(name = "managername")
    private String managerName;

    @Column(name = "managerphone")
    private String managerPhone;

    @Column(name = "brokeragefee")
    private Long brokerageFee;

    @Column(name = "type")
    private String typeCode;

    @Column(name = "note")
    private String note;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentbuilding",
                joinColumns = @JoinColumn(name = "buildingid", nullable = false),
                inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> userEntities = new ArrayList<>();

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    private List<RentAreaEntity> rentarea = new ArrayList<>();

    @OneToMany(mappedBy = "buildingId", fetch = FetchType.LAZY)
    private List<AssignBuildingEntity> assignBuilding = new ArrayList<>();

    public List<AssignBuildingEntity> getAssignBuilding() {
        return assignBuilding;
    }

    public void setAssignBuilding(List<AssignBuildingEntity> assignBuilding) {
        this.assignBuilding = assignBuilding;
    }

    public List<RentAreaEntity> getRentarea() {
        return rentarea;
    }

    public void setRentarea(List<RentAreaEntity> rentarea) {
        this.rentarea = rentarea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }

    public void setNumberOfBasement(Long numberOfBasement) {
        this.numberOfBasement = numberOfBasement;
    }

    public Long getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(Long floorArea) {
        this.floorArea = floorArea;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Long rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getRentPriceDescription() {
        return rentPriceDescription;
    }

    public void setRentPriceDescription(String rentPriceDescription) {
        this.rentPriceDescription = rentPriceDescription;
    }

    public Long getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Long serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Long getCarFee() {
        return carFee;
    }

    public void setCarFee(Long carFee) {
        this.carFee = carFee;
    }

    public Long getMotorbikeFee() {
        return motorbikeFee;
    }

    public void setMotorbikeFee(Long motorbikeFee) {
        this.motorbikeFee = motorbikeFee;
    }

    public Long getOvertimeFee() {
        return overtimeFee;
    }

    public void setOvertimeFee(Long overtimeFee) {
        this.overtimeFee = overtimeFee;
    }

    public Long getElectricityFee() {
        return electricityFee;
    }

    public void setElectricityFee(Long electricityFee) {
        this.electricityFee = electricityFee;
    }

    public Long getDeposit() {
        return deposit;
    }

    public void setDeposit(Long deposit) {
        this.deposit = deposit;
    }

    public Long getPayment() {
        return payment;
    }

    public void setPayment(Long payment) {
        this.payment = payment;
    }

    public String getRentTime() {
        return rentTime;
    }

    public void setRentTime(String rentTime) {
        this.rentTime = rentTime;
    }

    public String getDecorationTime() {
        return decorationTime;
    }

    public void setDecorationTime(String decorationTime) {
        this.decorationTime = decorationTime;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public Long getBrokerageFee() {
        return brokerageFee;
    }

    public void setBrokerageFee(Long brokerageFee) {
        this.brokerageFee = brokerageFee;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }
}
