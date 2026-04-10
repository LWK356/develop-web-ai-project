package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//@Controller+@ResponseBody(直接将方法的返回值直接作为响应数据响应给前端\
//如果是一个对象或者一个集合先转成json，转成json后再响应给前端 ）

public class DeptController {

    @Autowired
    private DeptService deptService;

//    @RequestMapping(value = "depts", method=RequestMethod.GET)




    @GetMapping("depts") //等于上面的 @RequestMapping
    public Result list(){
        System.out.println("查询全部部门的数据");
        List<Dept> deptlist = deptService.findAll();
        return Result.success(deptlist);
    }

    /*
    * 删除
    * */

//     方式一：通过原始的HttpServletRequest对象获取请求参数
//     繁琐，在真是的企业中用得不多
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

    @DeleteMapping("depts")
    //@RequestParam("id")会接受前端请求的id参数，然后将id的值赋给id变量
    public Result delete(Integer id){//required = false就可以不传递参数
        System.out.println("删除部门id为："+id);
        deptService.deleteById(id);
        return Result.success();
    }

    /*
    * 添加
    * */

    //添加
    // @RequestBody：将请求体中的json数据转换成Java对象【请求体中的数据必须是json格式的】
    @PostMapping("depts")
    public Result add(@RequestBody Dept dept){
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
    @PutMapping("depts")
    public Result update(@RequestBody Dept dept){
        System.out.println("修改部门");
        deptService.update(dept);
        return Result.success();
    }

}
