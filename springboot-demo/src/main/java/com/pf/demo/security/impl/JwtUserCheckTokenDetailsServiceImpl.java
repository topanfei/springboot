package com.pf.demo.security.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.pf.demo.entityview.UserInfo;

/**
 * 该类生成的token是有权限信息的
 * @author PF
 *
 */
@Component
public class JwtUserCheckTokenDetailsServiceImpl implements UserDetailsService {
	
	private boolean accountNonExpiredFlag=true;//账号是否未过期
	private boolean accountNonLockedFlag=true;//账号是否未锁定
	private boolean credentialsNonExpiredFlag=true;//密码是否过期
	private boolean enabledFlag=true;//账号是否激活
	
	@SuppressWarnings("unused")
	@Override
	public UserDetails loadUserByUsername(String info) throws UsernameNotFoundException {
		UserInfo userInfo = new UserInfo();
        if (userInfo == null) {
            throw new UsernameNotFoundException(String.format("未找到该用户名的用户 '%s'.", userInfo.getUserName()));
        } else {
        	return toCreateJwtUser(userInfo);
        }
	}
	
	/**
	 * 将自定义的user对象转为JwtUser
	 * @param user 自定义的user对象
	 * @return jwtUser对象
	 */
	public UserDetails toCreateJwtUser(UserInfo userInfo){
		String id = userInfo.getId();//用户id
    	String userName=userInfo.getUserName();//用户名
    	String userPasswd =userInfo.getUserPasswd();//密码
    	String roleName = userInfo.getRoleName();//角色名称
    	String roleType = userInfo.getRoleType();//角色类型
    	Date lastPasswordResetDate = userInfo.getLast_password_reset_date();//上次修改密码的日期
        List<GrantedAuthority> authorityNameList= mapToGrantedAuthorities(userInfo.getAuthorityNameList());//权限名称
        return JwtUserFactory.create(id, userName, userPasswd, roleName, roleType,  
        		lastPasswordResetDate, accountNonExpiredFlag, accountNonLockedFlag, credentialsNonExpiredFlag, enabledFlag, authorityNameList);
	}

	/**
	 * 将List<String>转为List<GrantedAuthority>类型
	 * @param authorities 操作员权限集合
	 * @return
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		if(!ObjectUtils.isEmpty(authorities)) {
			for (int i = 0; i < authorities.size(); i++) {
				GrantedAuthority au = new SimpleGrantedAuthority(authorities.get(i));
				list.add(au);
			}
		}
		return list;
	}
}
