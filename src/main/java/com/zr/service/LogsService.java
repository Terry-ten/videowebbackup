package com.zr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zr.pojo.Logs;
import com.zr.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * @author zr
 * @date 2023/4/18 21:33
 */
public interface LogsService{
    PageBean page(Integer page, Integer pageSize, Integer operateid, LocalDate begin, LocalDate end);

    void deleteLogs(List<Integer> ids);
}
