package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;

/*
* page 页码
* pageSize 每页展示的数量*/
public interface EmpService {
    PageResult<Emp> page(Integer page, Integer pageSize);
}
