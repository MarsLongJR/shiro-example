package com.github.MarsLongJR.shiro.chapter2.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public class MyRealm1 implements Realm {
    public String getName() {
        return "myrealm1";
    }

    public boolean supports(AuthenticationToken token) {
        //仅支持UsernamePasswordToken类型的Token
        return token instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获得name
        String name= (String) token.getPrincipal();
        //获得用户密码
        String password= new String((char[])token.getCredentials());
        //如果用户名和realm里用户名不相同
        if(!"zhang".equals(name)){
            throw new UnknownAccountException();
        }
        //如果用户名和realm里密码不同
        if(!"123".equals(password)){
            throw new IncorrectCredentialsException();
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(name,password,this.getName());
    }
}
