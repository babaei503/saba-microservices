package ir.saeidbabaei.productservice.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ir.saeidbabaei.productservice.model.Product;
import ir.saeidbabaei.productservice.service.IProductService;

/**
 * 
 * @author Saeid Babaei
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {
	
    @Autowired
    private IProductService productService;
    
    /**Saving product.
     * 
     * @param	product		Product information.
     * @return 			 	Response 200 OK. Product info.
     */
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/create", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
    	
    	productService.save(product);
	          
        return ResponseEntity.ok(product);
    }    
    
    /**Get list of all product.
     * 
     * @return				Response 200 OK. List of product.
     */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProductList() {
	      
		  return ResponseEntity.ok(productService.findAll());
	}
	  
	/**Retrieve product by id.
	 * 
	 * @param	id			Product id.
     * @return				Response 200 OK. Product info.
	 * @throw				EntityNotFoundException.     
	 */
	@GetMapping("/edit/{id}")
	public ResponseEntity<Product> retrieveProduct(@PathVariable long id) {
		
			Optional<Product> product = productService.findById(id);

			if (!product.isPresent())
				throw new EntityNotFoundException("Product");

	        return ResponseEntity.ok(product.get());
	}

	/**Update product by id.
	 * 
	 * @param	product 	Product update info.
	 * @param	id			Product id that should be updated. 
	 * @return				Response 200 OK. product info.
	 * @throw				EntityNotFoundException.
	 */
	@PutMapping("/update/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable long id) {

		Optional<Product> productOptional = productService.findById(id);

		if (!productOptional.isPresent())
			throw new EntityNotFoundException("Product");

		product.setId(id);
		
		productService.save(product);
		
		return ResponseEntity.ok(product);
	}
	
	/**Delete product by id.
	 * 
	 * @param	id			Product id that should be deleted.
	 * @return				Response 202 Accepted.
	 * @throw				EntityNotFoundException.
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable long id) {
		
		Optional<Product> productOptional = productService.findById(id);
		
		if (!productOptional.isPresent())
			throw new EntityNotFoundException("Product");
		
		productService.deleteById(id);
		
		return ResponseEntity.accepted().build();
		
	}    
    
}