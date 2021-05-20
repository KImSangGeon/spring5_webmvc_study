package spring5_webmvc_study.survey;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/servey")
public class ServeyController {
	
		@GetMapping
		public String form() {
			return "servey/serveyForm";
		}
		
		@PostMapping
		public String submit(@ModelAttribute("ansData") AnsweredData data) {
			return "servey/submitted";
		}
	
}
