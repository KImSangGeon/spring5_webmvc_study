package spring5_webmvc_study.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import spring5_webmvc_study.exception.WrongIdPasswordException;
import spring5_webmvc_study.member.Member;
import spring5_webmvc_study.member.MemberDao;

@Component
public class AuthService {
		
		@Autowired
		private MemberDao memberDao;	
		
		public AuthInfo authenicate(String email, String password) {
			Member member = memberDao.selectByEmail(email);
			if(member == null) {
				throw new WrongIdPasswordException();
			}
			
			if(!member.matchPassword(password)) {
				throw new WrongIdPasswordException();
			}
			
			return new AuthInfo(member.getId(), member.getEmail(), member.getName());
		}

}
