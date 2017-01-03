package com.hyp.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.hyp.popj.Auth;
import com.hyp.popj.Role;
import com.hyp.popj.Users;
import com.hyp.service.StudentService;
import com.hyp.service.UserService;

public class MyRealm extends AuthorizingRealm {

	@Autowired
	UserService userService;

	/**
	 * 验证当前登录的Subject
	 * 
	 * @see 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		// 获取基于用户名和密码的令牌
		// 实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
		// 两个token的引用都是一样的,本例中是org.apache.shiro.authc.UsernamePasswordToken@33799a1e
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		Users newUser = new Users();
		String username = token.getUsername();
		if (username != null && !username.isEmpty()) {
			newUser.setUsername(username);
		}
		Users user = userService.selectUserByUser(newUser);
		if (user != null) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),
					user.getUsername());
			this.setSession("currentUser", user);
			return authcInfo;
		} else {
			return null;
		}
	}

	/**
	 * 为当前登录的Subject授予角色和权限
	 * 
	 * @see 经测试:本例中该方法的调用时机为需授权资源被访问时
	 * @see 经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
	 * @see 个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,
	 *      则可灵活决定是否启用AuthorizationCache
	 * @see 比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String currentUsername = (String) super.getAvailablePrincipal(principals);
		List<String> roleList = new ArrayList<String>();
		List<String> permissionList = new ArrayList<String>();
		Users newUser = new Users();
		newUser.setUsername(currentUsername);
		Users user = userService.selectUserByUser(newUser);
		if (null != user) {
			// 实体类User中包含有用户角色的实体类信息
			if (null != user.getRoleList() && user.getRoleList().size() > 0) {
				// 获取当前登录用户的角色
				for (Role role : user.getRoleList()) {
					roleList.add(role.getRoleName());
					// 实体类Role中包含有角色权限的实体类信息
					if (null != role.getAuthList() && role.getAuthList().size() > 0) {
						// 获取权限
						for (Auth auth : role.getAuthList()) {
							if (!StringUtils.isEmpty(auth.getAuthName())) {
								permissionList.add(auth.getAuthName());
							}
						}
					}
				}
			}
		} else {
			throw new AuthorizationException();
		}
		// 为当前用户设置角色和权限
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		simpleAuthorInfo.addRoles(roleList);
		simpleAuthorInfo.addStringPermissions(permissionList);
		return simpleAuthorInfo;
	}

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * 
	 * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
	 */
	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}

}
