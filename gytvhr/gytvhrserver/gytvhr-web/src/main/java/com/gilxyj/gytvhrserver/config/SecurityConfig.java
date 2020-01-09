package com.gilxyj.gytvhrserver.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;


/**
 * @program: wlbbl
 * @description:
 * @author: GilbertXiao
 * @create: 2019-07-07 12:22
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AccessDecisionManagerSupport accessDecisionManagerSupport;

    @Autowired
    CustomMetadataSource metadataSource;

    @Autowired
    JsonAuthenticationSuccessHandler jsonAuthenticationSuccessHandler;

    @Autowired
    JsonAuthenticationFailureHandler jsonAuthenticationFailureHandler;

    @Autowired
    JsonAccessDeniedHandler jsonAccessDeniedHandler;

    @Autowired
    JsonLogoutSuccessHandler jsonLogoutSuccessHandler;

    @Autowired
    JsonAuthencationEntryPoint jsonAuthencationEntryPoint;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //设置false,使其能放回用户名不存在的提示
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login", "/index.html", "/static/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.anyRequest().authenticated()//所有的请求必须认证后才能通过
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(metadataSource);
                        o.setAccessDecisionManager(accessDecisionManagerSupport);
                        return o;
                    }
                })
                .and()
                .formLogin()
                //登录页面，就是用户未登录时自动跳转到的页面
                //.loginPage("/login")
                //登录请求处理接口
                .loginProcessingUrl("/doLogin")
                .usernameParameter("username")
                .passwordParameter("password")
                //登录成功后，要跳转的页面
//                .successForwardUrl()
                //登录成功后的处理器，可以在这个处理器中返回json
                .successHandler(jsonAuthenticationSuccessHandler)
                //登录失败处理器
                .failureHandler(jsonAuthenticationFailureHandler)
                .permitAll()
                .and().logout().logoutSuccessHandler(jsonLogoutSuccessHandler)
                .and()
                .csrf().disable().exceptionHandling().authenticationEntryPoint(jsonAuthencationEntryPoint).accessDeniedHandler(jsonAccessDeniedHandler)
        ;

    }

}
