package devmaster.TTCN1.controller;

import devmaster.TTCN1.domain.Customer;
import devmaster.TTCN1.domain.Product;
import devmaster.TTCN1.respository.CustomerRespon;
import devmaster.TTCN1.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerRespon customerRespon;
    @Autowired
    CustomerService customerService;

    // active : cho đang nhập nhưng ko cho mua (bị chặn mua hàng)

    // click vào tắt ở Is active -> chuyển isactive = 0
    @GetMapping("/activeCusOff/{idCus}")
    public String activeCusOff(@PathVariable("idCus")Integer idCus){
        Customer customer = customerRespon.getCustomerById(idCus);
        customer.setIsActive((byte) 0);
        customerService.save(customer);
        return "redirect:/admin#customer";
    }
    // click vào bật ở Is active -> chuyển isactive = 1
    @GetMapping("/activeCusOn/{idCus}")
    public String activeCusOn(@PathVariable("idCus")Integer idCus){
        Customer customer = customerRespon.getCustomerById(idCus);
        customer.setIsActive((byte) 1);
        customerService.save(customer);
        return "redirect:/admin#customer";
    }

    // delete: ng dùng ko đc đăng nhập

    // click vào tắt ở Is delete -> chuyển isdelete = 0
    @GetMapping("/deleteCusOff/{idCus}")
    public String deleteCusOff(@PathVariable("idCus")Integer idCus){
        Customer customer = customerRespon.getCustomerById(idCus);
        customer.setIsDelete((byte) 0);
        customerService.save(customer);
        return "redirect:/admin#customer";
    }
    // click vào bật ở Is delete -> chuyển isdelete = 1
    @GetMapping("/deleteCusOn/{idCus}")
    public String deleteCusOn(@PathVariable("idCus")Integer idCus){
        Customer customer = customerRespon.getCustomerById(idCus);
        customer.setIsDelete((byte) 1);
        customerService.save(customer);
        return "redirect:/admin#customer";
    }
}
