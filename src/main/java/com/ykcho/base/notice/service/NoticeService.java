package com.ykcho.base.notice.service;

import com.ykcho.base.common.constants.ResultCode;
import com.ykcho.base.common.exception.RuntimeExceptionBase;
import com.ykcho.base.notice.entity.NoticeEntity;
import com.ykcho.base.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeEntity selectNoticeDTO (int id) {
        Optional<NoticeEntity> noticeDTO = noticeRepository.findById(id);
        return noticeDTO.orElse(null);
    }

    public Page<NoticeEntity> selectNoticeDTOList(String category, String searchText, Pageable pageable) {
        if("TITLE".equalsIgnoreCase(category)) {
            return noticeRepository.findByTitleOrderById(searchText, pageable);
        } else if("CREATOR".equalsIgnoreCase(category)) {
            return noticeRepository.findByCreatorIdOrderById(searchText, pageable);
        } else {
            return noticeRepository.findAll(pageable);
        }
    }

    public boolean deleteNoticeDTO(int id) {
        noticeRepository.deleteById(id);
        return Boolean.TRUE;
    }

    public NoticeEntity insertNoticeDTO(NoticeEntity noticeEntity) {
        noticeEntity.setCreated(new Date());
        return noticeRepository.save(noticeEntity);
    }

    public NoticeEntity updateNoticeDTO(int id, String title, String contents, String updaterId) {
        Optional<NoticeEntity> tempNoticeDTO = noticeRepository.findById(id);
        if(tempNoticeDTO.isPresent()) {
            tempNoticeDTO.get().setTitle(title);
            tempNoticeDTO.get().setContent(contents);
            tempNoticeDTO.get().setUpdated(new Date());
            tempNoticeDTO.get().setUpdaterId(updaterId);
            return noticeRepository.save(tempNoticeDTO.get());
        }else {
            throw new RuntimeExceptionBase(ResultCode.NOT_FOUND);
        }
    }
}
