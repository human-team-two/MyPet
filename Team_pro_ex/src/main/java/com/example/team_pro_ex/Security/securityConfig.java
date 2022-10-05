package com.example.team_pro_ex.Security;

import com.example.team_pro_ex.com.Service.businessMember.businessMemberService;
import com.example.team_pro_ex.com.Service.member.memberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class securityConfig  {

    @Autowired
    memberService memberService;

    @Autowired
    businessMemberService businessMemberService;


    //WebSecurity filter 구현
    // 해당경로에는 Security가 모두 무시할 수 있도록 설정
    // 기본경로는 resources/static
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/js/**","/css/**","/images/**","/font/**","/html/**");
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        //매니저는 회원, 사업자에 접근할 수 있다.
        // antMatchers("/**").permitAll() => 모든 경로에 권한없이 접근가능
        // antMatchers().authenticated() => 모든 요청에 인증된
        // .antMatchers("/Member/**") => 와일드카드로 인해 모든 거 허용
        // .antMatchers("/Member/**").hasRole("Role_MANAGER") => 우리 프로젝트 Manager는 회원, 사업자
        // 경로의 모든 경로를 Role_MANAGER가 접근할 수 있다.


        //회원
        // http.formLogin() => 인증이 필요한 요청은 스프링시큐리티에서 사용하는 기본 form login page사용
        http.formLogin()
                .loginPage("/Member/Login") //로그인 페이지(시큐리티에서 주는 로그인 페이지가 있는데 이걸 우리가 만든 페이지로 변경)
                .defaultSuccessUrl("/") // 로그인 후 메인페이지로 이동
                .usernameParameter("id") // 시큐리티 default username -> 우리의 id로 변경
                .failureUrl("") // 로그인 실패
                .and()
                .logout() // 로그아웃
                .logoutRequestMatcher(new AntPathRequestMatcher("/Member/logout"))
                .logoutSuccessUrl("/");

        //사업자
//        http.formLogin()
//                .loginPage("/businessMember/LoginBm")
//                .defaultSuccessUrl("/")
//                .usernameParameter("id")
//                .failureUrl()
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/businessMember/logout"))
//                .logoutSuccessUrl("/");


        //http.authorizeRequests() => 메서드로 특정 경로에 특정한 권한을 가진 사용자만 접근할 수 있도록 설정
        http.authorizeRequests()
                //.mvcMatchers() => 특정결로를 지정해서 권한 설정가능
                .mvcMatchers("/js/**","/css/**","/images/**","/font/**","/html/**").permitAll()
                .mvcMatchers("/", "/Member/**", "/img/**").permitAll()
                .
    }
}
