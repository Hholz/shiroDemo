package com.hyp.test;

import static org.junit.Assert.*;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class LoginLogoutTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testHelloword() {
		// 1.获取SecurityManager工厂，此处使用ini配置文件初始化SecuriyManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		// 2.得到SecurityManager实例 并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3、得到 Subject 及创建用户名/密码身份验证 Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		try {
			// 4、登录，即身份验证
			subject.login(token);
			System.out.println("登录，即身份验证");
		} catch (AuthenticationException e) {
			// 5、身份验证失败
			System.out.println("身份验证失败");
		}
		Assert.assertEquals(true, subject.isAuthenticated()); // 断言用户已经登录
		// 6、退出
		subject.logout();
	}

}
