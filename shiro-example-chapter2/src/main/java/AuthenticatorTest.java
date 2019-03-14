import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public class AuthenticatorTest {

    public void login(String configFile){
       Factory<SecurityManager> factory=new IniSecurityManagerFactory(configFile);
       SecurityManager securityManager=factory.getInstance();
       SecurityUtils.setSecurityManager(securityManager);
       Subject subject=SecurityUtils.getSubject();
       UsernamePasswordToken token=new UsernamePasswordToken("zhang","123");
       subject.login(token);
   }

   @Test
    public void testAllSuccessfulStrategyWithSuccess(){
        login("classpath:shiro-authenticator-all-success.ini");
        Subject subject=SecurityUtils.getSubject();

       PrincipalCollection principalCollection=subject.getPrincipals();
       Assert.assertEquals(2,principalCollection.asList().size());
   }
}
