package spring5_webmvc_study.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberRegisterService {
		private MemberDao memberDao;

		public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
		}
		
		public Long regist(RegisterRequest req) {
			Member member = memberDao.selectByEmail(req.getEmail());
			if(member != null) {
				throw new DuplicateMemberException("dup email" + req.getEmail());
			}
			Member newMember = new Member(
					req.getEmail(), req.getPassword(), 
					req.getName(), LocalDateTime.now());
			
			//dao 두개면 트렌젝션 써야되는데 셀렉트 사용 update or insert는 안써도 됨.
			memberDao.insert(newMember);
			return newMember.getId();
		}
		
	
}
