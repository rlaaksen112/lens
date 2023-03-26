package min.spring.lens.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import min.spring.lens.Model.Member;
import min.spring.lens.Model.Reservation;
import min.spring.lens.Model.Store;



public interface ReservationRepository extends JpaRepository<Reservation, Integer > {
	List<Reservation> findByMemberLike(Member member);
	Reservation findByMemberAndProductNameAndProductOption(Member memberId , String productName , String productOption);

	List<Reservation> findByStoreLike(Store store);

	Page<Reservation> findAll(Pageable pageable);
}
