package com.ykcho.base.notice.repository;

import com.ykcho.base.notice.dto.NoticeDTO;
import com.ykcho.base.notice.entity.NoticeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // JPA 테스트를 할 수 있도록 해당 기능들만 들고 오는 기능이며 실제 save등의 작동을 해도 다시 지워지기 떄문에 다른 작업을 해주지 않아도 됨.
@AutoConfigureTestDatabase(replace = Replace.NONE) // 위에 거랑 쌍으로 쓰임. (datasource 관련)
class NoticeRepositoryTest {
    @Autowired
    private NoticeRepository noticeRepository;

    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
    }

    @Test
    void findById() {
        List<NoticeEntity> noticeList =  noticeRepository.findAll();
        assertNotNull(noticeList);
        if(noticeList.size() > 0) {
            Optional<NoticeEntity> noticeDTO = noticeRepository.findById(noticeList.get(0).getId());
            assertEquals(noticeList.get(0).getId(), noticeDTO.orElseThrow(NoSuchElementException::new).getId());
        }
    }

    @Test
    void findByAll() {
        assertNotNull(noticeRepository.findAll());
    }

    @Test
    void save() {
        NoticeDTO noticeDTO = NoticeDTO.builder().companyCode("1000")
                .title("testTile").content("TestContent").hit(0)
                .visible(false).tenantCode(1).creatorId("testUser").created(new Date())
                .build();

        NoticeEntity noticeEntitySaved = noticeRepository.save(modelMapper.map(noticeDTO, NoticeEntity.class));
        assertEquals(noticeEntitySaved.getId(), noticeEntitySaved.getId());
    }

    @Test
    void delete() {
        NoticeDTO noticeDTO = NoticeDTO.builder().companyCode("1000")
                .title("testTile").content("TestContent").hit(0)
                .visible(false).tenantCode(1).creatorId("testUser").created(new Date())
                .build();
        NoticeEntity noticeEntity = noticeRepository.save(modelMapper.map(noticeDTO, NoticeEntity.class));
        noticeRepository.deleteById(noticeEntity.getId());

        Optional<NoticeEntity> deleteResult = noticeRepository.findById(noticeEntity.getId());

        deleteResult.ifPresent(notice -> assertNotEquals(noticeEntity.getId(), notice.getId()));
    }

//    Mockito 사용 TEST 방법인데 상황에 맞게 사용을 해야할 것 같다.
//    private NoticeRepository noticeRepository;
//    @BeforeEach
//    void setUp() {
//        noticeRepository = Mockito.mock(NoticeRepository.class);
//    }
//
//    @Test
//    void findById() {
//        NoticeDTO noticeDTO = new NoticeDTO();
//        noticeDTO.setContent("contents");
//        when(noticeRepository.findById(1)).thenReturn(noticeDTO);
//        assertEquals(noticeRepository.findById(1), noticeRepository.findById(1));
//    }
}
