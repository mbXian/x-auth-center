package com.xmb.auth.auth.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.xmb.auth.auth.service.SysRoleService;

/**
 * 角色表
 *
 * @author Ben
 * @email 
 * @date 2020-05-04 19:46:47
 */
@Slf4j
@Api(description = "角色模块")
@RestController
@RequestMapping("auth/sysrole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

}
