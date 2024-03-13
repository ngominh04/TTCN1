package devmaster.TTCN1.controller;

import devmaster.TTCN1.domain.PaymentMethod;
import devmaster.TTCN1.respository.PaymentRespon;
import devmaster.TTCN1.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentRespon paymentRespon;
    @Autowired
    PaymentService paymentService;

    // lấy toàn bộ
    @GetMapping("/allPayment")
    public String allPayment(Model model){
        model.addAttribute("payment",paymentRespon.getAll());
        return "admin/method/showPayment";
    }
    // ấn bật
    @GetMapping("/onDelete/{idPayment}")
    public String  onDelete(@PathVariable("idPayment") Integer idPayment){
        PaymentMethod paymentMethod = paymentRespon.getPaymentById(idPayment);
        paymentMethod.setIsDelete((byte) 1);
        paymentService.save(paymentMethod);
        return "redirect:/payment/allPayment";
    }
    // ấn tắt
    @GetMapping("/offDelete/{idPayment}")
    public String  offDelete(@PathVariable("idPayment") Integer idPayment){
        PaymentMethod paymentMethod = paymentRespon.getPaymentById(idPayment);
        paymentMethod.setIsDelete((byte) 0);
        paymentService.save(paymentMethod);
        return "redirect:/payment/allPayment";
    }
    // ấn sửa
    @GetMapping("/fixPayment/{idPayment}")
    public String fixPayment(@PathVariable("idPayment")Integer idPayment,Model model){
        PaymentMethod paymentMethod = paymentRespon.getPaymentById(idPayment);
        model.addAttribute("payment",paymentMethod);
        return "/admin/method/updatePayment";
    }
    // lưu lại khi sửa
    @PostMapping("/updatePayment/{idPayment}")
    public String updatePayment(@RequestParam("name")String name,
                                @RequestParam("notes")String notes,
                                @PathVariable("idPayment")Integer idPayment){
        PaymentMethod paymentMethod = paymentRespon.getPaymentById(idPayment);
        paymentMethod.setName(name);
        paymentMethod.setNotes(notes);
        paymentMethod.setUpdatedDate(String.valueOf(LocalDateTime.now()));
        paymentService.save(paymentMethod);
        return "redirect:/payment/allPayment";
    }
    // click vào nút thêm mới
    @GetMapping("/newPayment")
    public String newPayment(){
        return "/admin/method/newPayment";
    }
    // tạo mới
    @PostMapping("/newPayment")

    public String newPayment(@RequestParam("name")String name,
                             @RequestParam("notes")String notes) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setIsDelete((byte) 1);
        paymentMethod.setIsactive((byte) 1);
        paymentMethod.setCreatedDate(String.valueOf(LocalDateTime.now()));
        paymentMethod.setName(name);
        paymentMethod.setNotes(notes);
        paymentRespon.save(paymentMethod);
        return "redirect:/payment/allPayment";
    }
}
