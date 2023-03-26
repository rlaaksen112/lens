package min.spring.lens.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class MainController {


		@RequestMapping("/")		//로그인
		public String root() {

			return "redirect:/member/home";
		}
}
