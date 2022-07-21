package com.ykcho.base.notice.repository;

import com.ykcho.base.notice.entity.NoticeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Integer> {
    Page<NoticeEntity> findByTitleOrderById(String title, Pageable pageable);
    Page<NoticeEntity> findByCreatorIdOrderById(String creatorId, Pageable pageable);
}
