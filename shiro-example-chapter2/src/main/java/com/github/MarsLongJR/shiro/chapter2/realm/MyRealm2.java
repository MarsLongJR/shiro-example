package com.github.MarsLongJR.shiro.chapter2.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public class MyRealm2 implements Realm {
    public String getName() {
        return "MyRealm2";
    }

    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
            String name= (String) token.getPrincipal();
            String password=new String((char [])token.getCredentials());
            if(!"wang".equals(name)){
                throw new UnknownAccountException();
            }
            if(!"123".equals(password)){
                throw new IncorrectCredentialsException();
            }
            //如果身份认证验证成功，返回一个AuthenticationInfo实现；
            return new SimpleAuthenticationInfo(name,password,this.getName());
    }
}