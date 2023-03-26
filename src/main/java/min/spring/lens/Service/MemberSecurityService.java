package min.spring.lens.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import min.spring.lens.Model.Member;
import min.spring.lens.Model.MemberRole;
import min.spring.lens.Repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class MemberSecurityService implements UserDetailsService {

	private final MemberRepository memberRepository;


	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		Optional<Member> _member = this.memberRepository.findByMemberId(memberId);

		if (_member.isEmpty()) {
			throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
		}
		Member member = _member.get();

		List<GrantedAuthority> authorities = new ArrayList<>();
		if  ("admin".equals(memberId)) {
			authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
		} else {
			authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
		}
		if  ("admin1".equals(memberId)) {
			authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
		}
		if  ("admin2".equals(memberId)) {
			authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
		}
		if  ("admin3".equals(memberId)) {
			authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
		}
		if  ("admin4".equals(memberId)) {
			authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
		}
		if  ("admin5".equals(memberId)) {
			authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
		}
		System.out.println("유저 접속 아이디 : "+memberId+"님 접속 권한 "+authorities + "가 부여 되었습니다.");		//접속 유저 권한 콘솔 확인
		return new User(member.getMemberId(), member.getMemberPw(), authorities);
	}
}
