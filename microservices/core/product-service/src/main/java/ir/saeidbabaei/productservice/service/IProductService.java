package ir.saeidbabaei.productservice.service;

import ir.saeidbabaei.productservice.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
	
  public List<Product> findAll();
  
  public Product save(Product product);

  public void deleteById(Long id);
  
  public Optional<Product> findById(Long id);
  
}
