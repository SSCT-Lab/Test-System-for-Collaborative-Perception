package com.nineya.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nineya.springboot.common.R;
import com.nineya.springboot.entity.Report;
import com.nineya.springboot.mapper.ReportMapper;
import com.nineya.springboot.service.ReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cw
 * @since 2023-05-07
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {

    @Autowired
    private ReportMapper reportMapper;
    @Override
    public R getAll() {
        QueryWrapper<Report> queryWrapper = new QueryWrapper<>();
        List<Report> ReportList = reportMapper.selectList(queryWrapper);
        return R.success(null, ReportList);
    }

    @Override
    public R insertReport(Report report) {
        try {
            if (reportMapper.insert(report) > 0) {
                return R.success("插入成功");
            } else {
                return R.error("插入失败");
            }
        } catch (DuplicateKeyException e) {
            return R.fatal(e.getMessage());
        }
    }

    @Override
    public R getById(int id) {
        QueryWrapper<Report> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("ID", id);
        List<Report> reportList = reportMapper.selectList(queryWrapper);
        return R.success(null, reportList);
    }
}

