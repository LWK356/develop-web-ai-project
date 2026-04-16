package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {


    List<Dept> findAll();

    Dept getById(Integer id);

    void deleteById(Integer id);

    void add(Dept dept);

    void update(Dept dept);
}
