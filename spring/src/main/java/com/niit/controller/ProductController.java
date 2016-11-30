package com.niit.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.niit.model.Categories;
import com.niit.model.Product;
import com.niit.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;

	// setter + getter
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// browse all products including Angular JS
	@RequestMapping(value = "/getProductsList")
	public @ResponseBody List<Product> getProductsListInJSON() {
		return productService.getAllProducts();
	}

	// To view the angularJS productList
	@RequestMapping("/productList")
	public String getProducts() {
		return "productList";
	}

	/*
	 * @RequestMapping("/productListAngular") public String getProducts() {
	 * return "productListAngular"; }
	 */

	// displays all the products in the table
	@RequestMapping(value = "/getallproducts")
	public ModelAndView getAllProducts() {
		List<Product> allproducts = productService.getAllProducts();
		ModelAndView mv = new ModelAndView("productList", "allproducts", allproducts);
		return mv;
	}

	// view product details if productId is clicked
	@RequestMapping(value = "/getProductsByProductId/{productId}")
	public ModelAndView getProductsByProductId(@PathVariable(value = "productId") int productId) {
		Product product = productService.getProductByProductId(productId);
		return new ModelAndView("productPage", "product", product);
	}

	// to delete a product
	@RequestMapping(value = "/admin/delete/{productId}")
	public String deleteProduct(@PathVariable(value = "productId") int productId) {
		productService.deleteProduct(productId);
		Path paths = Paths
				.get("F:\\Codes\\Eclipse\\Workspace_DT7_4\\spring\\src\\main\\webapp\\WEB-INF\\resources\\images\\"
						+ productId + ".png");

		if (Files.exists(paths)) {
			try {

				Files.delete(paths);

			} catch (IOException e) {
			}
		}

		return "redirect:/getallproducts";
	}

	// ADD PRODUCT
	// method 1 for add product
	@RequestMapping(value = "/admin/product/addProduct", method = RequestMethod.GET)
	public String getProductForm(Model model) {
		Product product = new Product();
		Categories categories = new Categories();
		categories.setcId(1);
		product.setCategory(categories);
		model.addAttribute("productFormObj", product);
		return "productForm";
	}

	// method 2 for addproduct
	@RequestMapping(value = "/admin/product/addProduct", method = RequestMethod.POST)
	public String addProduct(@Valid @ModelAttribute(value = "productFormObj") Product product, BindingResult result) {
		if (result.hasErrors()) {

			return "productForm";

		} else {
			productService.addProduct(product);

			MultipartFile productImage = product.getProductImage();
			if (productImage != null && !productImage.isEmpty()) {
				Path paths = (Path) Paths
						.get("F:\\Codes\\Eclipse\\Workspace_DT7_4\\spring\\src\\main\\webapp\\WEB-INF\\resources\\images\\"
								+ product.getProductId() + ".png");

				try {
					productImage.transferTo(new File(paths.toString()));
				} catch (IllegalStateException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}

			}

			return "redirect:/getallproducts";
		}
	}

	// EDIT PRODUCT
	// method 1 for edit product
	@RequestMapping("/admin/product/editProduct/{productId}")
	public ModelAndView getEditForm(@PathVariable(value = "productId") int productId) {
		Product product = this.productService.getProductByProductId(productId);
		return new ModelAndView("editProductForm", "editProductObj", product);
	}

	// method 2 for edit product
	@RequestMapping(value = "/admin/product/editProduct", method = RequestMethod.POST)
	public String editProduct(@ModelAttribute(value = "editProductObj") Product product) {
		productService.editProduct(product);
		return "redirect:/getallproducts";
	}
}
