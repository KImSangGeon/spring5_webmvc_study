package spring5_webmvc_study.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.jndi.toolkit.url.Uri;

import spring5_webmvc_study.exception.DuplicateMemberException;
import spring5_webmvc_study.exception.ErrorResponse;
import spring5_webmvc_study.exception.MemberNotFoundException;
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
		public ResponseEntity<Object> member(@PathVariable Long id, HttpServletResponse response) throws IOException {
			Member member = memberDao.selectById(id);
			if(member ==null) {
				
//				1번쨰변경
//				response.sendError(HttpServletResponse.SC_NOT_FOUND);
//				return null;
				
//				2번쨰변경
//				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("no member"));
				throw new MemberNotFoundException();
			}
			return ResponseEntity.status(HttpStatus.OK).body(member);
		}
//		3번째변경
		@ExceptionHandler(MemberNotFoundException.class)
		public ResponseEntity<ErrorResponse> handleNoData(){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("no member"));
		}
		
		
		@PostMapping("/api/members")
		public ResponseEntity<Object> newMember(
																	@RequestBody RegisterRequest regReq, Errors errors,
																	HttpServletResponse response) throws IOException {
				try {
					new RegisterRequestValidator().validate(regReq, errors);
					if(errors.hasErrors()) {
						//번쨰
//						response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//						return ResponseEntity.badRequest().build();
						String errorCodes = errors.getAllErrors()
								.stream()
								.map(error -> error.getCodes()[0])
								.collect(Collectors.joining(","));
						return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								.body(new ErrorResponse("errorcodes = " + errorCodes));
				
					}
					Long newMemberId = registerService.regist(regReq);
					URI uri = URI.create("/api/members/" + newMemberId);
					return ResponseEntity.created(uri).build();
//					response.setHeader("Location", "/api/members/" + newMemberId);
//					response.setStatus(HttpServletResponse.SC_CREATED);
				}catch (DuplicateMemberException e) {
					return ResponseEntity.status(HttpStatus.CONFLICT).build();							
//					response.sendError(HttpServletResponse.SC_CONFLICT);
				}
		}
		

}
