import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.security.Security;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public class LoginLoutTest {
    // 登录/退出简单默认测试
    @Test
    public void testHelloWorld(){

        //获得sem的工厂，并且用shiro.ini来初始化
        Factory<org.apache.shiro.mgt.SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager=factory.getInstance();
        //得到sem实例，并绑定给su
        SecurityUtils.setSecurityManager(securityManager);
        //得到主体，并且创建用户名和身份验证凭证
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("zhang","123");

        try {
            //登录，用户验证
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        //断言用户已经登录
        Assert.assertEquals(true,subject.isAuthenticated());
        //退出
        subject.logout();

    }
    //单数据源测试
    @Test
    public void singleRealm(){
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        SecurityManager securityManager=factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken("zhang","123");

        try {
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(true,subject.isAuthenticated());
        subject.logout();
    }

}
