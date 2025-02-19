package com.example.mesadmin.service.impl;

import com.example.mesadmin.dto.request.search.ProductModelSearchDto;
import com.example.mesadmin.dto.response.ProductModelResDto;
import com.example.mesadmin.entity.ProductModelEntity;
import com.example.mesadmin.repository.ProductModelRepository;
import com.example.mesadmin.service.ProductModelService;
import com.nvc.core.response.NvcResponse;
import com.nvc.core.service.impl.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductModelServiceImpl extends BaseServiceImpl<ProductModelEntity, UUID> implements ProductModelService {

    private ProductModelRepository productModelRepos;

    @Override
    public NvcResponse<ProductModelResDto> GetById(UUID id) {

        return null;
    }

    @Override
    public NvcResponse<ProductModelResDto> SearchBy(ProductModelSearchDto searchDto) {
        return null;
    }
}
