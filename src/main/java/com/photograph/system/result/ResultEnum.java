package com.photograph.system.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: shijinyong
 * @Date: 2023/2/20 20:09
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(200,"成功"),
    FAIL(201,"失败"),
    ACCOUNT_ERROR(202,"用户名或密码错误"),
    VERIFRCODE_ERROR(203,"验证码错误"),
    USERNAME_REPEAT(204,"用户名已存在"),
    PASSWORD_DIFFERENT(205,"密码不一致");


    private Integer code;

    private String message;

}
