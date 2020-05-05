package com.xmb.auth.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Ben
 * @date 2020-05-05
 * @desc
 */
@Data
public class CheckoutPasswordDTO {

    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("密码")
    private String password;
}
