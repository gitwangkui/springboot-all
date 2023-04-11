//package com.redmaple.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
//import org.springframework.ldap.core.LdapTemplate;
//import org.springframework.ldap.core.support.LdapContextSource;
//
///**
// * 配置AD数据源
// */
//@Configuration
//@EnableLdapRepositories
//public class LdapConfig {
//
//	// LDAP的连接地址(ldap://ip:port/)(ldaps://ip:port/)
//	private String provider_url;
//
//	// LDAP的根DN
//	private String base_dn;
//
//	// LDAP的连接账号（身份认证管理平台添加的应用账号，应用账号格式：uid=?,ou=?,dc=????）
//	private String security_principal;
////		private String PRINCIPAL = "CN=Monitoring Service,OU=Service Accounts,OU=ShanghaiHQ,OU=CN,OU=ASIA,OU=YFInterior,DC=YFCO,DC=YANFENGCO,DC=COM";
//
//	// LDAP的连接账号的密码（身份认证管理平台添加的应用账号的密码）
//	private String security_credentials;
//
//	// LDAP证书安装的路径
//	private String net_ssl_trustStore_path;
//
//	@Bean
//	public LdapContextSource ldapContextSource() {
//		System.setProperty("javax.net.ssl.trustStore", net_ssl_trustStore_path);
//		System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
//
//		LdapContextSource source = new LdapContextSource();
//		source.setUrl(provider_url);
//		source.setBase(base_dn);
//		source.setUserDn(security_principal);
//		source.setPassword(security_credentials);
//
//		return source;
//	}
//
//	@Bean
//	public LdapTemplate ldapTemplate() {
//		LdapTemplate ldapTemplate = new LdapTemplate(ldapContextSource());
//		ldapTemplate.setIgnorePartialResultException(true);
//		return ldapTemplate;
//	}
//}