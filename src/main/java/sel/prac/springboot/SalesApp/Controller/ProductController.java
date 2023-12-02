package sel.prac.springboot.SalesApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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



//this is basic curd operaton with themelyf

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        return viewPage(model, 1);
    }

    @RequestMapping("/page/{pageNum}")
    public String viewPage(Model model,@PathVariable(value = "pageNum") int pageNum){

        Page<Product> page=productService.getAllProduct(pageNum);
        List<Product> productList=page.getContent();
        model.addAttribute("currentPage",pageNum);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
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
