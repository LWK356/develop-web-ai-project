package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController//@Controller+@ResponseBody(直接将方法的返回值直接作为响应数据响应给前端\
//如果是一个对象或者一个集合先转成json，转成json后再响应给前端 ）

public class DeptController {

    @Autowired
    private DeptService deptService;

//    @RequestMapping(value = "depts", method=RequestMethod.GET)




    @GetMapping //等于上面的 @RequestMapping
    public Result list(){
//        System.out.println("查询全部部门的数据");
        log.info("查询全部部门数据");
        List<Dept> deptlist = deptService.findAll();
        return Result.success(deptlist);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询部门id为{}的数据",id);
        Dept dept = deptService.getById(id);
        if (dept == null) {
            return Result.error("部门不存在");
        }
        return Result.success(dept);
    }

    /*
    * 删除
    * */

//     方式一：通过原始的HttpServletRequest对象获取请求参数
//     繁琐，在真实的企业中用得不多
//    @DeleteMapping("depts")
//    public Result delete(Integer id){
//        System.out.println("删除部门id为："+id);
//        deptService.deleteById(id);
//        return Result.success();
//    }

    //方式二：通过Spring提供的@RequestParam注解获取请求参数
    //自动类型转换
    //注意事项：一旦声明了@RequestParam("id")注解，该参数必须传递，如果不传递将会报错，
    // (@RequestParam有属性required，默认为true)
//    @DeleteMapping("depts")
//    //@RequestParam("id")会接受前端请求的id参数，然后将id的值赋给id变量
//    public Result delete(@RequestParam(value="id",required = false) Integer id){//required = false就可以不传递参数
//        System.out.println("删除部门id为："+id);
//        deptService.deleteById(id);
//        return Result.success();
//    }

    //删除方式三：省略@RequestParam（前端传递的请求参数名与服务端形参名称一致）[推荐]

    @DeleteMapping
    //@RequestParam("id")会接受前端请求的id参数，然后将id的值赋给id变量
    public Result delete(Integer id){//required = false就可以不传递参数
//        System.out.println("删除部门id为："+id);
        log.info("删除部门id为：{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    /*
    * 添加
    * */

    //添加
    // @RequestBody：将请求体中的json数据转换成Java对象【请求体中的数据必须是json格式的】
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("添加部门:{}",dept);
        if (dept == null || dept.getName() == null || dept.getName().isBlank()) {
            return Result.error("部门名称不能为空");
        }
        if (dept.getName().length() > 10) {
            return Result.error("部门名称长度不能超过10");
        }
        System.out.println("添加部门");
        try {
            deptService.add(dept);
            return Result.success();
        } catch (DuplicateKeyException e) {
            return Result.error("部门名称已存在");
        }
    }


    /*
    * 根据id修改部门数据
    * */
    @PutMapping
    //@RequestBody使返回的对象是json格式
    public Result update(@RequestBody Dept dept){
//        System.out.println("修改部门"+dept);
        log.info("修改部门{}",dept);
        deptService.update(dept);
        return Result.success();
    }

}
