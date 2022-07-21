package com.ykcho.base.notice.service;

import com.ykcho.base.notice.dto.NoticeDTO;
import com.ykcho.base.notice.entity.NoticeEntity;
import com.ykcho.base.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RequiredArgsConstructor
class NoticeServiceTest {

    private NoticeService noticeService;
    @Mock
    private NoticeRepository noticeRepository;

    Page noticeEntities = mock(Page.class);

    @Mock
    Pageable pageable = mock(Pageable.class);

    private final String RESULT = "SUCCESS";


    @BeforeEach
    void setup() {
        noticeRepository = Mockito.mock(NoticeRepository.class);
        noticeService = new NoticeService(noticeRepository);
    }

    @Test
    void selectNoticeList() {
        when(noticeRepository.findByTitleOrderById("test", pageable)).thenReturn(noticeEntities);
        when(noticeRepository.findByCreatorIdOrderById("test", pageable)).thenReturn(noticeEntities);
        when(noticeRepository.findAll(pageable)).thenReturn(noticeEntities);

        assertNotNull(noticeService.selectNoticeDTOList("TITLE", "test", pageable));
        assertNotNull(noticeService.selectNoticeDTOList("CREATOR", "test", pageable));
        assertNotNull(noticeService.selectNoticeDTOList("ALL", "test", pageable));
    }

    @Test
    void selectNoticeDTO() {
        Optional<NoticeEntity> noticeEntity = Optional.of(new NoticeEntity());
        noticeEntity.ifPresent(notice -> {
            notice.setTitle("SUCCESS");
            notice.setId(9999999);
        });
        when(noticeRepository.findById(9999999)).thenReturn(noticeEntity);
        assertEquals(noticeService.selectNoticeDTO(noticeEntity.get().getId()).getTitle(), noticeEntity.get().getTitle());
    }

    @Test
    void updateNoticeDTO() {
        NoticeDTO noticeDTO = NoticeDTO.builder().companyCode("1000")
                .title("testTile").content("TestContent").hit(0)
                .visible(false).tenantCode(1).creatorId("testUser").created(new Date())
                .build();

        ModelMapper modelMapper = new ModelMapper();
        NoticeEntity noticeEntity = modelMapper.map(noticeDTO, NoticeEntity.class);

        when(noticeRepository.findById(1)).thenReturn(Optional.ofNullable(noticeEntity));
        assert noticeEntity != null;
        when(noticeRepository.save(noticeEntity)).thenReturn(noticeEntity);

        assertEquals(noticeEntity.getId(), noticeService.updateNoticeDTO(1, RESULT, "Test contents", "testUser").getId());
    }
    @Test
    void insertNotice() throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        NoticeDTO noticeDTO = NoticeDTO.builder().companyCode("1000")
                .title(RESULT).content("TestContent").hit(0)
                .visible(false).tenantCode(1).creatorId("testUser").created(new Date())
                .build();
        NoticeEntity noticeEntity = modelMapper.map(noticeDTO, NoticeEntity.class);
        when(noticeRepository.save(noticeEntity)).thenReturn(noticeEntity);
        assertEquals(noticeEntity.getTitle(), noticeDTO.getTitle());
    }

    @Test
    void deleteNotice() {
        Optional<NoticeEntity> noticeEntity = Optional.of(new NoticeEntity());
        noticeEntity.ifPresent(notice -> {
            notice.setTitle(RESULT);
            notice.setId(1);
        });
        when(noticeRepository.findById(1)).thenReturn(noticeEntity);
        assertTrue(noticeService.deleteNoticeDTO(1));
    }
}
