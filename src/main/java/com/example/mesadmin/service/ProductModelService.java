package com.example.mesadmin.service;

import com.example.mesadmin.dto.request.search.ProductModelSearchDto;
import com.example.mesadmin.dto.response.ProductModelResDto;
import com.example.mesadmin.entity.ProductModelEntity;
import com.nvc.core.response.NvcResponse;
import com.nvc.core.service.BaseService;

import java.util.UUID;

public interface ProductModelService extends BaseService<ProductModelEntity, UUID> {

    NvcResponse<ProductModelResDto> GetById(UUID id);

    NvcResponse<ProductModelResDto> SearchBy(ProductModelSearchDto searchDto);
}
