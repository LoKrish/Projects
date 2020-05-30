package com.stockmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stockmanagement.model.Product;
import com.stockmanagement.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	/*
	 * Get all products from the repository
	 */

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productRepository.getAllProducts();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	/*
	 * Get all the products of the Manager
	 */

	@RequestMapping(value = "/{managerID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getProductsOfManager(@PathVariable("managerID") String managerID) {
		List<Product> products = productRepository.getProductsByManager(managerID);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	/*
	 * Create a new product to store in the repository
	 */

	@RequestMapping(value = "/{managerID}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void createNewProduct(@PathVariable("managerID") String managerID, @RequestBody Product product) {
		String productID = generateProductID();
		product.setProductID(productID);
		productRepository.storeProduct(managerID, product);
	}

	private String generateProductID() {
		String existingProductID = productRepository.getProductID();
		if (existingProductID.equals(null)) {
			return "PR0000";
		} else {
			String code = existingProductID.substring(0, 2);
			int index = Integer.parseInt(existingProductID.substring(2));
			index++;
			return code + index;
		}
	}
	
	/*
	 *  Get a particular product
	 */
	
	@RequestMapping(value="/{productID}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProductByID( @PathVariable("productID") String productID){
		Product product=productRepository.getproductByID(productID);
		if(product==null) {
			return new ResponseEntity<Product>(product,HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<Product>(product,HttpStatus.OK);
		}
	}
	
	/*
	 *  Update a particular product
	 */
	
	@RequestMapping(value="/{managerID}/{productID}", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> updateProduct(@PathVariable("productID") String productID, @PathVariable("managerID") String managerID, @RequestBody Product product){
		productRepository.updateProduct(product.getProductID(),product);
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	/*
	 * Delete a particular product
	 */
	
	@RequestMapping(value="/{managerID}/{productID}", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseStatus DeleteProduct(@PathVariable("productID") String productID, @PathVariable("managerID") String managerID) {
		boolean deleted=productRepository.deleteProduct(productID);
		if(deleted) {
			return 
		}else {}
		
	}

}
