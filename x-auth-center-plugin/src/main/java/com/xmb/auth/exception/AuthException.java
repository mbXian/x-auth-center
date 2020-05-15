package com.xmb.auth.exception;

import com.xmb.common.exception.BizException;

import java.io.Serializable;

/**
 * @author 李超
 * @version 1.0
 * Created in 2019-07-29 22:39
 */
public class AuthException extends BizException implements Serializable {
    private static final long serialVersionUID = -2929839126999847816L;

    public AuthException(int code, String msg) {
        super(code, msg);
    }



    public static final AuthException APPLICATION_CREATE_RSA_EXCEPTION = new AuthException(10000002,"应用{0}密钥生成失败!");

    public static final AuthException APPLICATION_NOT_FOUND_EXCEPTION = new AuthException(10000003,"应用{0}未找到!");

    public static final AuthException APPLICATION_SAVE_EXCEPTION = new AuthException(10000004,"应用{0}保存失败!");


    public static final AuthException TOKEN_OUT_TIME = new AuthException(10000001, "token失效，请重新登录");
    public static final AuthException SYSUSER_NOT_FOUND = new AuthException(10000002, "用户不存在!");
    public static final AuthException PASSWORD_ERROR = new AuthException(10000003, "密码不正确!");
    public static final AuthException TOKEN_GENERATOR_ERROR = new AuthException(10000004, "生成Token失败!");
    public static final AuthException ACCOUNT_FROZEN = new AuthException(10000005, "账号已被锁定,请联系管理员");
    public static final AuthException ERROR_UNKNOW = new AuthException(10000006, "未知错误");

    public static final AuthException UN_AUTHORIZED_EXCEPTION = new AuthException(10000007,"权限不足");

    public static final AuthException INCONSISTENT_PASSWORD_EXCEPTION = new AuthException(10000008,"密码不一致");

    public static final AuthException WITHOUT_DEPTID_EXCEPTION = new AuthException(10000009,"部门id不能为空!");

    public static final AuthException WITHOUT_APPLICATIONID_EXCEPTION = new AuthException(100000010,"应用id不能为空!");

    public static final AuthException WITHOUT_CREATORID_EXCEPTION = new AuthException(100000011,"创建人id不能为空!");

    public static final AuthException WITHOUT_USERID_EXCEPTION = new AuthException(100000012,"创建人id不能为空!");
    public static final AuthException SAVE_APPCODE_ERROR = new AuthException(100000011,"CODE已存在");

    public static final AuthException EXCEED_PERMISSION_EXCEPTION = new AuthException(100000013,"新增角色的权限，已超出你的权限范围!");

    public static final AuthException WITHOUT_ROLEID_EXCEPTION = new AuthException(100000013,"角色id不能为空!");
    public static final AuthException WITHOUT_APPCODE_EXCEPTION = new AuthException(100000012,"appCode不能为空!");

    public static final AuthException ERROR_EDIT_EXCEPTION = new AuthException(100000099,"操作失败!");

    public static final AuthException CANNOT_FINDROLE_EXCEPTION = new AuthException(1000000100,"根据id找不到角色!");

    public static final AuthException WITHOUT_ISDEL_EXCEPTION = new AuthException(100000015,"isDel can not be null!");

    public static final AuthException WITHOUT_TYPE_EXCEPTION = new AuthException(100000016,"非法的type!");

    public static final AuthException WITHOUT_ID_EXCEPTION = new AuthException(100000016,"id不能为空!");

    public static final AuthException HAVE_SUBDEPT_EXCEPTION = new AuthException(1000000101,"该部门下有子部门，不能删除!");

    public static final AuthException CANNOT_FINDDEPT_EXCEPTION = new AuthException(1000000102,"根据id找不到部门!");

    public static final AuthException HAVE_CATALOG_MENU_OR_BUTTON_EXCEPTION = new AuthException(1000000103,"请先删除该节点下的目录或菜单或按钮!");

    public static final AuthException WITHOUT_UUID_EXCEPTION = new AuthException(1000000104,"uuid不能为空!");

    public static final AuthException ROLE_ALREADY_EXIST = new AuthException(1000000106,"角色CODE已存在!");
    public static final AuthException SUPER_ADMIN_CANOT_BIZ = new AuthException(1000000107,"超级管理员禁止登陆业务系统!");
    public static final AuthException DEPT_PARENT_SAME = new AuthException(1000000108,"部门父级相同!");
    public static final AuthException CHECK_USER_ERROR = new AuthException(1000000109,"用户添加失败:{0}!");
    public static final AuthException DEL_DEPT_HAVE_USER_EXCEPTION = new AuthException(1000000110,"该部门有用户存在!");
    public static final AuthException CANNOT_SET_MORE_ROOT_DEPT = new AuthException(1000000111,"不可选择多个一级部门!");
    public static final AuthException ROLE_NOT_FOUND = new AuthException(1000000112,"角色不存在!");
    public static final AuthException ROLE_NOT_PERM = new AuthException(1000000113,"权限不足!");
}
