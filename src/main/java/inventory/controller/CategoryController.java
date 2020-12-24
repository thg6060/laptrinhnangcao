package inventory.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import inventory.util.Constant;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import inventory.model.Category;
import inventory.service.ProductService;
import inventory.validate.CategoryValidator;

@Controller
public class CategoryController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryValidator categoryValidator;
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		if(binder.getTarget()==null) {
			return;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		if(binder.getTarget().getClass()== Category.class) {
			binder.setValidator(categoryValidator);
		}
	}

	@RequestMapping(value="/category/list")
	public String showCategoryList(Model model, HttpSession session , @ModelAttribute("searchForm") Category category) {
		List<Category> categories = productService.getAllCategory(category);
		if(session.getAttribute(Constant.MSG_SUCCESS)!=null ) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		if(session.getAttribute(Constant.MSG_ERROR)!=null ) {
			model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		
		model.addAttribute("categories", categories);
		return "category-list";

	}
	@GetMapping("/category/add")
	public String add(Model model) {
		model.addAttribute("titlePage", "Add Category");
		model.addAttribute("modelForm", new Category());
		model.addAttribute("viewOnly", false);
		return "category-action";
	}
	@GetMapping("/category/edit/{id}")
	public String edit(Model model , @PathVariable("id") int id) {
		Category category = productService.findByIdCategory(id);
		if(category!=null) {
			model.addAttribute("titlePage", "Edit Category");
			model.addAttribute("modelForm", category);
			model.addAttribute("viewOnly", false);
			return "category-action";
		}
		return "redirect:/category/list";
	}
	@GetMapping("/category/view/{id}")
	public String view(Model model , @PathVariable("id") int id) {
		Category category = productService.findByIdCategory(id);
		if(category!=null) {
			model.addAttribute("titlePage", "View Category");
			model.addAttribute("modelForm", category);
			model.addAttribute("viewOnly", true);
			return "category-action";
		}
		return "redirect:/category/list";
	}
	@PostMapping("/category/save")
	public String save(Model model,@ModelAttribute("modelForm") @Validated Category category,BindingResult result,HttpSession session) {
//		if(result.hasErrors()) {
//			if(category.getId()!=0) {
//				model.addAttribute("titlePage", "Edit Category");
//			}else {
//				model.addAttribute("titlePage", "Add Category");
//			}
//
//			model.addAttribute("modelForm", category);
//			model.addAttribute("viewOnly", false);
//			return "category-action";
//		}
		if(category.getId()!=0) {
			productService.updateCategory(category);
			session.setAttribute(Constant.MSG_SUCCESS, "Update success !");
		}else {
			productService.saveCategory(category);
			session.setAttribute(Constant.MSG_SUCCESS, "Insert success !");
		}
		return "redirect:/category/list";

	}
	@GetMapping("/category/delete/{id}")
	public String delete(Model model , @PathVariable("id") int id, HttpSession session) {
		Category category = productService.findByIdCategory(id);
		if(category!=null) {
			productService.deleteCategory(category);
			session.setAttribute(Constant.MSG_SUCCESS, "Delete success!!!");
		}
		return "redirect:/category/list";
	}

}