package com.zr.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zr
 * @date 2023/4/18 23:44
 */

@Data
public class DataBases {
    private int id;
    private String tablename;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatetime;
    private String url;
}
