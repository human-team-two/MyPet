package com.example.team_pro_ex.Security.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) //security 어노테이션 활성화
public class SerurityConfigMember extends WebSecurityConfigurerAdapter {

    //로그인 실패 핸들러 의존성 주입
    private final AuthenticationFailureHandler customAuthFailureHandler;

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/css/**","/js/**","/imges/**","/fonts/**","/webpageimg/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                //매니저는 회원, 사업자에 접근할 수 있다.
                // antMatchers("/**").permitAll() => 모든 경로에 권한없이 접근가능
                // antMatchers().authenticated() => 모든 요청에 인증된
                // .antMatchers("/Member/**") => 와일드카드로 인해 모든 거 허용
                // .antMatchers("/Member/**").hasRole("Role_MANAGER") => 우리 프로젝트 Manager는 회원, 사업자
                // 경로의 모든 경로를 Role_MANAGER가 접근할 수 있다.

                //페이지 권한설정 => 매니저의 권한을 넣으려면 Member옆에 access와 같이 입력을 해줘야한다.
                //시큐리티 권한을 사용하려면 반드시 ROLE_MEMBER와 같이 사용하여야 한다.
                .regexMatchers("/Member/[^(mJoin/Join)|(Login)].*").
                access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MEMBER')")
                .regexMatchers("/businessMember/[^(bmJoin/bm_Join)].*").
                access("hasRole('ROLE_manager') or hasRole('ROLE_ADMIN')")
                //설정해준거 외에는 어디든지 접근가능하다. .anyRequest().permitAll()
                .anyRequest().permitAll()
                .and()
                //로그인 권한설정
                .formLogin()
                .loginPage("/Member/Login")
                .usernameParameter("id")
                .loginProcessingUrl("/logins")
                .defaultSuccessUrl("/")
                .failureHandler(customAuthFailureHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/Member/Login")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID", "remember-me")
                .and()
                //403 예외처리 핸들링
                .exceptionHandling().accessDeniedPage("/index");

    }
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }



}
