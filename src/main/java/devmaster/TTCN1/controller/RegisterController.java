package devmaster.TTCN1.controller;

import devmaster.TTCN1.domain.Cart;
import devmaster.TTCN1.domain.Customer;
import devmaster.TTCN1.domain.Receiver;
import devmaster.TTCN1.respository.CartRespon;
import devmaster.TTCN1.respository.CustomerRespon;
import devmaster.TTCN1.respository.ReceiverRespon;
import devmaster.TTCN1.service.CustomerService;
import devmaster.TTCN1.service.ParamService;
import devmaster.TTCN1.service.ReceiverService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    CustomerRespon customerRespon;
    @Autowired
    CustomerService customerService;
    @Autowired
    ParamService paramService;
    @Autowired
    CartRespon cartRespon;
    @Autowired
    ReceiverRespon receiverRespon;
    @Autowired
    ReceiverService receiverService;
    @GetMapping("/login")
    public String login(){
        return "user/register/Login";
    }
    @PostMapping("/login_check")
    public String login(Model model, @RequestParam(name = "username") String username, HttpSession session, Cart item){
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

    // đăng kí người dùng
    @GetMapping("/newCus")
    public String newCus(){
        return "/user/register/newCus";
    }

    // lưu tài khoản mới
    @PostMapping("/newCustomer")
    public String newCustomer(@RequestParam("name")String name,
                              @RequestParam("email")String email,
                              @RequestParam("address")String address,
                              @RequestParam("phone")String phone,
                              @RequestParam("username")String username,
                              @RequestParam("password")String password,
                              Model model){
        boolean checkCus = false;
        List<Customer> customers = customerRespon.getAllById();
        for (Customer cus:customers) {
            if (cus.getEmail().equals(email) == true){
                model.addAttribute("message","Đã tồn tại email này");
                checkCus = false;
                break;
            }if (cus.getUsername().equals(username) == true){
                model.addAttribute("message","Đã tồn tại tên tài khoản");
                checkCus = false;
                break;
            }
            if (phone.length() != 10 ){
                model.addAttribute("message","Xem lại số điện thoại");
                checkCus = false;
                break;
            }
            if (password.length() < 5 ){
                model.addAttribute("message","Mật khẩu phải trên 5 kí tự");
                checkCus = false;
                break;
            }
            else {
                checkCus =true;
            }
        }
        if(checkCus) {
            Customer customer = new Customer();
            customer.setIsactive((byte) 1);
            customer.setIsDelete((byte) 1);
            customer.setPhone(phone);
            customer.setEmail(email);
            customer.setPassword(password);
            customer.setCreatedDate(String.valueOf(LocalDateTime.now()));
            customer.setPhanquyen(0);
            customer.setUsername(username);
            customer.setAddress(address);
            customer.setName(name);
            customerService.save(customer);
            // đồng thời tạo luôn receiver mặc định khi ng dùng tạo xong tài khoản mới
            Receiver receiver = new Receiver();
            receiver.setIdCus(customer.getId());
            receiver.setName(customer.getName());
            receiver.setIsDelete(1);
            receiver.setAddress(customer.getAddress());
            receiver.setPhone(customer.getPhone());
            receiverService.save(receiver);
            return "/user/register/Login";
        }
        return "/user/register/newCus";
    }
}
