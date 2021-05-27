package spring5_webmvc_study.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring5_webmvc_study.exception.DuplicateMemberException;
import spring5_webmvc_study.member.Member;
import spring5_webmvc_study.member.MemberDao;
import spring5_webmvc_study.member.MemberRegisterService;
import spring5_webmvc_study.member.RegisterRequest;
import spring5_webmvc_study.member.RegisterRequestValidator;

@RestController
public class RestMemberController {
		
		@Autowired
		private MemberDao memberDao;
		
		@Autowired
		private MemberRegisterService registerService;
		
		@GetMapping("/api/members")
		public List<Member> members(){
			return memberDao.selectAll();
		}
		
		@GetMapping("/api/members/{id}")
		public Member member(@PathVariable Long id, HttpServletResponse response) throws IOException {
			Member member = memberDao.selectById(id);
			if(member ==null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
				return null;
			}
			return member;
		}
		@PostMapping("/api/members")
		public void newMember(@RequestBody RegisterRequest regReq, Errors errors, HttpServletResponse response) throws IOException {
				try {
//					new RegisterRequestValidator().validate(regReq, errors);
//					if(errors.hasErrors()) {
//						response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//						return;
//					}
					Long newMemberId = registerService.regist(regReq);
					response.setHeader("Location", "/api/members/" + newMemberId);
					response.setStatus(HttpServletResponse.SC_CREATED);
				}catch (DuplicateMemberException e) {
					response.sendError(HttpServletResponse.SC_CONFLICT);
				}
		}
		

}
