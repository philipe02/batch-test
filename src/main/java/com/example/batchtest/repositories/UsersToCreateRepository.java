package com.example.batchtest.repositories;

import com.example.batchtest.models.UsersToCreateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersToCreateRepository extends JpaRepository<UsersToCreateEntity, Integer> {
    Page<UsersToCreateEntity> findAllByCreatedFalse(Pageable pageable);
}
