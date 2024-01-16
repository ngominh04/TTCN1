package devmaster.TTCN1.controller;

import devmaster.TTCN1.respository.ProductRespon;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class CommonController {
    @Autowired
    ProductRespon productRespon;

    @GetMapping("/")
    public String showUser(Model model){
        model.addAttribute("product",productRespon.getAllProduct());
        return "user/index";
    }

    @GetMapping("/1")
    public String showAdmin(){
        return "admin/index";
    }
}
