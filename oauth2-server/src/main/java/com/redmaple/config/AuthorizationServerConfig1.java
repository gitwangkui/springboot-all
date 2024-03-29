package com.redmaple.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig1 extends AuthorizationServerConfigurerAdapter {

	private static final int ACCESSTOKENVALIDITYSECONDS = 7200;// ��Сʱ
	private static final int REFRESHTOKENVALIDITYSECONDS = 7200;// ��Сʱ

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		// 密码模式
		// withClient appid
//		clients.inMemory().withClient("client_1").secret(passwordEncoder().encode("123456"))
//				.authorizedGrantTypes("password","client_credentials","refresh_token").scopes("all").accessTokenValiditySeconds(ACCESSTOKENVALIDITYSECONDS);

		// 密码+授权模式
		clients.inMemory().withClient("client_1").secret(passwordEncoder().encode("123456"))
		 .redirectUris("http://www.mayikt.com")
		 .authorizedGrantTypes("password","client_credentials","refresh_token","authorization_code").scopes("all")
		 .accessTokenValiditySeconds(ACCESSTOKENVALIDITYSECONDS)
		 .refreshTokenValiditySeconds(REFRESHTOKENVALIDITYSECONDS);

	}

	// 设置token类型
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.authenticationManager(authenticationManager()).allowedTokenEndpointRequestMethods(HttpMethod.GET,
				HttpMethod.POST);
		//刷新新的accessToken
		endpoints.authenticationManager(authenticationManager());
		endpoints.userDetailsService(userDetailsService());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		// 允许表单认证
		oauthServer.allowFormAuthenticationForClients();
		// 允许check_token访问
		oauthServer.checkTokenAccess("permitAll()");

	}

	@Bean
	AuthenticationManager authenticationManager() {
		AuthenticationManager authenticationManager = new AuthenticationManager() {
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				return daoAuhthenticationProvider().authenticate(authentication);
			}
		};
		return authenticationManager;
	}

	@Bean
	public AuthenticationProvider daoAuhthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	// 设置添加用户信息,正常应该从数据库中读取
	@Bean
	UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
		userDetailsService.createUser(User.withUsername("user_1").password(passwordEncoder().encode("123456"))
				.authorities("ROLE_USER").build());
		userDetailsService.createUser(User.withUsername("user_2").password(passwordEncoder().encode("1234567"))
				.authorities("ROLE_USER").build());
		return userDetailsService;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		// 加密方式
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}

}
