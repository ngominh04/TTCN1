package devmaster.TTCN1.controller;

import devmaster.TTCN1.respository.ProductRespon;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.lang.model.element.NestingKind;

@Controller
@RequestMapping("/view")
public class ViewController {
    @Autowired
    ProductRespon productRespon;

    @GetMapping("/productChiTiet/{idPro}") // cchi tiết sản phẩm
    public String showChiTiet(Model model,@PathVariable("idPro") Integer idPro){
        model.addAttribute("productImages",productRespon.getAllImage(idPro));
        model.addAttribute("productChiTiet",productRespon.getProductChiTiet(idPro));
        return "user/filter/ProductDetail";
    }
    @GetMapping("/price_up")
    public String getPriceUp(Model model, HttpSession session){
        session.setAttribute("price1",productRespon.getPriceUp());
        model.addAttribute("price_up",productRespon.getPriceUp());

        return "user/filter/Price";
    }
    @GetMapping("/price_down")
    public String getPriceDown(Model model, HttpSession session){
        session.removeAttribute("price1");
        model.addAttribute("price_Down",productRespon.getPriceDown());

        return "user/filter/Price";
    }

    @GetMapping("/price_15")
    public String getPrice_15(Model model,HttpSession session){
//        session.removeAttribute("price1");
        session.setAttribute("price2",productRespon.getPrice_15());
        model.addAttribute("price_15",productRespon.getPrice_15());
        return "user/filter/Price";
    }
    @GetMapping("/price_15_25")
    public String getPrice_15_25(Model model,HttpSession session){
//        session.removeAttribute("price1");
        session.setAttribute("price3",productRespon.getPrice_15_25());
        model.addAttribute("price_15_25",productRespon.getPrice_15_25());
        return "user/filter/Price";
    }
    @GetMapping("/price_25_35")
    public String getPrice_25_35(Model model,HttpSession session){
//        session.removeAttribute("price1");
        session.setAttribute("price4",productRespon.getPrice_25_35());
        model.addAttribute("price_25_35",productRespon.getPrice_25_35());
        return "user/filter/Price";
    }
    @GetMapping("/price_35")
    public String getPrice_35(Model model,HttpSession session){
//        session.removeAttribute("price1");
        session.setAttribute("price5",productRespon.getPrice_35());
        model.addAttribute("price_35",productRespon.getPrice_35());
        return "user/filter/Price";
    }
}
