# springboot-jpa-notice-tdd-sample
springboot 기본 셋팅 (JPA), 게시판 JPA Sample, TDD 적용

  - springboot version : 2.71.
  - java version : 1.8
  - swaager 적용
  
  
  
notice create sql
  create table tbl_notice
(
    id           int(11) unsigned auto_increment,
    company_cdoe varchar(4)   default '1000' null,
    title        varchar(255)                not null,
    content      longtext                    not null,
    hit          int unsigned default 0      not null,
    `delete`     tinyint(1)   default 0      not null,
    tenant_code  int unsigned                not null,
    visible      tinyint(1)   default 0      null,
    file_id      varchar(255)                null,
    creator_id   varchar(100)                not null,
    created      datetime                    not null,
    updater_id   varchar(255)                null,
    updated      datetime                    null,
    constraint tbl_notice_id_uindex
        unique (id)
)
    collate = utf8_bin;

alter table tbl_notice
    add primary key (id);

