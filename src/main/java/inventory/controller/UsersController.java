package inventory.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import inventory.model.Profile;
import inventory.model.Role;
import inventory.model.UserRole;
import inventory.model.Users;
import inventory.service.RoleService;
import inventory.service.UserRoleService;
import inventory.service.UserService;
import inventory.util.Constant;
import inventory.validate.UsersValidator;

@Controller
public class UsersController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService urService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UsersValidator usersValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {

		if (binder.getTarget() == null)
			return;
		if (binder.getTarget().getClass() == Profile.class) {
			
			binder.setValidator(usersValidator);
		      
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/user/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String showUserList(Model model, HttpSession session, @ModelAttribute("searchForm") Users user) {
		List<Users> users = userService.getAllUser(user);
		if (session.getAttribute(Constant.MSG_SUCCESS) != null) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		if (session.getAttribute(Constant.MSG_ERROR) != null) {
			model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		model.addAttribute("users", users);
		return "user-list";
	}

	@GetMapping("/user/add")
	public String add(Model model) {
		List<Role> listRole = roleService.getAllRole(new Role());
		List<String> listRoleName = new ArrayList<String>();
		for(Role items:listRole)
		{
			listRoleName.add(items.getRoleName());

		}
		
		model.addAttribute("titlePage", "Add User");
		model.addAttribute("modelFormUser", new Profile());
		model.addAttribute("listRoleName", listRoleName);
		model.addAttribute("viewOnly", false);
		model.addAttribute("readOnly", false);
		return "user-list-action";
	}

	@GetMapping("/user/edit/{id}")
	public String edit(Model model, @PathVariable("id") int id) {
		Users user = userService.findByIdUser(id);
		List<UserRole> ur = urService.findByProperty("userId", id);	
		Role role = roleService.findByIdURole(ur.get(0).getRoleId());
		//get list role name
		List<Role> listRole = roleService.getAllRole(new Role());
		List<String> listRoleName = new ArrayList<String>();
		for(Role items:listRole)
		{
			listRoleName.add(items.getRoleName());

		}
		Profile profile = new Profile();
		profile.setRole(role);
		profile.setUser(user);
		
		if (user != null) {
			model.addAttribute("titlePage", "Edit User");
			model.addAttribute("modelFormUser", profile);
			model.addAttribute("listRoleName", listRoleName);
			model.addAttribute("viewOnly", false);
			model.addAttribute("readOnly", true);
			return "user-list-action";
		}
		return "redirect:/user/list";
	}

	@GetMapping("/user/view/{id}")
	public String view(Model model, @PathVariable("id") int id) {
		Users user = userService.findByIdUser(id);
		List<UserRole> ur = urService.findByProperty("userId", user.getId());
		Role role = roleService.findByIdURole(ur.get(0).getRoleId());
		
		//get list role name
		List<Role> listRole = roleService.getAllRole(new Role());
		List<String> listRoleName = new ArrayList<String>();
		for(Role items:listRole)
		{
			listRoleName.add(items.getRoleName());

		}
		Profile profile = new Profile();
		profile.setRole(role);
		profile.setUser(user);
		
		if (user != null) {
			model.addAttribute("titlePage", "View User");
			model.addAttribute("modelFormUser",profile );
			model.addAttribute("listRoleName", listRoleName);
			model.addAttribute("viewOnly", true);
			model.addAttribute("readOnly", true);
			return "user-list-action";
		}
		return "redirect:/user/list";
	}

	@PostMapping("/user/save")
	public String save(Model model,@ModelAttribute("modelFormUser")@Validated Profile profile, BindingResult result,
			HttpSession session) {
		//get list role name
		List<Role> listRole = roleService.getAllRole(new Role());
		List<String> listRoleName = new ArrayList<String>();
		for(Role items:listRole)
		{
			listRoleName.add(items.getRoleName());

		}
		System.out.println("day nek:"+profile.getUser().getName());
		System.out.println("day nek:"+profile.getRole().getRoleName());
		
		usersValidator.validate(profile, result);
		//get roleid
		List<Role> role = roleService.findByProperty("roleName", profile.getRole().getRoleName());

		
		if (result.hasErrors()) {
			if (profile.getUser().getId() != 0) {
				//update userrole
				List<UserRole> ur = urService.findByProperty("userId", profile.getUser().getId());
				ur.get(0).setRoleId(role.get(0).getId());
				urService.updateUserRole(ur.get(0));
				System.out.println("day nek:"+ur.get(0).getId());
				userService.updateUser(profile.getUser());
				model.addAttribute("titlePage", "Edit user");
				
			} else {
				model.addAttribute("titlePage", "Add user");
			}
			model.addAttribute("modelFormUser", profile);
			model.addAttribute("listRoleName", listRoleName);
			model.addAttribute("viewOnly", false);
			
			return "user-list-action";
		}

		if (profile.getUser().getId() != 0) {

			//update userrole
			List<UserRole> ur = urService.findByProperty("userId", profile.getUser().getId());
			ur.get(0).setRoleId(role.get(0).getId());
			urService.updateUserRole(ur.get(0));
			System.out.println("day nek:"+ur.get(0).getId());
			userService.updateUser(profile.getUser());
			session.setAttribute(Constant.MSG_SUCCESS, "Update success !");
		} else {
			userService.saveUser(profile.getUser());
			List<Users> us = userService.findByProperty("userName", profile.getUser().getUserName());	
			UserRole newUr = new UserRole();
			newUr.setUserId(us.get(0).getId());
			newUr.setRoleId(role.get(0).getId());
			urService.saveUserRole(newUr);
			session.setAttribute(Constant.MSG_SUCCESS, "Insert success !");
		}
		return "redirect:/user/list";

	}

	@GetMapping("/user/delete/{id}")
	public String delete(Model model, @PathVariable("id") int id, HttpSession session) {
		Users user = userService.findByIdUser(id);
		List<UserRole> ur = urService.findByProperty("userId",id);
		
		if (user != null) {
			userService.deleteUser(user);
			urService.deleteUserRole(ur.get(0));
			session.setAttribute(Constant.MSG_SUCCESS, "Delete success!!!");
		}
		return "redirect:/user/list";
	}

}
