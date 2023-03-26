package min.spring.lens.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import min.spring.lens.Model.Member;



@Repository
public interface MemberRepository extends JpaRepository<Member, Integer > {
	Optional<Member> findByMemberId(String memberId);
	Optional <Member> findByStore(Integer storeCode);
}
