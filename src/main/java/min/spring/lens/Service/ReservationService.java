package min.spring.lens.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import min.spring.lens.Model.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import min.spring.lens.Model.Member;
import min.spring.lens.Model.Reservation;
import min.spring.lens.Model.Store;
import min.spring.lens.Repository.MemberRepository;
import min.spring.lens.Repository.ReservationRepository;
import min.spring.lens.Repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public void create(String storeName, Reservation reservation, Principal principal) {
        Reservation q = new Reservation();
        Optional<Member> mem = this.memberRepository.findByMemberId(principal.getName());
        Optional<Store> qq = this.storeRepository.findByStoreName(storeName);

        q.setCreateDate(LocalDateTime.now());
        q.setBuyTime(reservation.getBuyTime());
        q.setCount(reservation.getCount());
        q.setProductOption(reservation.getProductOption());
        q.setProductName(reservation.getProductName());
        q.setMember(mem.get());
        q.setStore(qq.get());

        this.reservationRepository.save(q);
    }

    public Reservation getReservationId(Integer reservationCode) {
        Optional<Reservation> a = this.reservationRepository.findById(reservationCode);
        if (a.isPresent()) {
            return a.get();
        } else {
            throw new DataNotFoundException("Reservation not found");
        }
    }


    public List<Reservation> selectList(Principal principal) {
        Optional<Member> mem = this.memberRepository.findByMemberId(principal.getName());
        Member member = this.memberRepository.findById(mem.get().getMemberCode()).get();
        return this.reservationRepository.findByMemberLike(member);
    }

    public List<Reservation> selectListStore(Principal principal) {
        Optional<Member> mem = this.memberRepository.findByMemberId(principal.getName());
        Store store = this.storeRepository.findByStoreName(mem.get().getStore().getStoreName()).get();
        return this.reservationRepository.findByStoreLike(store);
    }

    public void delate(Reservation reservation, Principal principal) {
        Optional<Member> mem = this.memberRepository.findByMemberId(principal.getName());
        Member me = this.memberRepository.findByMemberId(mem.get().getMemberId()).get();

        Reservation re = this.reservationRepository.findByMemberAndProductNameAndProductOption(me, reservation.getProductName(), reservation.getProductOption());

        this.reservationRepository.delete(re);
    }

    public void delet(Reservation reservation){

        this.reservationRepository.delete(reservation);
    }


    public Page<Reservation> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.reservationRepository.findAll(pageable);
    }


}
