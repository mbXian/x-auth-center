package com.xmb.auth.auth.dto;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户Token,Reids保存用户token信息，实时刷新时间
 * @author 李超
 * @version 1.0
 * Created in 2019-07-29 0:17
 */
@Data
public class SysUserTokenDto implements Serializable {
    private static final long serialVersionUID = 570584849278826888L;

    private Long userId;
    private String token;
    private Date expireTime;

}
