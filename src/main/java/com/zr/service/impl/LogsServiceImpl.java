package com.zr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zr.service.LogsService;
import com.zr.pojo.Logs;
import com.zr.pojo.PageBean;
import com.zr.mapper.LogsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
* @author 曾瑞
* @description 针对表【logs】的数据库操作Service实现
* @createDate 2023-04-15 10:44:10
*/
@Slf4j
@Service
public class LogsServiceImpl
    implements LogsService {
    @Autowired
    private LogsMapper logsMapper;


    @Override
    public PageBean page(Integer page, Integer pageSize, Integer operateid, LocalDate begin, LocalDate end) {
        log.info("opreateid"+operateid);
        PageHelper.startPage(page,pageSize);
        List<Logs> logs=logsMapper.selectPageLogs(operateid,begin,end);
        Page<Logs> logs1= (Page<Logs>) logs;
        return new PageBean(logs1.getTotal(),logs1.getResult());
    }
}




