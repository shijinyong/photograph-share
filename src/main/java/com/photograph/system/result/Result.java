package com.photograph.system.result;

import lombok.Data;

/**
 * @Author: shijinyong
 * @Date: 2023/2/20 18:13
 * @Description: 统一返回结果集
 */
@Data
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

    public static <T> Result<T> build(T data){
        Result<T> result = new Result<>();
        if(data != null) {
            result.setData(data);
        }
        return result;
    }

    public static <T> Result<T> build(T data,ResultEnum resultEnum){
        Result<T> result = build(data);
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        return result;
    }

    public static<T> Result<T> ok(){
        return Result.ok(null);
    }


    public static<T> Result<T> ok(T data){
        Result<T> result = build(data);
        return build(data, ResultEnum.SUCCESS);
    }

    public static<T> Result<T> fail(){
        return Result.fail(null);
    }


    public static<T> Result<T> fail(T data){
        Result<T> result = build(data);
        return build(data, ResultEnum.FAIL);
    }

    public static<T> Result<T> fail(ResultEnum resultEnum){
        Result<T> result = new Result<>();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        return result;
    }



    public Result<T> code(Integer code){
        this.code = code;
        return this;
    }

    public Result<T> message(String message){
        this.message = message;
        return this;
    }

}
