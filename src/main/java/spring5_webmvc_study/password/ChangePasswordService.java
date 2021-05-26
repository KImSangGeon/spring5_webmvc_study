package spring5_webmvc_study.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring5_webmvc_study.exception.MemberNotFoundException;
import spring5_webmvc_study.member.Member;
import spring5_webmvc_study.member.MemberDao;
@Service
public class ChangePasswordService {
	@Autowired
		private MemberDao memberDao;

		@Transactional
		public void changePassword(String email, String oldPwd, String newPwd) {
			Member member = memberDao.selectByEmail(email);
			if(member == null) {
				throw new MemberNotFoundException();
			}
			member.changePassword(oldPwd, newPwd);
			memberDao.update(member);
		}
		
}
