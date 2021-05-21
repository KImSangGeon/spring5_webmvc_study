package spring5_webmvc_study.survey;

import java.util.Collections;
import java.util.List;

//  title :  질문제목
//  option : 답변보기 옵션

public class Question {
		private String title;
		private List<String> options;
		
//	@param title 질문제목
//	@param options 답변보기
		
		public Question(String title, List<String> options) {
			this.title = title;
			this.options = options;			
		}
		
//	주관식
//	@param title 질문제목
		public Question(String title) {
			this(title, Collections.<String>emptyList());
		}

		public String getTitle() {
			return title;
		}

		public List<String> getOptions() {
			return options;
		}
		
		public boolean isChoice() {
			return options != null && !options.isEmpty();
		}
		
		
}
