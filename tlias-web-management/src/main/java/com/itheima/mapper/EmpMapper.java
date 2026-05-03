package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {

    /*
    * 查询
    * */

    @Select("select count(*) from emp left outer join dept on emp.dept_id=dept.id")
//       @Select("count(*) from emp left outer join dept on emp.dept_id=dept.id;") //分号可以要，可以不写
    public Long count();


    /*
    * 分页查询
    * */
    @Select("select emp.*,dept.name deptName from emp left outer join dept on emp.dept_id=dept.id " +
            "order by update_time desc limit #{start},#{pageSize};")
    public List<Emp> findAll(Integer start,Integer pageSize);

    //分页查询：如果public List<Emp> findAll(Integer start,Integer pageSize);传递有多个参数，直接写参数名字，不用去加
//    para注解，因为基于springboot框架开发的项目参数名会保留。


}
