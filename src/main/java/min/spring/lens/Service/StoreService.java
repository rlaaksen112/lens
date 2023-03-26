package min.spring.lens.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import min.spring.lens.Model.Member;
import min.spring.lens.Model.Store;
import min.spring.lens.Repository.MemberRepository;
import min.spring.lens.Repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreService {
	private final StoreRepository storeRepository;
	private final MemberRepository memberRepository;


	public void create(String storeName, String storeTel) {
		Store q = new Store();

		q.setStoreName(storeName);
		q.setStoreTel(storeTel);

		this.storeRepository.save(q);
	}

	public Store getStore(Integer Id) {

		this.storeRepository.findById(Id).get();

		return this.storeRepository.findById(Id).get();
	}
	public List<Store>  selectList(Principal principal) {
		Optional<Member> mem = this.memberRepository.findByMemberId(principal.getName());
		Member member = this.memberRepository.findById(mem.get().getMemberCode()).get();
		return  this.storeRepository.findByMemberLike(member);
	}
}
