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
        session.removeAttribute("price2");
        session.removeAttribute("price1");
        session.removeAttribute("price3");
        session.removeAttribute("price4");
        session.removeAttribute("price5");
        session.setAttribute("price",productRespon.getPriceUp());
        model.addAttribute("price_up",productRespon.getPriceUp());

        return "user/filter/Price";
    }
    @GetMapping("/price_down")
    public String getPriceDown(Model model, HttpSession session){
        session.removeAttribute("price");
        session.removeAttribute("price2");
        session.removeAttribute("price3");
        session.removeAttribute("price4");
        session.removeAttribute("price5");
        session.setAttribute("price1",productRespon.getPriceDown());
        model.addAttribute("price_Down",productRespon.getPriceDown());

        return "user/filter/Price";
    }

    @GetMapping("/price_15")
    public String getPrice_15(Model model,HttpSession session){
        session.removeAttribute("price1");
        session.removeAttribute("price");
        session.removeAttribute("price3");
        session.removeAttribute("price4");
        session.removeAttribute("price5");
        session.setAttribute("price2",productRespon.getPrice_15());
        model.addAttribute("price_15",productRespon.getPrice_15());
        return "user/filter/Price";
    }
    @GetMapping("/price_15_25")
    public String getPrice_15_25(Model model,HttpSession session){
        session.removeAttribute("price1");
        session.removeAttribute("price");
        session.removeAttribute("price2");
        session.removeAttribute("price4");
        session.removeAttribute("price5");
        session.setAttribute("price3",productRespon.getPrice_15_25());
        model.addAttribute("price_15_25",productRespon.getPrice_15_25());
        return "user/filter/Price";
    }
    @GetMapping("/price_25_35")
    public String getPrice_25_35(Model model,HttpSession session){
        session.removeAttribute("price1");
        session.removeAttribute("price");
        session.removeAttribute("price2");
        session.removeAttribute("price3");
        session.removeAttribute("price5");
        session.setAttribute("price4",productRespon.getPrice_25_35());
        model.addAttribute("price_25_35",productRespon.getPrice_25_35());
        return "user/filter/Price";
    }
    @GetMapping("/price_35")
    public String getPrice_35(Model model,HttpSession session){
        session.removeAttribute("price1");
        session.removeAttribute("price");
        session.removeAttribute("price2");
        session.removeAttribute("price3");
        session.removeAttribute("price4");
        session.setAttribute("price5",productRespon.getPrice_35());
        model.addAttribute("price_35",productRespon.getPrice_35());
        return "user/filter/Price";
    }
    @GetMapping("/accer")
    public String getAccer(Model model,HttpSession session){
        session.removeAttribute("asus");
        session.removeAttribute("dell");
        session.removeAttribute("apple");
        session.removeAttribute("hp");
        session.removeAttribute("lenovo");
        session.setAttribute("accer",productRespon.getAccer());
        model.addAttribute("accer",productRespon.getAccer());
        return "user/filter/Description";
    }
    @GetMapping("/asus")
    public String getAsus(Model model,HttpSession session){
        session.removeAttribute("accer");
        session.removeAttribute("dell");
        session.removeAttribute("apple");
        session.removeAttribute("hp");
        session.removeAttribute("lenovo");
        session.setAttribute("asus",productRespon.getAsus());
        model.addAttribute("asus",productRespon.getAsus());
        return "user/filter/Description";
    }
    @GetMapping("/dell")
    public String getDell(Model model,HttpSession session){
        session.removeAttribute("accer");
        session.removeAttribute("asus");
        session.removeAttribute("apple");
        session.removeAttribute("hp");
        session.removeAttribute("lenovo");
        session.setAttribute("dell",productRespon.getDell());
        model.addAttribute("dell",productRespon.getDell());
        return "user/filter/Description";
    }
    @GetMapping("/hp")
    public String getHp(Model model,HttpSession session){
        session.removeAttribute("accer");
        session.removeAttribute("dell");
        session.removeAttribute("apple");
        session.removeAttribute("asus");
        session.removeAttribute("lenovo");
        session.setAttribute("hp",productRespon.getHp());
        model.addAttribute("hp",productRespon.getHp());
        return "user/filter/Description";
    }
    @GetMapping("/lenovo")
    public String getLenovo(Model model,HttpSession session){
        session.removeAttribute("accer");
        session.removeAttribute("dell");
        session.removeAttribute("apple");
        session.removeAttribute("hp");
        session.removeAttribute("asus");
        session.setAttribute("lenovo",productRespon.getLenovo());
        model.addAttribute("lenovo",productRespon.getLenovo());
        return "user/filter/Description";
    }
    @GetMapping("/apple")
    public String getApple(Model model,HttpSession session){
        session.removeAttribute("accer");
        session.removeAttribute("dell");
        session.removeAttribute("lenovo");
        session.removeAttribute("hp");
        session.removeAttribute("asus");
        session.setAttribute("apple",productRespon.getApple());
        model.addAttribute("apple",productRespon.getApple());
        return "user/filter/Description";
    }
    @GetMapping("/phuKien")
    public String showPhuKien(Model model,HttpSession session) {session.removeAttribute("accer");
        session.removeAttribute("dell");
        session.removeAttribute("lenovo");
        session.removeAttribute("hp");
        session.removeAttribute("asus");
        session.removeAttribute("apple");

        session.setAttribute("phuKien",productRespon.getPhuKien());
        model.addAttribute("phuKien",productRespon.getPhuKien());
        return "user/filter/Description";
    }

    @GetMapping("/backProductChiTiet")
    public String getBackProductChiTiet(Model model){
        return "redirect:/";
    }
}
