package tn.esprit.fundme.controller;

import java.util.List;

import javax.batch.operations.JobExecutionAlreadyCompleteException;
import javax.batch.operations.JobRestartException;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.fundme.batch.BatchLauncher;
import tn.esprit.fundme.entities.Product;
import tn.esprit.fundme.services.ProductsService;

@RestController
@RequestMapping("product")
public class ProductsController {
	@Autowired
	ProductsService ProductsService ;
	
	@Autowired
    private BatchLauncher batchLauncher;

    @GetMapping("/batch")
    public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyCompleteException, JobRestartException, JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, org.springframework.batch.core.repository.JobRestartException {
         return batchLauncher.run();
    }
	@GetMapping("/retrieveAllProduct")
	@ResponseBody
	public List<Product> getGuarantor(){
		List<Product>listProduct =ProductsService.retrieveAllProduct();
		return listProduct;
	}
	
	
	@GetMapping("/retrieveAllProduct/{operateur-id}")
	@ResponseBody
	public Product retrieveProduct(@PathVariable("Product-id") Long ProductId) {
	return ProductsService.retrieveProduct(ProductId);
	}

	
	@PostMapping("/addProduct")
	@ResponseBody
	public Product Product(@RequestBody Product G)
	{
			return ProductsService.addProduct(G);

	}
	
	@DeleteMapping("/removeProduct/{operateur-id}")
	@ResponseBody
	public void removeProduct(@PathVariable("Product-id") Long ProductId) {
		ProductsService.removeProduct(ProductId);
	}

	@PutMapping("/modifyProduct")
	@ResponseBody
	public Product modifyProduct(@RequestBody Product Product) {
	return ProductsService.updateProduct(Product);
	}
}
