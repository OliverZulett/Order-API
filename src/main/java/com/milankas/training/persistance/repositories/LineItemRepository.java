package com.milankas.training.persistance.repositories;

import com.milankas.training.persistance.entities.LineItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LineItemRepository extends JpaRepository<LineItemEntity, UUID> { }
