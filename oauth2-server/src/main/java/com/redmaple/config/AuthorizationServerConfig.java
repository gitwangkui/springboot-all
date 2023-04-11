//package com.redmaple.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//// ������Ȩ������Ϣ
//@Configuration
//@EnableAuthorizationServer // ������֤��Ȩ����
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//
//	@Autowired
//	// @Qualifier("authenticationManagerBean")
//	private AuthenticationManager authenticationManager;
//
//	@Autowired
//	@Qualifier("dataSource")
//	private DataSource dataSource;
//
//	// @Autowired
//	// private UserDetailsService userDetailsService;
//
//	@Bean
//	public TokenStore tokenStore() {
//		// return new InMemoryTokenStore(); //ʹ���ڴ��е� token store
//		return new JdbcTokenStore(dataSource); /// ʹ��Jdbctoken store
//	}
//
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//
//		// �����Ȩ�û�
//		clients.jdbc(dataSource).withClient("client_1").secret(new BCryptPasswordEncoder().encode("123456"))
//				.authorizedGrantTypes("password", "refresh_token", "authorization_code")// ������Ȩ��Χ
//				.redirectUris("http://www.mayikt.com").authorities("ROLE_ADMIN", "ROLE_USER")// �ͻ��˿���ʹ�õ�Ȩ��
//				.scopes("all").accessTokenValiditySeconds(7200).refreshTokenValiditySeconds(7200);
//		// �Զ������ݲ����Ӧ��id��Ϣ
//	}
//
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager)
//				.userDetailsService(userDetailsService());// ��������
//															// UserDetailsService
//															// ����ˢ��token ʱ�ᱨ��
//	}
//
//	// �Ѿ�����UserDetailsService д������
//	@Bean
//	UserDetailsService userDetailsService() {
//		InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//		userDetailsService.createUser(User.withUsername("user_1").password(new BCryptPasswordEncoder().encode("123456"))
//				.authorities("ROLE_USER").build());
//		userDetailsService.createUser(User.withUsername("user_2")
//				.password(new BCryptPasswordEncoder().encode("1234567")).authorities("ROLE_USER").build());
//		return userDetailsService;
//	}
//
//	@Override
//	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
//				.allowFormAuthenticationForClients();// �������¼
//
//	}
//
//	@Bean
//	AuthenticationManager authenticationManager() {
//		AuthenticationManager authenticationManager = new AuthenticationManager() {
//
//			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//				return daoAuhthenticationProvider().authenticate(authentication);
//			}
//		};
//		return authenticationManager;
//	}
//
//	@Bean
//	public AuthenticationProvider daoAuhthenticationProvider() {
//		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
//		daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
//		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//		return daoAuthenticationProvider;
//	}
//
//	@Bean
//	PasswordEncoder passwordEncoder() {
//		// ���ܷ�ʽ
//		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		return passwordEncoder;
//	}
//	// 6��git ����oauth2 ����� 1.5�汾 1�� 2.0
//
//}
