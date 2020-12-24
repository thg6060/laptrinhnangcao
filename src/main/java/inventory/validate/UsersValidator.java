package inventory.validate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import inventory.model.Category;
import inventory.model.Profile;
import inventory.model.Users;
import inventory.service.UserService;

@Component
public class UsersValidator implements Validator {
	@Autowired
	private UserService userService;

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == Profile.class;
	}

	public void validate(Object target, Errors errors) {
		System.out.println("alo user");
	
       
		Profile user = (Profile) target;
		System.out.println("workdie: "+user.getUser().getName());
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.userName", "msg.username");
		ValidationUtils.rejectIfEmpty(errors, "user.password", "msg.password");

		List<Users> users = userService.findByProperty("userName", user.getUser().getUserName());
		if (users.size() != 0) {
			errors.rejectValue("user.userName", "msg.existedUsername");
		}

	}

}