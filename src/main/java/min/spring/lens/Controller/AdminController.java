package min.spring.lens.Controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import min.spring.lens.Model.*;
import min.spring.lens.Service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member/admin")
@RequiredArgsConstructor
public class AdminController {

    private final MakerService makerService;
    private final ProductService productService;
    private final OptionsService optionsService;
    private final StoreService storeService;
    private final MemberService memberService;
    private final ReservationService reservationService;
    private final SalesService salesService;

    // -----------------------------------------------------------------------------
    @GetMapping("/maker") // 메이커 등록
    public String maker(MakerCreateForm makerCreateForm) {
        return "admin_maker";
    }

    @PostMapping("/maker") // 메이커 정보 전송
    public String makerCreate(@Valid MakerCreateForm makerCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin_maker";
        }
        this.makerService.create(makerCreateForm.getMakerName());
        return "admin_maker";
    }

    // -----------------------------------------------------------------------------
    @GetMapping("/product") // 제품 등록
    public String product(Product product) {
        return "admin_product";
    }

    @PostMapping("/product") // 제품 정보 전송
    public String productCreate(String storeName, String makerName, Product product) {
        this.productService.create(storeName, makerName, product);

        return "admin_product";
    }

    // -----------------------------------------------------------------------------
    @GetMapping("/options") // 재고 추가
    public String options(Options options, Principal principal) {

        return "admin_options";
    }

    @PostMapping("/options") // @RequestParam == > 템플릿으로부터 데이터를 바로 직관적으소 쏴서 담긴다
    public String optionsCreate(Options options, String productName, Principal principal) {
        this.optionsService.create(options, productName, principal);
        return "admin_options";
    }

    // -----------------------------------------------------------------------------
    @GetMapping("/store") // 매장 등록
    public String store(Store store) {

        return "admin_store";
    }

    @PostMapping("/store")
    public String storeCreate(@RequestParam("storeName") String storeName, @RequestParam("storeTel") String storeTel,
                              Store store) {

        this.storeService.create(storeName, storeTel);

        return "redirect:/member/admin/store";
    }

    // -----------------------------------------------------------------------------
    @GetMapping("/modify") // 재고 변경
    public String modify(Options options, Principal principal) {

        return "admin_modify";
    }

    @PostMapping("/modify")
    public String modifyCreate(Options options, String productName, Principal principal) {
        this.optionsService.modify(options, productName, principal);
        return "admin_modify";
    }
    // -----------------------------------------------------------------------------

    @GetMapping("/stock")    // 재고 확인
    public String stock(Model model, Principal principal) {

        List<Product> a = this.productService.getList2(principal);

        model.addAttribute("a", a);

        return "admin_stock";
    }

    // -----------------------------------------------------------------------------
    @GetMapping("/reservation")  //매장 예약 확인
    public String storeReservation(Principal principal, Model model) {
        List<Reservation> reservationList = this.reservationService.selectListStore(principal);
        model.addAttribute("reservationList", reservationList);

        return "admin_reservation";
    }

    @RequestMapping(value = "/reservation/detail/{reservationCode}") // 예약 취소
    public String reservationDetail(Model model, @PathVariable("reservationCode") Integer reservationCode){
        Reservation a = this.reservationService.getReservationId(reservationCode);
        model.addAttribute("a",a);

        this.reservationService.delet(a);

        return "admin_reservation_detail";
    }

    @GetMapping("/reservation/detail2/{reservationCode}")   // 판매 완료
    public String sales(@PathVariable("reservationCode") Integer reservationCode){
        Reservation a = this.reservationService.getReservationId(reservationCode);
        this.salesService.create(a);
        this.reservationService.delet(a);

        return "redirect:/member/admin/reservation";
    }

    @GetMapping("/sales")       //판매 내역
    public String sales(Model model ,Principal principal){
        List<Sales> a = this.salesService.getName(principal);

        model.addAttribute("a",a);

        return "admin_sales";
    }

}