package devmaster.TTCN1.controller;

import devmaster.TTCN1.domain.*;
import devmaster.TTCN1.projection.ICountCart;
import devmaster.TTCN1.projection.IEvaluate;
import devmaster.TTCN1.projection.IOrder;
import devmaster.TTCN1.projection.IOrderDetails;
import devmaster.TTCN1.respository.*;
import devmaster.TTCN1.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
    @Autowired
    OrderPaymentRespon orderPaymentRespon;
    @Autowired
    OrderPaymentService orderPaymentService;
    @Autowired
    OrderTransportRespon orderTransportRespon;
    @Autowired
    OrderTransportService orderTransportService;
    @Autowired
    ProductRespon productRespon;
    @Autowired
    OrderDetailRespon orderDetailRespon;
    @Autowired
    CartService cartService;
    @Autowired
    EvaluateRespon evaluateRespon;
    @Autowired
    EvaluateService evaluateService;

    @GetMapping("/showOrder/{idCus}")
    public String showOrder(Model model, @PathVariable("idCus") Integer idCus,HttpSession session){

        ICountCart cart = cartRespon.getCount(idCus);
        List<Cart> carts = cartRespon.getCart(idCus);
        if(carts.size() == 0){
            model.addAttribute("message","Giỏ hàng đang trống,quý khách hãy chọn sản phẩm để mua !");
            return "/user/cart/Cart";
        }else {
            model.addAttribute("count",cart.getCount());
            model.addAttribute("cart",cartRespon.getCart(idCus));
            model.addAttribute("receiver",receiverRespon.getAllReceiver(idCus));
            model.addAttribute("transport",transportRespon.getAllById());
            model.addAttribute("payment",paymentRespon.getAllById());
            double total = 0;
            for (Cart cartItem:carts) {
                total = total+ cartItem.getQuantity()*cartItem.getPrice();
            }
            model.addAttribute("total",total);
            return "user/order/showOrder";
        }
    }


    // các status của order:
    // 1: đã đặt hàng, chờ xác nhận, 2: Đang giao, 3: Đã giao đến người dùng,4: người dùng xác nhận đã nhận được hàng, 0: Người dùng hủy hàng

    @PostMapping("/updateOrder/{idCus}") // lưu đồng thời bảng order , order_detail, order_payment,order_transport
    public String updateOrder(@PathVariable("idCus") Integer idCus,
                              @RequestParam(value = "idRece",required = false)Integer idRece,
                              @RequestParam(value = "idTrans",required = false)Integer idTrans,
                              @RequestParam(value = "idPayment",required = false)Integer idPayment,
                              @RequestParam(value = "notes",required = false)String notes,
                              Model model,HttpSession session){

        if(idRece == null){ // ktra địa chỉ nhận , chưa có thì bắn lỗi
            showOrder(model,idCus,session); // gọi lại hàm showOrder ở trên để lấy lấy lại thông tin order7
            model.addAttribute("message","Chưa có địa chỉ nhận hàng");
            return "user/order/showOrder";
        }else {
            // save order
            Order order = new Order();

            String idOrder = UUID.randomUUID().toString().substring(0,10);
            order.setIdOrders(idOrder);

            order.setOrdersDate(String.valueOf(LocalDateTime.now()));
            order.setStatus(1); // đặt trạng thái đã đặt hàng từ người dùng
            order.setNotes(notes);

            Receiver receiver = receiverRespon.findAllById(idRece);
            order.setNameReciver(receiver.getName());
            order.setPhone(receiver.getPhone());
            order.setAddress(receiver.getAddress());
            order.setIdCustomer(idCus);

            List<Cart> carts = cartRespon.getCart(idCus);
            double total = 0;
            for (Cart cartItem:carts) {
                total = total+ cartItem.getQuantity()*cartItem.getPrice();
            }
            TransportMethod transport = transportRespon.getById(idTrans);
            order.setTotalMoney(total+transport.getPrice());

            orderService.save(order);
            // end save order

            // save order_payment
            OrdersPayment ordersPayment = new OrdersPayment();
            ordersPayment.setIdord(order.getId());
            ordersPayment.setIdpayment(idPayment);
            ordersPayment.setTotal(0);
            ordersPayment.setNotes(paymentRespon.getById(idPayment).getName());
            orderPaymentService.save(ordersPayment);

            // save order_transport
            OrdersTransport ordersTransport = new OrdersTransport();
            ordersTransport.setIdtransport(idTrans);
            ordersTransport.setNotes(transportRespon.getById(idTrans).getName());
            ordersTransport.setIdord(order.getId());
            ordersTransport.setTotal(transportRespon.getById(idTrans).getPrice());
            orderTransportService.save(ordersTransport);

            // save order_detail
            List<Cart> item = cartRespon.getCart(idCus);
            for (Cart item1 : item) {
                OrdersDetail orderDetail = new OrdersDetail();
                orderDetail.setIdord(order.getId());
                orderDetail.setQty(item1.getQuantity());
                orderDetail.setIdproduct(item1.getIdPrduct());
                orderDetail.setPrice(item1.getPrice());
                //save oder detail
                orderDetailRespon.save(orderDetail);
                // cập nhật lại số lượng sản phẩm
                int idProduct =  item1.getIdPrduct();
                Product product = productRespon.findAllById(idProduct);
                product.setQuatity(product.getQuatity()-item1.getQuantity());
                // cập nhật lại trạng thái nếu hết hàng
                if(product.getQuatity() == 0){
                    product.setIsactive((byte) 0);
                }
                productRespon.save(product);
            }

            // xóa cart của ng dùng
            for (Cart cart:carts) {
                cartService.delete(cart);
            }
            return "user/order/notifyOrder";
        }
    }

    // order 1 , khi người dùng ấn đặt hàng, admin vào xác nhận và chuẩn bị đơn
    @GetMapping("/order1/{idOrder}")
    public String order1(Model model,@PathVariable("idOrder")Integer idOrder){
        Order order = orderRespon.finById(idOrder);
        order.setStatus(2);
        orderRespon.save(order);
        return "redirect:/admin#order";
    }
    // hiển thị đơn hàng ở trang người dùng
    @GetMapping("/order/{idCus}")
    public String order(Model model,@PathVariable("idCus")Integer idCus){
        model.addAttribute("order2",orderRespon.getOrder_IdCus_Status(idCus,1));
        return "/user/order/order";
    }
    // order 2 : ở  người dùng hiển thị đơn hàng chờ admin xác nhận
    @GetMapping("/order2/{idCus}")
    public String order2(@PathVariable("idCus")Integer idCus){
        return "redirect:/order/order/{idCus}";
    }
    // order 3: đơn hàng đnag được giao tới người mua
    @GetMapping("/order3/{idCus}")
    public String order3(@PathVariable("idCus")Integer idCus,Model model){
        model.addAttribute("order3",orderRespon.getOrder_IdCus_Status(idCus,2));
        return "/user/order/order3";
    }
    // order0: người dùng hủy đơn hàng
    @GetMapping("/order0/{idCus}/{idOrder}")
    public String  order0(@PathVariable("idCus")Integer idCus,
                          @PathVariable("idOrder")Integer idOrder){
        Order order = orderRespon.finById(idOrder);
        order.setStatus(0);
        orderRespon.save(order);
        return "redirect:/order/order/{idCus}";
    }
    @GetMapping("/order0/{idCus}")
    public String order0(@PathVariable("idCus")Integer idCus,Model model){
        model.addAttribute("order0",orderRespon.getOrder_IdCus_Status(idCus,0));
        return "/user/order/order0";
    }
    // click xác nhận shiper giao hàng đến người dùng
    @GetMapping("xacNhan/{idCus}/{idOrder}")
    public String xacNhan(@PathVariable("idCus")Integer idCus,
                          @PathVariable("idOrder")Integer idOrder){
        Order order = orderRespon.finById(idOrder);
        order.setStatus(3);
        orderRespon.save(order);
        return "redirect:/order/order/{idCus}";
    }
    // click xác nhận shiper giao hàng đến người dùng
    @GetMapping("muaLai/{idCus}/{idOrder}")
    public String muaLai(@PathVariable("idCus")Integer idCus,
                          @PathVariable("idOrder")Integer idOrder){
        Order order = orderRespon.finById(idOrder);
        order.setStatus(1);
        orderRespon.save(order);
        return "redirect:/order/order/{idCus}";
    }
    //order4 : đơn người dùng đã mua
    @GetMapping("/order4/{idCus}")
    public String order4(@PathVariable("idCus")Integer idCus,Model model){
        model.addAttribute("order4",orderRespon.getOrder_IdCus_Status(idCus,3));
        return "/user/order/order4";
    }
    // xem chi tiết order khi người dùng đã đặt hàng
    @GetMapping("/orderChiTiet/{idOrder}") // dùng theo form của showOrder
    public String orderChiTiet(@PathVariable("idOrder")Integer idOrder,
                               Model model){

        List<IOrderDetails> ordersDetails =orderDetailRespon.getOrdersDetail_IdOrder(idOrder);
        model.addAttribute("pro",orderDetailRespon.getOrdersDetail_IdOrder(idOrder));

        IOrder order = orderRespon.getOrderById(idOrder);

        model.addAttribute("receiver",order);
        model.addAttribute("total",order.getTotalMoney());
        model.addAttribute("payment",order);
        model.addAttribute("transport",order);

        // hiển thị đánh giá sản phẩm khi người dùng đã đánh giá
        Integer idCus= order.getIdCus();
        IEvaluate evaluate = evaluateRespon.getEvaluate_IdOrder_IdCus(idOrder,idCus);
        model.addAttribute("evaluate",evaluate);
        return "/user/order/orderChiTiet";
    }
    // back trong order chi tiết
    @GetMapping("backOrderChiTiet/{idCus}")
    public String backOd(@PathVariable("idCus")Integer idCus){
        return "redirect:/order/order/{idCus}";
    }

    // đánh giá sản phẩm khi người dùng đã mua hàng
    @PostMapping("/evaluate/{idOrder}")
    public String evaluate(@RequestParam("value")String value,
                           @PathVariable("idOrder")Integer idOrder){
        Evaluate evaluate = new Evaluate();
        evaluate.setValue(value);
        evaluate.setIdorder(idOrder);
        evaluate.setIsActive((byte) 1);
        evaluate.setIsDelete((byte) 1);
        evaluateService.save(evaluate);
        return "redirect:/order/orderChiTiet/{idOrder}";
    }

}
