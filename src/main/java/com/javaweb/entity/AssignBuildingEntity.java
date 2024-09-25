package com.javaweb.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "assignmentbuilding")
public class AssignBuildingEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "buildingid")
    private BuildingEntity buildingId;

    @ManyToOne
    @JoinColumn(name = "staffid")
    private UserEntity userId;

    public BuildingEntity getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(BuildingEntity buildingId) {
        this.buildingId = buildingId;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }
}
