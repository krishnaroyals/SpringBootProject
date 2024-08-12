package com.techpalle.springbootrest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techpalle.springbootrest.entity.Product;
import com.techpalle.springbootrest.repository.InventoryRepository;

@Service
public class InventoryService {
	//we need repository object
	private final InventoryRepository repository;
	@Autowired
	public InventoryService(InventoryRepository repository) {
		this.repository=repository;
	}
	
	//service layer will have business logic + minimum 5 funtions for crud
	//1.a function required to insert row into Product table
	public Product saveProduct(Product p) {
		//save function returns product we need to catch the product and return that obj
		Product obj=repository.save(p);
		return obj;
	}
	
	
	//2.function to read all products
	public List<Product> readAllProducts() {
		List<Product> products=repository.findAll();//it will return multiple products thats why create list
		return products;
	}
	//3.function to update based on id
	public Product updateProduct(int pno,Product np) {
		Optional<Product> temp=repository.findById(pno);
		if(temp.isPresent()) {
			Product p=temp.get();
			p.setPname(np.getPname());
			p.setPprice(np.getPprice());
			p.setPqty(np.getPqty());
			return repository.save(p);
		}
		else {
			throw new RuntimeException("product not found");
		}
	}
	//4.function to delete product based in id primary key
	public void deleteProduct(int pno) {
		repository.deleteById(pno);
	}
	//5.function to read one product
	public Optional<Product> getProduct(int pno)
	{
	return repository.findById(pno);
	}
}
