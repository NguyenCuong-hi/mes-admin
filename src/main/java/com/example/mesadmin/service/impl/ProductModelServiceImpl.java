package com.example.mesadmin.service.impl;

import com.example.mesadmin.repository.ProductModelRepository;
import com.example.mesadmin.service.ProductModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductModelServiceImpl implements ProductModelService {
    private ProductModelRepository productModelRepos;



}
