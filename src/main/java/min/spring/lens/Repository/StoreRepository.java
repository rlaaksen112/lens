package min.spring.lens.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import min.spring.lens.Model.Member;
import min.spring.lens.Model.Store;

public interface StoreRepository extends JpaRepository<Store, Integer > {
	Optional<Store> findByStoreName(String storeName);
	List<Store> findByMemberLike(Member member);
}
