package com.example.mesadmin.repository;

import com.example.mesadmin.entity.ProductModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductModelRepository extends JpaRepository<ProductModelEntity, UUID> {
}
