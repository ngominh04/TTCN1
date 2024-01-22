package devmaster.TTCN1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shopping_cart")
public class CartController {
    @GetMapping("/add")
    public String getAddCart(Model model, @PathVariable("idPro") Integer idPro){

        return "user/filter/ProductDetail";
    }
}
