package com.redmaple.config;

import com.redmaple.entity.Permission;
import com.redmaple.handler.MyAuthenticationFailureHandler;
import com.redmaple.handler.MyAuthenticationSuccessHandler;
import com.redmaple.mapper.PermissionMapper;
import com.redmaple.security.MyUserDetailService;
import com.redmaple.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * @author 若成风
 * @description
 * @date 2021/4/25 21:37
 * @copyright (c) 2021, all rights reserved
 **/
@Configuration
@EnableWebSecurity  //开启webSerurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;
    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private MyUserDetailService myUserDetailService;

    /**
     * @description: 授权用户名密码
     * @author: 若成风
     * @date: 2021/4/25 21:39
     * @return:
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 添加admin账号并授权
//        auth.inMemoryAuthentication().withUser("admin").password("123456")
//            .authorities("showOrder","addOrder","updateOrder","deleteOrder");
//        // 添加普通账号
//        auth.inMemoryAuthentication().withUser("userAdd").password("123456")
//                .authorities("showOrder","addOrder");

        //动态从数据库中查询
        auth.userDetailsService(myUserDetailService).passwordEncoder(new PasswordEncoder() {

            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String) rawPassword);
            }

            /**
             * @description: 匹配数据库中密码
             * rawPassword  输入原始未加密密码，
             * encodedPassword  数据库中加密密码
             * @author: 若成风
             * @date: 2021/4/25 22:42
             * @return: void
             */
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                String encode = MD5Util.encode((String) rawPassword);
                encodedPassword=encodedPassword.replace("\r\n", "");
                boolean result = encodedPassword.equals(encode);
                return result;
            }
        });

    }

    /**
     * @description: 拦截用户的请求
     * @author: 若成风
     * @date: 2021/4/25 21:40
     * @return:
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 如何权限控制 给每一个请求路径 分配一个权限名称 让后账号只要关联该名称，就可以有访问权限
//        http.authorizeRequests()
//            // 配置查询订单权限
//            .antMatchers("/login").permitAll()
//            .antMatchers("/showOrder").hasAnyAuthority("showOrder")
//            .antMatchers("/addOrder").hasAnyAuthority("addOrder")
//            .antMatchers("/updateOrder").hasAnyAuthority("updateOrder")
//            .antMatchers("/deleteOrder").hasAnyAuthority("deleteOrder")
//            .antMatchers("/**").fullyAuthenticated().and().formLogin().loginPage("/login")
//            .successHandler(successHandler).failureHandler(failureHandler)
//            .and().csrf().disable();
        List<Permission> listPermission = permissionMapper.findAllPermission();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http
                .authorizeRequests();
        for (Permission permission : listPermission) {
            authorizeRequests.antMatchers(permission.getUrl()).hasAnyAuthority(permission.getPermTag());
        }
        authorizeRequests.antMatchers("/login").permitAll().antMatchers("/**").fullyAuthenticated().and().formLogin()
                .loginPage("/login").and().csrf().disable();
    }

    // 表示去除密码的加密
//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }
}
