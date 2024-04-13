package devmaster.TTCN1.controller;

import devmaster.TTCN1.domain.Evaluate;
import devmaster.TTCN1.domain.Product;
import devmaster.TTCN1.respository.CartRespon;
import devmaster.TTCN1.respository.CategoryRespon;
import devmaster.TTCN1.respository.EvaluateRespon;
import devmaster.TTCN1.respository.ProductRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/evaluate")
public class EvaluateController {
    @Autowired
    EvaluateRespon evaluateRespon;
    @Autowired
    ProductRespon productRespon;
    @Autowired
    CategoryRespon categoryRespon;

   // click ẩn đánh giá
    @GetMapping("/activeOff/{idEvaluate}/{idPro}")
    public String activeOff(@PathVariable("idEvaluate")Integer idEvaluate,
                            @PathVariable("idPro")Integer idPro, Model model){
        model.addAttribute("productImages",productRespon.getAllImage(idPro));
        model.addAttribute("proImg",productRespon.getProByIdCateByUrl(idPro));
        model.addAttribute("productDetail",productRespon.getProductChiTiet(idPro));
        model.addAttribute("cate",productRespon.getProByIdCateByUrl(idPro));
        model.addAttribute("evaluate",evaluateRespon.getAllEvaluateAdminByPro(idPro));
        model.addAttribute("cateAll",categoryRespon.getAll());

        // lưu lại ngày sửa sản phẩm
        Product product = productRespon.findAllById(idPro);
        product.setUpdatedDate(String.valueOf(LocalDateTime.now()));
        Evaluate evaluate = evaluateRespon.getById(idEvaluate);
        evaluate.setIsActive((byte) 0);
        evaluateRespon.save(evaluate);
        return "redirect:/product/detailPro/{idPro}";
    }
    // click hiện đánh giá
    @GetMapping("/activeOn/{idEvaluate}/{idPro}")
    public String activeOn(@PathVariable("idEvaluate")Integer idEvaluate,
                           @PathVariable("idPro")Integer idPro, Model model){
        model.addAttribute("productImages",productRespon.getAllImage(idPro));
        model.addAttribute("proImg",productRespon.getProByIdCateByUrl(idPro));
        model.addAttribute("productDetail",productRespon.getProductChiTiet(idPro));
        model.addAttribute("cate",productRespon.getProByIdCateByUrl(idPro));
        model.addAttribute("evaluate",evaluateRespon.getAllEvaluateAdminByPro(idPro));
        model.addAttribute("cateAll",categoryRespon.getAll());

        // lưu lại ngày sửa sản phẩm
        Product product = productRespon.findAllById(idPro);
        product.setUpdatedDate(String.valueOf(LocalDateTime.now()));
        Evaluate evaluate = evaluateRespon.getById(idEvaluate);
        evaluate.setIsActive((byte)1);
        evaluateRespon.save(evaluate);
        return "redirect:/product/detailPro/{idPro}";
    }


}
