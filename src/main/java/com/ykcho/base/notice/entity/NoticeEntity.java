package com.ykcho.base.notice.entity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "tbl_notice")
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String companyCode;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @Column(name = "tenant_code")
    private int tenantCode;
    private int hit;
    private boolean visible;
    @Column(name = "file_id")
    private String fileId;
    @Transient
    private MultipartFile file;
    private String creatorId;
    private Date created;
    private String updaterId;
    private Date updated;
    @Transient
    private String fileCreated;
}
