package com.hyp.test;

import static org.junit.Assert.*;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;

public class ShiroMySqlTest {

	@Before
	public void setUp() throws Exception {
		// 1.��ȡSecurityManager�������˴�ʹ��ini�����ļ���ʼ��SecurityManager  
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm-2.ini");  
        // 2.��ȡSecurityManagerʵ�������󶨵�SecurityUtils  
        org.apache.shiro.mgt.SecurityManager sm = factory.getInstance();  
        SecurityUtils.setSecurityManager(sm); 
	}

	@Test  
    public void test() {  
        // 3.�õ�Subject  
        Subject subject = SecurityUtils.getSubject();  
        // 4.�����û���¼ƾ֤  
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "1234");  
        // 5.��¼�������¼ʧ�ܻ��׳���ͬ���쳣�������쳣���ʧ��ԭ��  
        try {  
            subject.login(token);  
            // 6.�ж��Ƿ�ɹ���¼  
            assertEquals(true, subject.isAuthenticated());  
            System.out.println("��¼�ɹ�����");  
            // 7.ע���û�  
            subject.logout();  
        } catch (IncorrectCredentialsException e) {  
            System.out.println("��¼�������. Password for account " + token.getPrincipal() + " was incorrect.");  
        } catch (ExcessiveAttemptsException e) {  
            System.out.println("��¼ʧ�ܴ�������");  
        } catch (LockedAccountException e) {  
            System.out.println("�ʺ��ѱ�����. The account for username " + token.getPrincipal() + " was locked.");  
        } catch (DisabledAccountException e) {  
            System.out.println("�ʺ��ѱ�����. The account for username " + token.getPrincipal() + " was disabled.");  
        } catch (ExpiredCredentialsException e) {  
            System.out.println("�ʺ��ѹ���. the account for username " + token.getPrincipal() + "  was expired.");  
        } catch (UnknownAccountException e) {  
            System.out.println("�ʺŲ�����. There is no user with username of " + token.getPrincipal());  
        }  
    }  
	
	@Test  
    public void test2() {  
        // 3.�õ�Subject  
        Subject subject = SecurityUtils.getSubject();  
        // 4.�����û���¼ƾ֤  
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "1234");  
        token.setRememberMe(true);  
        // 5.��¼�������¼ʧ�ܻ��׳���ͬ���쳣�������쳣���ʧ��ԭ��  
        try {  
            subject.login(token);  
            // 6.�ж��Ƿ�ɹ���¼  
            assertEquals(true, subject.isAuthenticated());  
            System.out.println("��¼�ɹ�����");  
            // �ж��û��Ƿ�ӵ��ĳ����ɫ  
            assertEquals(true, subject.hasRole("admin"));  
            // ʹ��Shiro�Դ��Ķ����ж��û��Ƿ��б���Ȩ  
            subject.checkRole("manager");  
            subject.checkPermission("create_user1");  
            // 7.ע���û�  
        } catch (IncorrectCredentialsException e) {  
            System.out.println("��¼�������. Password for account " + token.getPrincipal() + " was incorrect.");  
        } catch (ExcessiveAttemptsException e) {  
            System.out.println("��¼ʧ�ܴ�������");  
        } catch (LockedAccountException e) {  
            System.out.println("�ʺ��ѱ�����. The account for username " + token.getPrincipal() + " was locked.");  
        } catch (DisabledAccountException e) {  
            System.out.println("�ʺ��ѱ�����. The account for username " + token.getPrincipal() + " was disabled.");  
        } catch (ExpiredCredentialsException e) {  
            System.out.println("�ʺ��ѹ���. the account for username " + token.getPrincipal() + "  was expired.");  
        } catch (UnknownAccountException e) {  
            System.out.println("�ʺŲ�����. There is no user with username of " + token.getPrincipal());  
        } catch (UnauthorizedException e) {  
            System.out.println("��û�еõ���Ӧ����Ȩ��" + e.getMessage());  
        }  
        
        /*SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userService.getRoles(username));
        authorizationInfo.setStringPermissions(userService.getPermissions(username));*/
        
        if (subject.isAuthenticated()) {  
            System.out.println("��¼�ɹ�");
        } else {  
        	System.out.println("��¼ʧ��");
        } 
        
        subject.logout();
    }  

}
