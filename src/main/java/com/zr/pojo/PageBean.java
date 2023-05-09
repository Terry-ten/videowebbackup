package com.zr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zr
 * @date 2023/4/4 21:52
 * 分页查询的封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {
        private Long total;
        private List rows;
}
