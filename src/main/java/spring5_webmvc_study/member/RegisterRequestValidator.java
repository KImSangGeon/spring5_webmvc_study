package spring5_webmvc_study.member;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class RegisterRequestValidator implements Validator {
	
	private static final String emailRegExp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;

	public RegisterRequestValidator() {
		this.pattern = Pattern.compile(emailRegExp);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return RegisterRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	RegisterRequest regSeq = (RegisterRequest) target;
	
	if(regSeq.getEmail() == null || regSeq.getEmail().trim().isEmpty()) {
		errors.rejectValue("email", "required");		
	}else {
		Matcher matcher = pattern.matcher(regSeq.getEmail());
		if(!matcher.matches()) {
			errors.rejectValue("email",	"bad");
		}
	}		
		//name 검증
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		//password 검증
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
		if(!regSeq.getPassword().isEmpty()) {
			if(!regSeq.isPasswordEqualToConfirmPassword()) {
				errors.rejectValue("confirmPassword", "nomatch");
			}
		}
		

	}

}
