package com.photograph.system.vo;

import lombok.Data;

/**
 * @Author: shijinyong
 * @Date: 2023/2/20 17:41
 * @Description:
 */
@Data
public class UserLogin {

    private String loginName;

    private String loginPassword;

    private String isRemPassword;

    private String verify;
}
