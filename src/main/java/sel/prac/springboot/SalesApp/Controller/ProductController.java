package sel.prac.springboot.SalesApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sel.prac.springboot.SalesApp.Model.Product;
import sel.prac.springboot.SalesApp.Service.ProductService;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/")
    public String veiwHomePage(Model model){

        List<Product> productList=productService.getAllProduct();
        model.addAttribute("productList",productList);
        return "index";
    }

    @RequestMapping("/new")
    public String showNewProductPage(Model model){
        Product product = new Product();
        model.addAttribute("product", product);

        return "new_product";
    }
    @PostMapping("/save" )
    public String saveProduct(@ModelAttribute("product") Product product){
        productService.createProduct(product);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editUpdateProduct(@PathVariable(value="id") Long id){
        ModelAndView mav=new ModelAndView("edit_product");
        Product product=productService.getProduct(id);
        mav.addObject("product",product);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id){
        productService.deleteProduct(id);
        return "redirect:/";
    }
}