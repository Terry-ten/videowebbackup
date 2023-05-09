package com.zr.service;

import com.zr.pojo.PageBean;
import com.zr.pojo.Reports;

import java.time.LocalDate;

/**
* @author 曾瑞
* @description 针对表【reports】的数据库操作Service
* @createDate 2023-04-03 12:36:28
*/
public interface ReportsService {

    PageBean page(Integer page, Integer pageSize, Integer type, LocalDate begin, LocalDate end);

    void deleteReportsByReporter(Integer userId);



    void addReport(Reports report);

    void deleteReportsByid(Integer id);
}
