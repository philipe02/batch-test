package com.example.batchtest.repositories;

import com.example.batchtest.models.NotificationsToSendEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationsToSendRepository extends JpaRepository<NotificationsToSendEntity, Integer> {
    Page<NotificationsToSendEntity> findAllBySentFalse(Pageable pageable);
}
