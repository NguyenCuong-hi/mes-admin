package com.example.mesadmin.entity;

import com.nvc.core.entity.BaseUUIDEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "product_model")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductModelEntity extends BaseUUIDEntity {

    @Column(name = "device")
    private String device;

    @Column(name = "description")
    private String description;

    @Column(name = "remark")
    private String remark;

    @Column(name = "customer")
    private String customer;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "approval")
    private Boolean approval;

    @Column(name = "model")
    private String model;

    @Column(name = "discontinue")
    private Boolean discontinue;

}
