package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/emps")  //因为下面的公共所有返回结果都有emps，所以写在了最上面
@RestController
public class EmpController {


    @Autowired
    private EmpService empService;
    /*
    * 分页查询
    * */

//    增删改查的返回结果都是Result


//    @RequestParam是默认参数，相当于与if(page==null) page = 1;
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
     @RequestParam (defaultValue = "12") Integer pageSize){
        log.info("分页查询，参数：{}，{}",page,pageSize);
        PageResult<Emp> pageResult = empService.page(page,pageSize);
        return Result.success(pageResult);


    }
}
