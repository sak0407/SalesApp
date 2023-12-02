package sel.prac.springboot.SalesApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sel.prac.springboot.SalesApp.DAO.ProductRepository;
import sel.prac.springboot.SalesApp.Model.Product;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;


    public List<Product> getAllProduct(){

        return productRepository.findAll();
    }


    public Product getProduct(Long id){

        Optional<Product> product=productRepository.findById(id);

        return product.get();
    }

    public void createProduct(Product product){
        productRepository.save(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

}
