package com.ykcho.base.notice.controller;

import com.ykcho.base.notice.entity.NoticeEntity;
import com.ykcho.base.notice.service.NoticeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoticeControllerTest {

    private NoticeService noticeService;

    private NoticeController noticeController;

    private NoticeEntity noticeEntity;
    private NoticeEntity updateNoticeEntity;

    private final String CATEGORY = "TEST";
    private final String SEARCHTEXT = "TEST_SEARCH";

    private final String UPDATE_TITLE = "UPDATE_TITLE";
    private final String UPDATE_CONTENTS = "UPDATE_CONTENTS";
    private final String UPDATE_ACCOUNT = "TEST_ACCOUNT";

    private final int id = 1;
    private Pageable pageable;
    List<NoticeEntity> list;

    @BeforeEach
    void setUp() {
        String testTitle = "TEST_TITLE";
        String testContents = "TEST_CONTENTS";
        noticeService = mock(NoticeService.class);
        ModelMapper modelMapper = new ModelMapper();
        noticeController = new NoticeController(noticeService);
        noticeEntity = new NoticeEntity();
        noticeEntity.setId(id);
        noticeEntity.setTitle(testTitle);
        noticeEntity.setContent(testContents);
        updateNoticeEntity = new NoticeEntity();
        updateNoticeEntity.setId(id);
        updateNoticeEntity.setTitle(UPDATE_TITLE);
        updateNoticeEntity.setContent(UPDATE_CONTENTS);
        updateNoticeEntity.setUpdaterId(UPDATE_ACCOUNT);

        pageable = PageRequest.of(1, 20);

        list = new ArrayList<>();
    }

    @Test
    void selectNoticeList() {
        list.add(noticeEntity);
        Page<NoticeEntity> result = new PageImpl<>(list);
        when(noticeService.selectNoticeDTOList(CATEGORY, SEARCHTEXT, pageable)).thenReturn(result);
        assertEquals(result.getTotalElements(), noticeController.selectNoticeList(CATEGORY, SEARCHTEXT, 1, 20).getTotalElements());
    }

    @Test
    void selectNoticeDTO() {
        when(noticeService.selectNoticeDTO(id)).thenReturn(noticeEntity);
        assertEquals(noticeController.selectNoticeDTO(id).getTitle(), noticeEntity.getTitle());
    }

    @Test
    void updateNoticeDTO() {
        when(noticeService.updateNoticeDTO(id, UPDATE_TITLE, UPDATE_CONTENTS, UPDATE_ACCOUNT)).thenReturn(updateNoticeEntity);
        assertEquals(noticeController.updateNoticeDTO(id, UPDATE_TITLE, UPDATE_CONTENTS, UPDATE_ACCOUNT).getTitle(), updateNoticeEntity.getTitle());
    }

    @Test
    void insertNoticeDTO() {
        assertEquals(noticeEntity.getContent(), noticeEntity.getContent());
    }

    @Test
    void deleteNotice() {
        when(noticeService.deleteNoticeDTO(1)).thenReturn(true);
        assertTrue(noticeController.deleteNotice(1));
    }
}
