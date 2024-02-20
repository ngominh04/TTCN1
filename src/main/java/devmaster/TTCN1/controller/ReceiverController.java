package devmaster.TTCN1.controller;

import devmaster.TTCN1.domain.Customer;
import devmaster.TTCN1.domain.Order;
import devmaster.TTCN1.domain.Receiver;
import devmaster.TTCN1.respository.CustomerRespon;
import devmaster.TTCN1.respository.OrderRespon;
import devmaster.TTCN1.respository.ReceiverRespon;
import devmaster.TTCN1.service.ReceiverService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWarDeployment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/receiver")
public class ReceiverController {
    @Autowired
    CustomerRespon customerRespon;
    @Autowired
    ReceiverRespon receiverRespon;
    @Autowired
    ReceiverService receiverService;
    @Autowired
    OrderRespon orderRespon;
    // show người nhận
    @GetMapping("/receiver/{idCus}")
    public String showRereiver(Model model, @PathVariable("idCus")Integer idCus,HttpSession session){
        Customer customer = (Customer) session.getAttribute("saveCus");
        model.addAttribute("customer",customer);
        model.addAttribute("receiver",receiverRespon.getAllReceiver(idCus));
        session.removeAttribute("newReceiverOrder");
        return "user/receiver/showReceiver";
    }
    // đường dẫn tới table sửa người nhận
    @GetMapping("/repairReceiver/{idRece}")
    public String repairReceiver(@PathVariable("idRece") Integer idRece, Model model, HttpSession session){
        session.setAttribute("receiverById",idRece);
        model.addAttribute("receiverById",receiverRespon.findAllById(idRece));
        return "user/receiver/repairReceiver";
    }

    // lưu lại khi sửa người nhận
    @PostMapping("/repairReceiverId/{idCus}/{idRece}")
    public String repairReceiverId(@ModelAttribute("receiver")Receiver receiver, // lấy receiver ở form khi nhập lên
            @PathVariable("idRece")Integer idRece
            , @PathVariable("idCus") Integer idCus){
        Receiver receiver1 = receiverRespon.findAllById(idRece); // lấy receiver theo id rồi sửa
        receiver1.setName(receiver.getName());
        receiver1.setPhone(receiver.getPhone());
        receiver1.setAddress(receiver.getAddress());
        receiverService.save(receiver1);
        return "redirect:/receiver/receiver/{idCus}";
    }
    // xóa địa chỉ
    @GetMapping("/remove/{idCus}/{idRece}")
    public String removeReceiver(@PathVariable("idCus") Integer idCus
            , @PathVariable("idRece") Integer idRece){
        Receiver receiver = receiverRespon.findAllById(idRece);
        receiver.setIsDelete(0);
        receiverService.save(receiver);
        return "redirect:/receiver/receiver/{idCus}";
    }
    // thêm địa chỉ mới
    @GetMapping("/newReceiver") // thêm mới khi đang ở nơi kiểm soát tài khoản  của ng dùng
    public String newRece(Model model){
        model.addAttribute("newRece",new Receiver());
        return "/user/receiver/newReceiver";
    }
    @PostMapping("/newReceiver/{idCus}") // lưu thêm mới ở chỗ kiểm soát tài khoản người dùng
    public String newReceiver(@ModelAttribute("receiver")Receiver receiver,@PathVariable("idCus")Integer idCus){
        receiver.setIsDelete(1);
        Customer customer = customerRespon.getCustomerById(idCus);
        receiver.setIdCus(customer.getId());
        receiverService.save(receiver);
        return "redirect:/receiver/receiver/{idCus}";
    }
    // nút back trong newReceOrder
    @GetMapping("/back/{idCus}")
    public String back(@PathVariable("idCus")Integer idCus){
        return "redirect:/receiver/receiver/{idCus}";
    }
    @GetMapping("/newReceiverOrder/{idCus}") // thêm mới khi đang ở Order
    public String newReceOrder(Model model,
                               @PathVariable("idCus")Integer idCus,
                               HttpSession session){
        session.getAttribute("newReceiverOrder");
        model.addAttribute("newRece",new Receiver());
        return "/user/receiver/newReceiver";
    }
    @PostMapping("/newReceiverOrder/{idCus}") // lưu thêm mới ở order
    public String newReceiverOrder(@ModelAttribute("receiver")Receiver receiver,
                                   @PathVariable("idCus")Integer idCus,
                                   HttpSession session){
        session.removeAttribute("newReceiverOrder");
        receiver.setIsDelete(1);
        Customer customer = customerRespon.getCustomerById(idCus);
        receiver.setIdCus(customer.getId());
        receiverService.save(receiver);
        return "redirect:/order/showOrder/{idCus}";
    }
    // nút back trong newReceOrder
    @GetMapping("/backOrder/{idCus}")
    public String backReceOrder(@PathVariable("idCus")Integer idCus){
        return "redirect:/order/showOrder/{idCus}";
    }


}
