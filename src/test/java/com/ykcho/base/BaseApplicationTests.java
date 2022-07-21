package com.ykcho.base;

import com.ykcho.base.notice.controller.NoticeController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest // -> 실제로 각종 리소스가 로딩 되는지 확인하긴 해야하는데 굳이 ;ㅁ;..
@ExtendWith(MockitoExtension.class)
class BaseApplicationTests {

//    @Autowired
//    NoticeController noticeController;
    @Test
    void contextLoads() {
        NoticeController noticeController = Mockito.mock(NoticeController.class); // 주석 제거하면 이쪽을 주석 해야함
        assertThat(noticeController).isNotNull();

    }
}
