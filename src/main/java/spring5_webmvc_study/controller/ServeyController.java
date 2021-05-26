package spring5_webmvc_study.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xpath.internal.operations.Mod;

import spring5_webmvc_study.survey.AnsweredData;
import spring5_webmvc_study.survey.Question;

@Controller
@RequestMapping("/servey")
public class ServeyController {
	
//		@GetMapping
//		public String form(Model model) {
//			List<Question> questions = createQuestion();
//			model.addAttribute("questions", questions);
//			return "servey/serveyForm";
//		}	
	
		@GetMapping
		public ModelAndView form() {
			List<Question> questions = createQuestion();
			ModelAndView mav = new ModelAndView();
			mav.addObject("questions", questions);
			mav.setViewName("servey/serveyForm");
			return mav;
		}

		@PostMapping
		public String submit(@ModelAttribute("ansData") AnsweredData data) {
			return "servey/submitted";
		}
		
		private List<Question> createQuestion() {
			Question q1 = new Question("당신의 역활은 무엇입니까?", Arrays.asList("서버", "프론트", "풀스택"));
			Question q2 = new Question("많이 사용하는 개발도구는 무엇입니까?", Arrays.asList("이클립스", "인텔리J", "서브라임"));
			Question q3 = new Question("하고 싶은 말을 적어주세요.");			
			return Arrays.asList(q1, q2, q3);
		}
		
	
}
