package inventory.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import inventory.util.Constant;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import inventory.model.ProductInfo;
import inventory.service.ProductService;
import inventory.validate.ProductInfoValidator;

@Controller
public class ProductInfoController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductInfoValidator ProductInfoValidator;
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		if(binder.getTarget()==null) {
			return;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		if(binder.getTarget().getClass()== ProductInfo.class) {
			binder.setValidator(ProductInfoValidator);
		}
	}

	@RequestMapping(value="/product-info/list")
	public String showProductInfoList(Model model, HttpSession session , @ModelAttribute("searchForm") ProductInfo productInfo) {
		List<ProductInfo> productInfos = productService.getAllProductInfo(productInfo);
		if(session.getAttribute(Constant.MSG_SUCCESS)!=null ) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		if(session.getAttribute(Constant.MSG_ERROR)!=null ) {
			model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		
		model.addAttribute("productInfos", productInfos);
		return "product-info-list";

	}
	@GetMapping("/product-info/add")
	public String add(Model model) {
		model.addAttribute("titlePage", "Add ProductInfo");
		model.addAttribute("modelForm", new ProductInfo());
		model.addAttribute("viewOnly", false);
		return "product-info-action";
	}
	@GetMapping("/product-info/edit/{id}")
	public String edit(Model model , @PathVariable("id") int id) {
		ProductInfo productInfo = productService.findByIdProductInfo(id);
		if(productInfo!=null) {
			model.addAttribute("titlePage", "Edit ProductInfo");
			model.addAttribute("modelForm", productInfo);
			model.addAttribute("viewOnly", false);
			return "product-info-action";
		}
		return "redirect:/product-info/list";
	}
	@GetMapping("/product-info/view/{id}")
	public String view(Model model , @PathVariable("id") int id) {
		ProductInfo productInfo = productService.findByIdProductInfo(id);
		if(productInfo!=null) {
			model.addAttribute("titlePage", "View ProductInfo");
			model.addAttribute("modelForm", productInfo);
			model.addAttribute("viewOnly", true);
			return "product-info-action";
		}
		return "redirect:/product-info/list";
	}
	@PostMapping("/product-info/save")
	public String save(Model model,@ModelAttribute("modelForm") @Validated ProductInfo productInfo,BindingResult result,HttpSession session) {

		if(productInfo.getId()!=0) {
			productService.updateProductInfo(productInfo);
			session.setAttribute(Constant.MSG_SUCCESS, "Update success !");
		}else {
			productService.saveProductInfo(productInfo);
			session.setAttribute(Constant.MSG_SUCCESS, "Insert success !");
		}
		return "redirect:/product-info/list";

	}
	@GetMapping("/product-info/delete/{id}")
	public String delete(Model model , @PathVariable("id") int id, HttpSession session) {
		ProductInfo productInfo = productService.findByIdProductInfo(id);
		if(productInfo!=null) {
			productService.deleteProductInfo(productInfo);
			session.setAttribute(Constant.MSG_SUCCESS, "Delete success!!!");
		}
		return "redirect:/product-info/list";
	}

}