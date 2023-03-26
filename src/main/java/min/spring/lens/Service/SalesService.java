package min.spring.lens.Service;

import lombok.RequiredArgsConstructor;
import min.spring.lens.Model.*;
import min.spring.lens.Repository.MemberRepository;
import min.spring.lens.Repository.ReservationRepository;
import min.spring.lens.Repository.SalesRepository;
import min.spring.lens.Repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SalesService {
    private final SalesRepository salesRepository;
    private final StoreRepository storeRepository;
    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;

    public void create(Reservation reservation) {   //판매 등록
        Sales q = new Sales();

        q.setStore(reservation.getStore());
        q.setProductName(reservation.getProductName());
        q.setSale(reservation.getCount());
        q.setProductOption(reservation.getProductOption());
        q.setCreateDate(LocalDateTime.now());
        this.salesRepository.save(q);
    }

    public List<Sales> getName(Principal principal){
        Optional<Member> mem = this.memberRepository.findByMemberId(principal.getName());
        Store store = this.storeRepository.findByStoreName(mem.get().getStore().getStoreName()).get();

        return this.salesRepository.findByStoreLike(store);
    }
}
