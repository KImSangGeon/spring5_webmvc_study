package spring5_webmvc_study.exception;

@SuppressWarnings("serial")
public class MemberNotFoundException extends RuntimeException {

	public MemberNotFoundException() {
	}

	public MemberNotFoundException(String message) {
		super("데이터 없음" );
	}

}
