package com.zr.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zr.pojo.PageBean;
import com.zr.pojo.ReporstsPage;
import com.zr.pojo.Reports;
import com.zr.service.ReportsService;
import com.zr.mapper.ReportsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author 曾瑞
* @description 针对表【reports】的数据库操作Service实现
* @createDate 2023-04-03 12:36:28
*/
@Service
public class ReportsServiceImpl
    implements ReportsService{
    @Autowired
    private ReportsMapper reportsMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, Integer type, LocalDate begin, LocalDate end) {
        PageHelper.startPage(page,pageSize);

        List<ReporstsPage> reports =reportsMapper.selectAllReports(type,begin,end);

        Page<ReporstsPage> page1= (Page<ReporstsPage>) reports;
        return new PageBean(page1.getTotal(),page1.getResult());
    }

    @Transactional
    @Override
    public void deleteReportsByReporter(Integer userId) {
        reportsMapper.deleteByReporter(userId);
    }

    @Override
    public void addReport(Reports report) {
        LocalDateTime createtime=LocalDateTime.now();
        report.setCreatetime(createtime);
        reportsMapper.insertReport(report);
    }

    @Override
    public void deleteReportsByid(Integer id) {
        reportsMapper.deleteReportById(id);
    }


}




