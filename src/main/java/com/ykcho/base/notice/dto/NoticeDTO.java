package com.ykcho.base.notice.dto;

import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class NoticeDTO {
    @ApiModelProperty(value = "공지 ID", notes = "공지 ID", example = "1", hidden = true)
    private int id;

    @ApiModelProperty(value = "회사코드", hidden = true)
    private String companyCode;

    @NotBlank
    @ApiModelProperty(value = "제목", notes = "제목", required = true, example = "Test Title")
    @Size(min =1, max = 255, message = "제목은 255 글자 입니다.")
    private String title;

    @NotBlank
    @ApiModelProperty(value = "내용", notes = "내용", required = true, example = "Insert Contents...")
    private String content;

    @ApiModelProperty(value = "category", notes = "category", required = true, example = "3")
    private int tenantCode;

    @ApiModelProperty(value = "조회수", notes = "조회수", example = "1", hidden = true)
    private int hit;

    @ApiModelProperty(value = "게시여부", notes = "게시여부", example = "0", hidden = true)
    private boolean visible;

    @Ignore
    @ApiModelProperty(value = "파일아이디", notes = "파일아이디", example = "5200cd20-ebe4-4659-8b65-ecd51ad1e1c6")
    private String fileId;

    @Ignore
    @Transient
    @ApiModelProperty(value = "파일", notes = "파일", example = "file", hidden = true)
    private MultipartFile file;

    @ApiModelProperty(value = "등록아이디", notes = "등록아이디", example = "admin", hidden = true)
    private String creatorId;

    @ApiModelProperty(value = "등록일시", notes = "등록일시", example = "2020-12-09", hidden = true)
    private Date created;

    @ApiModelProperty(value = "수정아이디", notes = "수정아이디", example = "admin", hidden = true)
    private String updaterId;

    @ApiModelProperty(value = "수정일", notes = "수정일", example = "2020-12-09", hidden = true)
    private Date updated;

    private String fileCreated;
}
