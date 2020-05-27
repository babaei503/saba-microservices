package ir.saeidbabaei.productservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ir.saeidbabaei.productservice.model.Product;
import ir.saeidbabaei.productservice.repositories.ProductRepository;


@Service
public class ProductService implements IProductService {

  @Autowired
  ProductRepository productRepository;
  
  @Override
  public List<Product> findAll() {
    List<Product> productList = new ArrayList<>();
    productRepository.findAll().forEach(productList::add);
    return productList;
  }
  
  @Override
  public Product save(Product product) {
    return productRepository.save(product);
  }

  @Override
  public Optional<Product> findById(Long id) {
    Optional<Product> product = productRepository.findById(id);
    return product;
  }

  @Override
  public  void deleteById(Long id){
	productRepository.deleteById(id);
  }
  
}
