package devmaster.TTCN1.controller;

import devmaster.TTCN1.domain.Category;
import devmaster.TTCN1.domain.Product;
import devmaster.TTCN1.respository.EvaluateRespon;
import devmaster.TTCN1.respository.ProductRespon;
import devmaster.TTCN1.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRespon productRespon;
    @Autowired
    ProductService productService;
    @Autowired
    EvaluateRespon evaluateRespon;
    // click vào tắt ở Is active -> chuyển isactive = 0
    @GetMapping("/activeProOff/{idPro}")
    public String activeProOff(@PathVariable("idPro")Integer idPro){
        Product product = productRespon.findAllById(idPro);
        product.setIsactive((byte) 0);
        productRespon.save(product);
        return "redirect:/admin#product";
    }
    // click vào bật ở Is active -> chuyển isactive = 1
    @GetMapping("/activeProOn/{idPro}")
    public String activeProOn(@PathVariable("idPro")Integer idPro){
        Product product = productRespon.findAllById(idPro);
        product.setIsactive((byte) 1);
        productRespon.save(product);
        return "redirect:/admin#product";
    }
    // click vào tắt ở Is delete -> chuyển isdelete = 0
    @GetMapping("/deleteProOff/{idPro}")
    public String deleteProOff(@PathVariable("idPro")Integer idPro){
        Product product = productRespon.findAllById(idPro);
        product.setIsdelete((byte)0);
        productRespon.save(product);
        return "redirect:/admin#product";
    }
    // click vào bật ở Is delete -> chuyển isdelete = 1
    @GetMapping("/deleteProOn/{idPro}")
    public String deleteProOn(@PathVariable("idPro")Integer idPro){
        Product product = productRespon.findAllById(idPro);
        product.setIsdelete((byte) 1);
        productRespon.save(product);
        return "redirect:/admin#product";
    }
    // xem chi tiết product
    @GetMapping("/detailPro/{idPro}")
    public String detailPro(Model model,@PathVariable("idPro")Integer idPro){
        model.addAttribute("productImages",productRespon.getAllImage(idPro));
        model.addAttribute("productChiTiet",productRespon.getProductChiTiet(idPro));
        model.addAttribute("cate",productRespon.getProByIdCate(idPro));
        model.addAttribute("evaluate",evaluateRespon.getAllEvaluateByPro(idPro));
        return "/admin/product/detailPro";
    }

}
