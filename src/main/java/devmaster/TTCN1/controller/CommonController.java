package devmaster.TTCN1.controller;

import devmaster.TTCN1.domain.Customer;
import devmaster.TTCN1.respository.CartRespon;
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
    @Autowired
    CartRespon cartRespon;
    @GetMapping("/")
    public String showUser(Model model,HttpSession session){
        session.getAttribute("saveCus");
        if (session.getAttribute("saveCus") != null){
            Customer customer = (Customer) session.getAttribute("saveCus");
            int count = customer.getId();
            System.out.println(cartRespon.getCount(count));
            model.addAttribute("countCart",cartRespon.getCount(count));// đếm số lượng cartItem khi đăng nhập
        }

        model.addAttribute("product",productRespon.getAllProduct());
        return "user/index";
    }

    @GetMapping("/admin")
    public String showAdmin(){
        return "admin/index";
    }
}
