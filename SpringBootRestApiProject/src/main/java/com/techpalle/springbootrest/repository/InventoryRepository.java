package com.techpalle.springbootrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techpalle.springbootrest.entity.Product;

@Repository
public interface InventoryRepository extends JpaRepository<Product, Integer>{
 //if we dont extend jpa repository
	//we have to write all hibernate code to perform crud operation
}
