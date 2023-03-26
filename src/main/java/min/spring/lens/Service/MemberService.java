package min.spring.lens.Service;

import java.security.Principal;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import min.spring.lens.Model.DataNotFoundException;
import min.spring.lens.Model.Member;
import min.spring.lens.Model.MemberCreateForm2;
import min.spring.lens.Model.Store;
import min.spring.lens.Repository.MemberRepository;
import min.spring.lens.Repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder; // 패스워드의 암호화를 위해서 시큐리티의 BCryptPasswordEncoder를 사용했다.
	private final StoreRepository storeRepository;

	public Member create(String memberId, String memberPw, String memberName, String memberPhone, String memberEmail,
			String memberBirth) {
		Member q = new Member();
		q.setMemberId(memberId);
		q.setMemberPw(passwordEncoder.encode(memberPw));
		q.setMemberName(memberName);
		q.setMemberPhone(memberPhone);
		q.setMemberEmail(memberEmail);
		q.setMemberBirth(memberBirth);

		this.memberRepository.save(q);

		return q;
	}

	public Member create2(MemberCreateForm2 memberCreateForm2, String storeName) {
		Member q = new Member();
		Optional<Store> qq = this.storeRepository.findByStoreName(storeName);

		q.setMemberId(memberCreateForm2.getMemberId());
		q.setMemberPw(passwordEncoder.encode(memberCreateForm2.getMemberPw()));
		q.setMemberName(memberCreateForm2.getMemberName());
		q.setMemberPhone(memberCreateForm2.getMemberPhone());
		q.setMemberEmail(memberCreateForm2.getMemberEmail());
		q.setMemberBirth(memberCreateForm2.getMemberBirth());
		q.setStore(qq.get());

		this.memberRepository.save(q);

		return q;

	}

	public Member getMemberId(Principal principal) {
		Optional<Member> member = this.memberRepository.findByMemberId(principal.getName());

		if (member.isPresent()) {
			return member.get();
		} else {
			throw new DataNotFoundException("member not found");
		}
	}

}
