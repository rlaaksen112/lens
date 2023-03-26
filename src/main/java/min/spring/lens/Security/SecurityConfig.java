package min.spring.lens.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;
import min.spring.lens.Service.MemberSecurityService;


@RequiredArgsConstructor
@Configuration // 스프링의 환경설정 파일임을 의미하는 애너테이션이다.
@EnableWebSecurity // 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만드는 애너테이션이다.
public class SecurityConfig {


	private final MemberSecurityService memberSecurityService;


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/")
		.permitAll()			//홈 화면("/")에 대해선 모든 유저가 접근 가능하게,

        .antMatchers("/member/login")
        .anonymous()	//로그인 페이지는 익명의 사용자만 가능

        .antMatchers("/member/create")
        .anonymous()	//회원가입 페이지는 익명의 사용자만 가능

		 .antMatchers("/member/admin/**")
		 .hasRole("ADMIN")		//관리자 페이지는 관리자의 권한을 가진 사용자만 가능


////    -------- submit 보안해제 -----------
			.and()
			.csrf()
			.ignoringAntMatchers("/**") //CSRF 폼태그 전체에 검사한

////	--------- 로그인 설정 담당 -----------
			.and()
            .formLogin()
           .loginPage("/member/login")
           .defaultSuccessUrl("/")
           .usernameParameter("memberId")	//usernameParameter("id")'는 유저 아이디에 해당하는 form의 name을 변경합니다. 이 부분은 없어도 되며, 그럼 default는 'username' 입니다. 위와 같이 변경했다면 input은 '<input type="text" name="id">' 처럼 되겠네요.
           .passwordParameter("memberPw")	//데이터에서 관여하는 것은 name만이다 인풋에 Id:같은 다른태그가 있을떄도 네임만 파라미터 지정된 이름으로 바꿔주면 된다.

////--------- 로그아웃 설정 담당 -----------
           .and()
           .logout()
           .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))		//템플릿에 들어갈 요청 주소
           .logoutSuccessUrl("/")	//로그아웃 완료 후 돌아갈 페이지
           .invalidateHttpSession(true)

           ;
		return http.build();
	}

////	-----비밀번호 암호화 메서드 사용 ------
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

////	------시큐리티 인증 담당 -------
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}





//authenticated() ;  인증된 사용자의 접근을 허용
//fullyAuthenticated(): 인증된 사용자의 접근을 허용,  rememberMe인증 제외
//permitAll(): 무조건 허용
//denyAll(): 무조건 차단
//anonymous(): 익명사용자 허용
//rememberMe(): rememberMe 인증 사용자 접근 허용
//access(String): 주어진 SpEL표현식의 평가 결과가 true 이면 접근허용
//hasRole(String): 사용자가 주어진 역할이 있다면 접근을 허용
//hasAuthority(String): 사용자가 주어진 권한이 있다면 허용
//hasAnyRole(String...): 사용자가 주어진 어떤권한이라도 있으면 허용
//hasAnyAuthority(String...): 사용자가 주어진 권한중 어떤 것이라도 있다면 허용
//hasIpAddress(String): 주어진 IP로 부터 요청이 왔다면 접근을 허용
