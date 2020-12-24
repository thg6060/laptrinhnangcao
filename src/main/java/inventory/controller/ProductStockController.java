package inventory.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import inventory.model.ProductInStock;
import inventory.model.ProductInfo;
import inventory.service.ProductService;

@Controller
public class ProductStockController {
	@Autowired
	private ProductService productService;

	@RequestMapping(value="/product-stock/list")
	public String showProductInfoList(Model model, HttpSession session , @ModelAttribute("searchForm") ProductInStock productStock) {
		List<ProductInStock> productInStocks = productService.getAllProductStock(productStock);
//		if(session.getAttribute(Constant.MSG_SUCCESS)!=null ) {
//			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
//			session.removeAttribute(Constant.MSG_SUCCESS);
//		}
//		if(session.getAttribute(Constant.MSG_ERROR)!=null ) {
//			model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
//			session.removeAttribute(Constant.MSG_ERROR);
//		}
		
		model.addAttribute("productInStocks", productInStocks);
		return "product-stock-list";

	}
	@GetMapping("/product-stock/import")
	public String add(Model model) {
		model.addAttribute("titlePage", "Import Product");
		model.addAttribute("modelForm", new ProductInStock());
		model.addAttribute("viewOnly", false);
		model.addAttribute("mapProduct", initMapProduct());
		return "import-product";
	}
	
	@PostMapping("/product-stock/save")
	public String save(Model model,@ModelAttribute("modelForm") @Validated ProductInStock productInStock,BindingResult result,HttpSession session) {
		productService.saveProductInStock(productInStock);
		return "redirect:/product-stock/import";

	}
	@GetMapping("/product-stock/export")
	public String export(Model model) {
		model.addAttribute("titlePage", "Import Product");
		model.addAttribute("modelForm", new ProductInStock());
		model.addAttribute("viewOnly", false);
		model.addAttribute("mapProduct", initMapProduct());
		return "export-product";
	}
	@PostMapping("/product-stock/export-stock")
	public String exportStock(Model model,@ModelAttribute("modelForm") @Validated ProductInStock productInStock,BindingResult result,HttpSession session) {
		productService.exportProductInStock(productInStock);
		return "redirect:/product-stock/import";

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