/* drop table if exists COM_CODE; */

create table if not exists COM_CODE (
        cd_id       SMALLINT primary key ,
        up_cd_id    SMALLINT,
        cd_val      VARCHAR(255),
        cd_name     VARCHAR(255) NOT NULL,
        cd_eng_name VARCHAR(255),
        cd_descr    VARCHAR(255),
        use_yn      TINYINT(1) NOT NULL default 1,
        ordno       SMALLINT,
        register_id VARCHAR(255),
        modifier_id VARCHAR(255),
        reg_dtm     TIMESTAMP,
        upd_dtm     TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        open_yn     TINYINT(1) NOT NULL default 1,
        standard_spec VARCHAR(255),
        ext_val     VARCHAR(255),
        ext_val2    VARCHAR(255)
);
