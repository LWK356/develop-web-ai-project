package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;//编码：1成功，0失败
    private String msg;//
    private Object data;//返回数据


    //想给前端返回无参的，用下面的这个
    public static Result success(){
        Result result = new Result();
        result.code=1;
        result.msg="success";
        return result;
    }

    //想给前端返回有数据的，用下面的这个

    public static Result success(Object object){
        Result result = new Result();
        result.code=1;
        result.msg="success";
        result.data=object;
        return result;
    }

    public static Result error(String msg){
        Result result = new Result();
        result.code=0;
        result.msg=msg;
        return result;
    }
}
