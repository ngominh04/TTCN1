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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                model.addAttribute("message","Mật khẩu sai");
            } else if (customer.getIsDelete() == 0) {
                model.addAttribute("message","Tài khoản của bạn bị khóa");
            } else {

                model.addAttribute("customer",customerRespon.getCustomer(username));

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
            if (email.isBlank()){
                model.addAttribute("message","email không được để trống");
                checkCus = false;
                break;
            }if (cus.getUsername().equals(username) == true){
                model.addAttribute("message","Đã tồn tại tên tài khoản");
                checkCus = false;
                break;
            }
            if (phone.length() != 10 ){
                model.addAttribute("message","Số điện thoại phải 10 kí tự ");
                checkCus = false;
                break;
            }
            if (password.length() < 8  ){
                model.addAttribute("message","Mật khẩu phải trên 8 kí tự");
                checkCus = false;
                break;
            }
            //Dùng regex
            Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).+$");
            Matcher matcher = pattern.matcher(password);
            boolean checkPass = matcher.matches();
            if ( checkPass == false){
                model.addAttribute("message",
                        "Mật khẩu phải chứa ít nhất 1 kí tự số, " +
                                "1 chữ thường, 1 chữ hoa, 1 ký tự đặc biệt");
                checkCus = false;
                break;
            }
            else {
                checkCus =true;
            }
        }
        if(checkCus) {
            Customer customer = new Customer();
            customer.setIsActive((byte) 1);
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

    // ng dùng quên pass
    @GetMapping("/forgotPass")
    public String forgotpass(){
        return "/user/register/forgotPass";
    }
    @PostMapping("/forgotPass")
    public String forgotPass(Model model,
                             @RequestParam("email")String email,
                             @RequestParam("username")String username,
                             HttpSession session){
        List<Customer> customers = customerRespon.getAllById();
        boolean check = false;
        for (Customer customer:customers) {
            if (customer.getEmail().equals(email) == true){
                if (customer.getUsername().equals(username) == true){
                    check = false;
                    session.setAttribute("usernameForgot",username);
                    return "/user/register/forgotPass2";
                }
            }
            else {
                check = true;
            }
        }
        if(check){
            model.addAttribute("message","Không tồn tại email hoặc tài khoản này");
        }
        return "/user/register/forgotPass";
    }
    @PostMapping("forgotPass2")
    public String forgotPass2(Model model,
                              @RequestParam("password")String password,
                              @RequestParam("password2")String password2,
                              HttpSession session){
        if (password.equals(password2) == false){
            model.addAttribute("message","2 mật khẩu phải giống nhau");
            return "/user/register/forgotPass2";
        }
        if (password.length() <8 && password2.length()<8){
            model.addAttribute("message","Mật khẩu phải trên 8 ký tự");
            return "/user/register/forgotPass2";
        }
        //Dùng regex
        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).+$");
        Matcher matcher = pattern.matcher(password);
        boolean checkPass = matcher.matches();
        if ( checkPass == false){
            model.addAttribute("message",
                    "Mật khẩu phải chứa ít nhất 1 kí tự số, " +
                            "1 chữ thường, 1 chữ hoa, 1 ký tự đặc biệt");
            return "/user/register/forgotPass2";
        }
        else {
            Customer customer = customerRespon.getCustomer((String) session.getAttribute("usernameForgot"));
            customer.setPassword(password);
            session.removeAttribute("usernameForgot");
            customerService.save(customer);
        }
        return "/user/register/Login";
    }
    // sửa thông tin tài khoản
    @GetMapping("/updateCus/{idCus}")
    public String updateCus(HttpSession session,Model model,@PathVariable("idCus") Integer idCus){
        Customer customer = (Customer) session.getAttribute("saveCus");
        model.addAttribute("customer",customer);
        return "user/register/updateCus";
    }
    @PostMapping("/updateCus/{idCus}")
    public String updatecus(@PathVariable("idCus") Integer idCus,
                            @RequestParam("fullName")String fullName,
                            @RequestParam("email")String email,
                            @RequestParam("username")String username,
                            @RequestParam("password")String password,
                            HttpSession session,
                            Model model){
        Customer customer = (Customer) session.getAttribute("saveCus");
        if (fullName.isBlank() ){ // isBlank : ktra kí tu khoảng trắng và emty
            model.addAttribute("message","Full name Không hợp lệ");
            model.addAttribute("customer",customer);
            return "user/register/updateCus";
        }
        String emailCut = email.replace("@gmail.com",""); // replace thay kí tự na thành kí tự khác
        if (emailCut.isBlank() ){ // isBlank : ktra kí tu khoảng trắng và emty
            model.addAttribute("message","email Không hợp lệ");
            model.addAttribute("customer",customer);
            return "user/register/updateCus";
        }
        if (password.isBlank() || password.length() < 5){ // isBlank : ktra kí tu khoảng trắng và emty
            model.addAttribute("message","Password Không hợp lệ");
            model.addAttribute("customer",customer);
            return "user/register/updateCus";
        }
        if (username.isBlank() ){ // isBlank : ktra kí tu khoảng trắng và emty
            model.addAttribute("message","Username Không hợp lệ");
            model.addAttribute("customer",customer);
            return "user/register/updateCus";
        }
        customer.setName(fullName);
        customer.setEmail(email);
        customer.setUsername(username);
        customer.setPassword(password);
//        customerService.save(customer);
        return "redirect:/receiver/receiver/{idCus}";
    }
}
