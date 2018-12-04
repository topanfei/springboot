package com.pf.demo.security.entity;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * jwtUser对象
 * @author PF
 *
 */
public class JwtUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private final String id;//用户id
	private final String userName;//用户名
	private final String userPasswd;//用户密码
	private final String roleName;//角色名称
	private final String roleType;//角色类型
    private final Date lastPasswordResetDate;//上次修改密码时间
    private final boolean accountNonExpired;//账号是否到期
    private final boolean accountNonLocked;//账号是否锁定
    private final boolean credentialsNonExpired;//资格证书是否过期
    private final boolean enabled;//账号是否激活
    private final Collection<? extends GrantedAuthority> authorities;
    
    public JwtUser(
    		String id,
            String userName,
            String userPasswd,
            String roleName,
            String roleType,
            Date lastPasswordResetDate,
            boolean accountNonExpired,
            boolean accountNonLocked,
            boolean credentialsNonExpired,
            boolean enabled,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userName = userName;
        this.userPasswd = userPasswd;
        this.roleName = roleName;
        this.roleType = roleType;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.authorities = authorities;
    }
    
    /**
     * 获取用户权限
     */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	/**
	 * 获取用户id
	 * @return
	 */
	@JsonIgnore
    public String getId() {
        return id;
    }
	/**
	 * 获取用户密码
	 */
	@Override
	public String getPassword() {
		return userPasswd;
	}
	/**
	 * 获取用户用户名
	 */
	@Override
	public String getUsername() {
		return userName;
	}
	/**
	 * 账户是否未过期
	 */
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	/**
	 * 账户是否未锁定
	 */
    @JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
    /**
     * 密码是否未过期
     */
    @JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
    /**
     * 账户是否激活
     */
    @JsonIgnore
	@Override
	public boolean isEnabled() {
		return enabled;
	}
    /**
     * 这个是自定义的，返回上次密码重置日期
     * @return
     */
    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
    /**
     * 获取角色名称
     * @return
     */
    @JsonIgnore
	public String getRoleName() {
		return roleName;
	}
    
    /**
     * 获取角色类型
     * @return
     */
    @JsonIgnore
	public String getRoleType() {
		return roleType;
	}
}
