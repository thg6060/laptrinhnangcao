package inventory.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import inventory.model.ProductInfo;
import inventory.model.ProductInStock;
import inventory.service.ProductService;

@Controller
public class ImportProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("/import-product/add")
	public String add(Model model) {
		model.addAttribute("titlePage", "Import Product");
		model.addAttribute("modelForm", new ProductInStock());
		model.addAttribute("viewOnly", false);
		model.addAttribute("mapProduct", initMapProduct());
		return "goods-issue-action";
	}
	
	@PostMapping("/import-product/save")
	public String save(Model model,@ModelAttribute("modelForm") @Validated ProductInStock productInStock,BindingResult result,HttpSession session) {
		productService.saveProductInStock(productInStock);
		return "redirect:/import-product/add";

	}
	
	private Map<Integer,String> initMapProduct(){
		List<ProductInfo> productInfos = productService.getAllProductInfo(null);
		Map<Integer, String> mapProduct = new HashMap<>();
		for(ProductInfo productInfo : productInfos) {
			mapProduct.put(productInfo.getId(),productInfo.getName());
		}
		
		return mapProduct;
	}
	

}