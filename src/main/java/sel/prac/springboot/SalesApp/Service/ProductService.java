package sel.prac.springboot.SalesApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sel.prac.springboot.SalesApp.DAO.ProductRepository;
import sel.prac.springboot.SalesApp.Model.Product;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;


    public Page<Product> getAllProduct(int pageNum,String sortField,String sortDir){



        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc")? Sort.by(sortField).ascending():Sort.by(sortField).descending());

        return productRepository.findAll(pageable);
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
