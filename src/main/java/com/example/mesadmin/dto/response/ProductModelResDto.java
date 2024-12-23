package com.example.mesadmin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductModelResDto {

    private UUID id;

    private String device;

    private String description;

    private String remark;

    private String customer;

    private String projectName;

    private Boolean approval;

    private String model;

    private Boolean discontinue;
}
