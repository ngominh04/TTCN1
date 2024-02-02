package devmaster.TTCN1.controller;

import devmaster.TTCN1.domain.Cart;
import devmaster.TTCN1.domain.TransportMethod;
import devmaster.TTCN1.projection.ICountCart;
import devmaster.TTCN1.respository.*;
import devmaster.TTCN1.service.OrderService;
import devmaster.TTCN1.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRespon orderRespon;
    @Autowired
    CartRespon cartRespon;
    @Autowired
    ReceiverRespon receiverRespon;
    @Autowired
    TransportRespon transportRespon;
    @Autowired
    TransportService transportService;
    @Autowired
    PaymentRespon paymentRespon;

    @GetMapping("/showOrder/{idCus}")
    public String showOrder(Model model, @PathVariable("idCus") Integer idCus){
        ICountCart cart = cartRespon.getCount(idCus);
        model.addAttribute("count",cart.getCount());
        model.addAttribute("cart",cartRespon.getCart(idCus));
        model.addAttribute("receiver",receiverRespon.getAllReceiver(idCus));
        model.addAttribute("transport",transportRespon.getAllById());
        model.addAttribute("payment",paymentRespon.getAllById());
        List<Cart> carts = cartRespon.getCart(idCus);
        double total = 0;
        for (Cart cartItem:carts) {
            total = total+ cartItem.getQuantity()*cartItem.getPrice();
        }
        model.addAttribute("total",total);
        return "user/order/showOrder";
    }
    @PostMapping("/{}")
    public String order(){

        return "user/order/notifyOrder";
    }

}
