package devmaster.TTCN1.controller;

import devmaster.TTCN1.domain.PaymentMethod;
import devmaster.TTCN1.domain.TransportMethod;
import devmaster.TTCN1.respository.PaymentRespon;
import devmaster.TTCN1.respository.TransportRespon;
import devmaster.TTCN1.service.PaymentService;
import devmaster.TTCN1.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/transport")
public class TransportController {
    @Autowired
    TransportRespon transportRespon;
    @Autowired
    TransportService transportService;

    // lấy toàn bộ
    @GetMapping("/allTransport")
    public String allTp(Model model){
        model.addAttribute("transport",transportRespon.getAll());
        return "admin/method/showTransport";
    }
    // ấn bật
    @GetMapping("/onDelete/{idTransport}")
    public String  onDelete(@PathVariable("idTransport") Integer idTransport){
        TransportMethod transportMethod = transportRespon.getTransportById(idTransport);
        transportMethod.setIsDelete((byte) 1);
        transportRespon.save(transportMethod);
        return "redirect:/transport/allTransport";
    }
    // ấn tắt
    @GetMapping("/offDelete/{idTransport}")
    public String  offDelete(@PathVariable("idTransport") Integer idTransport){
        TransportMethod transportMethod = transportRespon.getTransportById(idTransport);
        transportMethod.setIsDelete((byte) 0);
        transportRespon.save(transportMethod);
        return "redirect:/transport/allTransport";
    }
    // ấn sửa
    @GetMapping("/fixTransport/{idTransport}")
    public String fixTp(@PathVariable("idTransport")Integer idTransport,Model model){
        TransportMethod transportMethod = transportRespon.getTransportById(idTransport);
        model.addAttribute("transport",transportMethod);
        return "/admin/method/updateTransport";
    }
    // lưu lại khi sửa
    @PostMapping("/updateTransport/{idTransport}")
    public String updateTp(@RequestParam("name")String name,
                                @RequestParam("notes")String notes,
                                @RequestParam("price")String priceStr,
                                @PathVariable("idTransport")Integer idTransport){
        TransportMethod transportMethod = transportRespon.getTransportById(idTransport);
        transportMethod.setName(name);
        transportMethod.setNotes(notes);
        // replace("chuỗi cần thay "." thay bằng")
        double price = Double.parseDouble(priceStr.replace(".",""));
        transportMethod.setPrice(price);
        transportMethod.setUpdatedDate(String.valueOf(LocalDateTime.now()));
        transportService.save(transportMethod);
        return "redirect:/transport/allTransport";
    }
    // click vào nút thêm mới
    @GetMapping("/newTransport")
    public String newTransport(){
        return "/admin/method/newTransport";
    }
    // tạo mới
    @PostMapping("/newTransport")

    public String newTp(@RequestParam("name")String name,
                             @RequestParam("notes")String notes,
                             @RequestParam("price")Double price) {
        TransportMethod transportMethod = new TransportMethod();
        transportMethod.setIsDelete((byte) 1);
        transportMethod.setIsActive((byte) 1);
        transportMethod.setPrice(price);
        transportMethod.setCreatedDate(String.valueOf(LocalDateTime.now()));
        transportMethod.setName(name);
        transportMethod.setNotes(notes);
        transportService.save(transportMethod);
        return "redirect:/transport/allTransport";
    }
}
