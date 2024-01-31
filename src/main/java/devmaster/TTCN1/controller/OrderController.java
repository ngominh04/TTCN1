package devmaster.TTCN1.controller;

import devmaster.TTCN1.domain.Cart;
import devmaster.TTCN1.projection.ICountCart;
import devmaster.TTCN1.respository.CartRespon;
import devmaster.TTCN1.respository.OrderRespon;
import devmaster.TTCN1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRespon orderRespon;
    @Autowired
    CartRespon cartRespon;

    @GetMapping("/showOrder/{idCus}")
    public String showOrder(Model model, @PathVariable("idCus") Integer idCus){
        ICountCart cart = cartRespon.getCount(idCus);
        model.addAttribute("count",cart.getCount());
        model.addAttribute("cart",cartRespon.getCart(idCus));
        return "user/order/showOrder";
    }
    @PostMapping("/{}")
    public String order(){

        return "user/order/notifyOrder";
    }

}
