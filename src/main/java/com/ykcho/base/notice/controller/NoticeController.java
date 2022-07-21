package com.ykcho.base.notice.controller;

import com.ykcho.base.notice.dto.NoticeDTO;
import com.ykcho.base.notice.entity.NoticeEntity;
import com.ykcho.base.notice.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@Api(value = "Notice API")
@RequestMapping(value = "/api/v1/notices")
public class NoticeController {

    private final NoticeService noticeService;

    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    @ApiOperation(value = "공지 리스트", notes = "공지 리스트를 가져온다.")
    public Page<NoticeEntity> selectNoticeList(@RequestParam String category, @RequestParam(required = false) String searchText, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return noticeService.selectNoticeDTOList(category, searchText, pageable);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "공지 상세", notes = "공지 상세를 가져온다.")
    public NoticeEntity selectNoticeDTO(@PathVariable int id) {
        return noticeService.selectNoticeDTO(id);
    }

    @PutMapping("/{id}/{title}/{contents}/{updater}")
    @ApiOperation(value = "공지 수정", notes = "공지를 수정한다.")
    public NoticeEntity updateNoticeDTO(@PathVariable int id, @PathVariable String title, @PathVariable String contents, @PathVariable String updater) {
        return noticeService.updateNoticeDTO(id, title, contents, updater);
    }

    @PostMapping
    @ApiOperation(value = "공지 등록", notes = "공지를 등록한다.")
    public NoticeDTO insertNoticeDTO(@Valid @RequestBody NoticeDTO noticeDTO) {
        return modelMapper.map(noticeService.insertNoticeDTO(modelMapper.map(noticeDTO, NoticeEntity.class)), NoticeDTO.class);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "공지 삭제", notes = "공지를 삭제한다.")
    public boolean deleteNotice(@PathVariable int id) {
        return noticeService.deleteNoticeDTO(id);
    }
}
