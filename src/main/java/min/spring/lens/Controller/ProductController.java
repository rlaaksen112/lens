package min.spring.lens.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class ProductController {

	// ---------------------------------------------------------------------------------------------------------------------
	@RequestMapping("/product/acuvue") // 아큐브 상품 조회
	public String productList1() {

		return "product_acuvue";
	}

	@RequestMapping("/product/bauschlomb") // 바슈롬 상품 조회
	public String productList2() {

		return "product_bauschlomb";
	}

	@RequestMapping("/product/clalen") // 클라렌 상품 조회
	public String productlist3() {

		return "product_clalen";
	}

	@RequestMapping("/product/alcon") // 알콘 상품 조회
	public String productlist4() {

		return "product_alcon";
	}

	@RequestMapping("/product/coopervision") // 쿠퍼비전 상품 조회
	public String productlist5() {

		return "product_coopervision";
	}

	// ---------------------------------------------------------------------------------------------------------------------


}