package com.xmb.auth.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Ben
 * @date 2020-05-08
 * @desc
 */
@Data
public class SysUserLoginDto {

    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("密码")
    private String password;
}
