package com.techpalle.springbootrest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techpalle.springbootrest.entity.Product;
import com.techpalle.springbootrest.service.InventoryService;

@RestController
//it says it is rest api
@RequestMapping("/api/v1")
//it says the url of webservice
public class InventoryController {
	private final InventoryService service;
	
	@Autowired
	public InventoryController(InventoryService service) {
		this.service=service;
	}
	
	@PostMapping("/saveproduct")
	public ResponseEntity<Product> saveProduct(@RequestBody Product p) {
		//from here we need to call service layer saveproduct method
		Product obj=service.saveProduct(p);
		return ResponseEntity.ok(obj);
	}
	
	//read all method
	@GetMapping("/getproducts")
	public List<Product> getProducts() {
		List<Product> products=service.readAllProducts();
		return products;
	}
	//update product
	@PutMapping("/updateproduct/{pno}")
	public Product updateProduct(@PathVariable int pno,@RequestBody Product np) {
		return service.updateProduct(pno, np);
		
	}
	//delete procuct
	@DeleteMapping("/deleteproduct/{pno}")
	public void deleteProduct(@PathVariable int pno) {
		service.deleteProduct(pno);
	}
	//read one product
	@GetMapping("/getproducts/{pno}")
	public ResponseEntity<Product> getProduct(@PathVariable int pno) {
	        Optional<Product> temp = service.getProduct(pno);
	        if(temp.isPresent()){
	        	Product p=temp.get();
	        	return ResponseEntity.ok(p);
	        }
	        else{
	        	throw new RuntimeException("product not avaialble");
	        }
	 
	}
	
}
