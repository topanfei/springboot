package com.pf.demo.security.impl;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;

import com.pf.demo.security.entity.JwtUser;


/**
 * 用于创建jwtUser对象
 * @author PF
 *
 */
public class JwtUserFactory {
	private JwtUserFactory() {}
	
	/**
	 * 创建jwtUser对象
	 * @param id 用户id
	 * @param te_operid 操作员账号
	 * @param username 操作员用户名
	 * @param password 操作员密码
	 * @param org_id 操作员所在的单位id
	 * @param te_dept_type 操作员所在单位类型
	 * @param positions_id 操作员的职位id
	 * @param status 操作员状态
	 * @param lastPasswordResetDate 上次修改密码时间
	 * @param accountNonExpired 账号是否失效
	 * @param accountNonLocked 账号是否锁定
	 * @param credentialsNonExpired 资格证书是否过期
	 * @param enabled 账号是否可用
	 * @param authorities 操作员的权限集合
	 * @return 返回创建的jwtUser
	 */
	public static JwtUser create(String id,String username,String userPasswd,String roleName,String roleType,
			Date lastPasswordResetDate,boolean accountNonExpired,boolean accountNonLocked,boolean credentialsNonExpired,
			boolean enabled,Collection<? extends GrantedAuthority> authorities) {
		return new JwtUser(id, username, userPasswd, roleName, roleType,lastPasswordResetDate, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled, authorities);
	}
	
}