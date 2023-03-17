package com.photograph.system.vo;

import lombok.Data;

/**
 * @Author: shijinyong
 * @Date: 2023/2/22 14:40
 * @Description:
 */
@Data
public class UserRegister {
    private String registerName;

    private String registerPassword;

    private String registerVerifyPassword;

    private String verify;
}
