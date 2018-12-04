package com.pf.demo.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pf.demo.security.impl.JwtUserCheckTokenDetailsServiceImpl;
import com.pf.demo.security.util.JwtTokenUtil;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	
	@Value("${jwt.header}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserCheckTokenDetailsServiceImpl jwtUserCheckTokenDetailsServiceImpl;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader(this.tokenHeader);
		if (authHeader != null && authHeader.startsWith(tokenHead)) {
			final String authToken = authHeader.substring(tokenHead.length());
			String userId = jwtTokenUtil.getUserIdFromToken(authToken);
			if ( !ObjectUtils.isEmpty(userId) && SecurityContextHolder.getContext().getAuthentication() == null) {
                //验证token信息，userDetails其实是从库中查出的对应用户信息。
                //实现：通过token里解密出的用户信息和库中查出来的进行比较，有问题就抛出异常
				
				
				UserDetails userDetails = this.jwtUserCheckTokenDetailsServiceImpl.loadUserByUsername(userId);
				
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    logger.info("authenticated user " + userId + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
			}
		}
		
		filterChain.doFilter(request, response);
	}


}
