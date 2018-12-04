package com.pf.demo.security.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.pf.demo.exception.BusinessException;
import com.pf.demo.security.entity.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	public static final String ROLE_REFRESH_TOKEN = "ROLE_REFRESH_TOKEN";//refreshToken
    private static final String CLAIM_KEY_USER_ID = "id";//用户id
    private static final String CLAIM_KEY_PASSWORD = "userPasswd";//用户密码
    private static final String CLAM_KEY_ROLENAME = "roleName";//角色名称
    private static final String CLAM_KEY_ROLETYPE = "roleType";//角色类型
    private static final String CLAIM_KEY_AUTHORITIES = "scope";
    private static final String CLAIM_KEY_ACCOUNT_ENABLED = "enabled";//是否激活
    private static final String CLAIM_KEY_ACCOUNT_NON_LOCKED = "non_locked";//是否锁定
    private static final String CLAIM_KEY_ACCOUNT_NON_EXPIRED = "non_expired";//

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.access_token.expiration}")
    private Long access_token_expiration;
    @Value("${jwt.refresh_token.expiration}")
    private Long refresh_token_expiration;
    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    /**
     * 从token中获取用户信息
     * @param token
     * @return
     */
    @SuppressWarnings("rawtypes")
	public JwtUser getUserFromToken(String token) {
    	JwtUser user;
        try {
            final Claims claims = getClaimsFromToken(token);
            String userId = getUserIdFromToken(token);//用户id
            String username = claims.getSubject();//用户名
            String userPasswd=(String)claims.get(CLAIM_KEY_PASSWORD);//用户密码
            String roleName = (String)claims.get(CLAM_KEY_ROLENAME);//用户角色名称
            String roleType = (String)claims.get(CLAM_KEY_ROLETYPE);//用户角色类型
            List roles = (List) claims.get(CLAIM_KEY_AUTHORITIES);//用户权限信息
            Collection<? extends GrantedAuthority> authorities = parseArrayToAuthorities(roles);
            boolean account_enabled = (Boolean) claims.get(CLAIM_KEY_ACCOUNT_ENABLED);
            boolean account_non_locked = (Boolean) claims.get(CLAIM_KEY_ACCOUNT_NON_LOCKED);
            boolean account_non_expired = (Boolean) claims.get(CLAIM_KEY_ACCOUNT_NON_EXPIRED);
            user = new JwtUser(userId, username, userPasswd,roleName,roleType,
            		new Date(), account_non_expired, account_non_locked, true, account_enabled, authorities);
        } catch (Exception e) {
            user = null;
        }
        return user;
    }

    /**
     * 从token中获取用户id
     * @param token token值
     * @return
     */
    public String getUserIdFromToken(String token) {
        String userId="";
        try {
            final Claims claims = getClaimsFromToken(token);
            userId = claims.get(CLAIM_KEY_USER_ID).toString();
        } catch (Exception e) {
            userId = "";
        }
        return userId;
    }
    
    
    /**
     * 从token中获取用户的用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = "";
        }
        return username;
    }

    /**
     * 获取用户的密码
     * @param token
     * @return
     */
    public String getUserPassword(String token){
    	String te_passwd="";
        try {
            final Claims claims = getClaimsFromToken(token);
            te_passwd = claims.get(CLAIM_KEY_PASSWORD).toString();
        } catch (Exception e) {
        	te_passwd = "";
        }
        return te_passwd;
    }
    
    
    /**
     * 从token中获取创建token日期
     * @param token
     * @return
     */
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = claims.getIssuedAt();
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    /**
     * 获取token到期时间
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 将token到期时间转为毫秒
     * @param expiration
     * @return
     */
    private Date generateExpirationDate(long expiration) {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 判断token是否已经到期
     * @param token
     * @return
     */
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    /**
     * 生成accessToken
     * @param userDetails
     * @return
     */
    public String generateAccessToken(UserDetails userDetails) {
    	JwtUser user = (JwtUser) userDetails;
        Map<String, Object> claims = generateClaims(user);
        claims.put(CLAIM_KEY_AUTHORITIES, JSON.toJSON(authoritiesToArray(user.getAuthorities())));
        return generateAccessToken(user.getUsername(), claims);
    }
    
    private Map<String, Object> generateClaims(JwtUser user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USER_ID, user.getId());//用户id
        claims.put(CLAIM_KEY_PASSWORD, user.getPassword());//用户密码
        claims.put(CLAM_KEY_ROLENAME, user.getRoleName());//角色名称
        claims.put(CLAM_KEY_ROLETYPE, user.getRoleType());//角色类型
        claims.put(CLAIM_KEY_ACCOUNT_ENABLED, user.isEnabled());
        claims.put(CLAIM_KEY_ACCOUNT_NON_LOCKED, user.isAccountNonLocked());
        claims.put(CLAIM_KEY_ACCOUNT_NON_EXPIRED, user.isAccountNonExpired());
        return claims;
    }

    private String generateAccessToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, access_token_expiration);
    }

	@SuppressWarnings("rawtypes")
	private List authoritiesToArray(Collection<? extends GrantedAuthority> authorities) {
        List<String> list = new ArrayList<>();
        for (GrantedAuthority ga : authorities) {
            list.add(ga.getAuthority());
        }
        return list;
    }

    @SuppressWarnings("rawtypes")
	private Collection<? extends GrantedAuthority> parseArrayToAuthorities(List roles) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority authority;
        for (Object role : roles) {
            authority = new SimpleGrantedAuthority(role.toString());
            authorities.add(authority);
        }
        return authorities;
    }

    /**
     * 生成refreshToken
     * @param userDetails
     * @return
     */
    public String generateRefreshToken(UserDetails userDetails) {
    	JwtUser user = (JwtUser) userDetails;
        Map<String, Object> claims = generateClaims(user);
        // 只授于更新 token 的权限
        String roles[] = new String[]{JwtTokenUtil.ROLE_REFRESH_TOKEN};
        claims.put(CLAIM_KEY_AUTHORITIES, JSON.toJSON(roles));
        return generateRefreshToken(user.getUsername(), claims);
    }
    
    private String generateRefreshToken(String subject, Map<String, Object> claims) {
        return generateToken(subject, claims, refresh_token_expiration);
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token));
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            refreshedToken = generateAccessToken(claims.getSubject(), claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    private String generateToken(String subject, Map<String, Object> claims, long expiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate(expiration))
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SIGNATURE_ALGORITHM, secret)
                .compact();
    }

    /**
     * 校验
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
    	JwtUser user = (JwtUser) userDetails;//从数据库中查出来的用户信息，包括权限信息
        final String userId = getUserIdFromToken(token);
        if(!userId.equals(user.getId())){
        	throw new BusinessException("该用户不存在!");
        }else if(!user.isAccountNonLocked()){//账号已被锁定
        	throw new BusinessException("账号已被锁定!");
        }else if(!user.isEnabled()){//账号未激活
        	throw new BusinessException("账号未激活!");
        	//throw new DisabledException("账号未激活");
        }else if(!user.isAccountNonExpired()){//账号过期
        	throw new BusinessException("账号过期!");
        	//throw new AccountExpiredException("账号过期");
        }else if(!user.isCredentialsNonExpired()){
        	throw new BusinessException("资格证书过期!");
        }
        else{
        	return true;
        }
    }
}
