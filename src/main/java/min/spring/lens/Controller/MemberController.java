package min.spring.lens.Controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import min.spring.lens.Model.MemberCreateForm;
import min.spring.lens.Model.MemberCreateForm2;
import min.spring.lens.Model.Reservation;
import min.spring.lens.Repository.MemberRepository;
import min.spring.lens.Service.MemberService;
import min.spring.lens.Service.ReservationService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;
	private final MemberRepository memberRepository;
	private final ReservationService reservationService;

	// ---------------------------------------------------------------------------------------------------------------------
	@RequestMapping("/home") // 홈화면 및 로그인 컨트롤러
	public String home() {

		return "home";
	}

	// ---------------------------------------------------------------------------------------------------------------------
	@RequestMapping("/create") // 회원가입 컨트롤러
	public String MemberCreate(MemberCreateForm memberCreateForm) {

		return "memberCreateForm";
	}

	// ---------------------------------------------------------------------------------------------------------------------
	@PostMapping("/create") // 회원가입 정보 전송
	public String submitForm(@Valid MemberCreateForm memberCreateForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "memberCreateForm";
		}

		if (!memberCreateForm.getMemberPw().equals(memberCreateForm.getMemberPw2())) {
			bindingResult.rejectValue("memberPw2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
			return "memberCreateForm";
		}

		memberService.create(memberCreateForm.getMemberId(), memberCreateForm.getMemberPw(),
				memberCreateForm.getMemberName(), memberCreateForm.getMemberPhone(), memberCreateForm.getMemberEmail(),
				memberCreateForm.getMemberBirth());

		return "redirect:/member/success";
	}

	// ---------------------------------------------------------------------------------------------------------------------
	@RequestMapping("/success") // 회원가입 성공 컨트롤러
	public String success(Model model) {
		return "member_success";
	}

	// ---------------------------------------------------------------------------------------------------------------------
	@GetMapping("/login") // 로그인 컨트롤러
	public String login() {
		return "login_form";
	}

	// ---------------------------------------------------------------------------------------------------------------------
	@GetMapping("/admin/page")
	public String apage() {

		return "admin_page";
	}

	// ---------------------------------------------------------------------------------------------------------------------
	@RequestMapping("/admin/create/manager") // 매니저 회원가입 컨트롤러
	public String managerCreate(MemberCreateForm2 memberCreateForm2) {

		return "memberCreateForm2";
	}

	// ---------------------------------------------------------------------------------------------------------------------
	@PostMapping("/admin/create/manager") // 매니저 회원가입 정보 전송
	public String managerCreateForm(@Valid MemberCreateForm2 memberCreateForm2, String storeName,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "memberCreateForm2";
		}

		if (!memberCreateForm2.getMemberPw().equals(memberCreateForm2.getMemberPw2())) {
			bindingResult.rejectValue("memberPw2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
			return "memberCreateForm2";
		}
		this.memberService.create2(memberCreateForm2, storeName);

		return "redirect:/member/success";
	}

	// ---------------------------------------------------------------------------------------------------------------------
	@GetMapping("/reservation/detail")	//예약내역 조회
	public String memberReservation(Principal principal, Model model) {

		List<Reservation> reservationList = this.reservationService.selectList(principal);
		model.addAttribute("reservationList",reservationList);

		return "member_reservation";
	}


	@GetMapping("/reservation/detail/{reservationCode}")	//예약 취소 완료 상세
	public String memberReservationDetail(Model model, @PathVariable("reservationCode") Integer reservationCode){
		Reservation a = this.reservationService.getReservationId(reservationCode);

		model.addAttribute("a",a);

		this.reservationService.delet(a);
		return "member_reservation_detail";
	}

	// --------------------------------------------------------------------------------------------------------------------
	@GetMapping("/product/reservation") // 예약하기 테이블
	public String reservation(Reservation reservation) {

		return "product_reservation";
	}

	@PostMapping("/product/reservation")
	public String reservationCreate(String storeName, Reservation reservation,Principal principal) {

		this.reservationService.create(storeName, reservation,principal);
		return "redirect:/member/success";
	}
	// --------------------------------------------------------------------------------------------------------------------
	
}
