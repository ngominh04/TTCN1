package devmaster.TTCN1.controller;

import devmaster.TTCN1.domain.Cart;
import devmaster.TTCN1.domain.Customer;
import devmaster.TTCN1.respository.CartRespon;
import devmaster.TTCN1.respository.CustomerRespon;
import devmaster.TTCN1.respository.ReceiverRespon;
import devmaster.TTCN1.service.ParamService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    CustomerRespon customerRespon;
    @Autowired
    ParamService paramService;
    @Autowired
    CartRespon cartRespon;
    @GetMapping("/login")
    public String login(){
        return "user/register/Login";
    }
    @PostMapping("/login_check")
    public String login(Model model, @RequestParam(name = "username") String username, HttpSession session, Cart item){
//        String username = paramService.getString("username","");
        String password = paramService.getString("password","");
        Customer customer= customerRespon.getCustomer(username);
        session.setAttribute("saveCus",customer);
        try {

            if(!customer.getPassword().equals(password)){
                model.addAttribute("message","Mật khẩu có vấn đề");

            }else {

                model.addAttribute("customer",customerRespon.getCustomer(username));

//                List<Nguoinhan> nguoinhan = nguoiNhanRespon.getNguoinhan(customer.getId());
//                model.addAttribute("listNguoiNhan",nguoinhan);
//
//                item.setUsername(username);
                if(customer.getPhanquyen() == 1){
                    return "redirect:/admin";
                }else {

                    return "redirect:/";
                }
            }
        }catch (Exception e){
            model.addAttribute("message","Không tồn tại tài khoản");
        }

        return "user/register/Login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("saveCus");
        return "redirect:/";
    }

    @Autowired
    ReceiverRespon receiverRespon;
    // người nhận
    @GetMapping("/receiver/{idCus}")
    public String showRereiver(Model model, @PathVariable("idCus")Integer idCus){
        model.addAttribute("receiver",receiverRespon.getAllReceiver(idCus));
        return "user/receiver/showReceiver";
    }
}
