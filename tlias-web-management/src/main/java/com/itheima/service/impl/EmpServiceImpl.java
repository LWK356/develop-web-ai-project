package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //1.调用mapper接口，获取总记录数

        long total = empMapper.count();


        //2.调用mapper接口，获取分页查询结果

        Integer start = (page-1)*pageSize;
        List<Emp> rows=empMapper.findAll(start, pageSize);

        PageResult<Emp> pageResult = new PageResult<>();

        return new PageResult<Emp>(total,rows);
    }
}
