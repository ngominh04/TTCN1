package devmaster.TTCN1.controller;

import devmaster.TTCN1.domain.*;
import devmaster.TTCN1.projection.ICountCart;
import devmaster.TTCN1.respository.*;
import devmaster.TTCN1.service.OrderService;
import devmaster.TTCN1.service.ParamService;
import devmaster.TTCN1.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    ParamService paramService;
    @Autowired
    CustomerRespon customerRespon;

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
    @PostMapping("/updateOrder/{idCus}")
    public String order(@PathVariable("idCus") Integer idCus,
                        @RequestParam(value = "idRece",required = false)Integer idRece,
                        @RequestParam(value = "idTrans",required = false)Integer idTrans,
                        @RequestParam(value = "idPayment",required = false)Integer idPayment,
                        @RequestParam(value = "notes",required = false)String notes){
//        Customer customer = customerRespon.getCustomerById(idCus);

        String idOrder = UUID.randomUUID().toString().substring(0,10); // random idOrder
        Receiver receiver = receiverRespon.findAllById(idRece);
        List<Cart> carts = cartRespon.getCart(idCus);
        double total = 0;
        for (Cart cartItem:carts) {
            total = total+ cartItem.getQuantity()*cartItem.getPrice();
        }

        Order order = new Order();
        order.setAddress(receiver.getAddress());
        order.setIdCustomer(idCus);
        order.setIdOrders(idOrder);
        order.setNameReciver(receiver.getName());
        order.setNotes(notes);
        order.setOrdersDate(String.valueOf(LocalDate.now()));
        order.setPhone(receiver.getPhone());
        order.setTotalMoney(total+transportRespon.getById(idTrans).getPrice());
        orderRespon.save(order);

        System.out.println("idRece: "+idRece+" - idTrans: "+idTrans+" - idPayment: "
                +idPayment+" -notes: "+notes+" - 1: "+LocalDateTime.now()+" -2: "+LocalDate.now()+" -3: "+Instant.now());
        return "user/order/notifyOrder";
    }

}
