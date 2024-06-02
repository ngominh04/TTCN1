package devmaster.TTCN1.controller;

import devmaster.TTCN1.domain.Category;
import devmaster.TTCN1.domain.Product;
import devmaster.TTCN1.respository.CategoryRespon;
import devmaster.TTCN1.respository.EvaluateRespon;
import devmaster.TTCN1.respository.ProductRespon;
import devmaster.TTCN1.service.CategoryService;
import devmaster.TTCN1.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.NestingKind;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/view")
public class ViewController {
    @Autowired
    ProductRespon productRespon;
    @Autowired
    EvaluateRespon evaluateRespon;
    @Autowired
    CategoryRespon categoryRespon;
    @Autowired
    CategoryService categoryService;
    @GetMapping("/productChiTiet/{idPro}") // cchi tiết sản phẩm
    public String showChiTiet(Model model,@PathVariable("idPro") Integer idPro){
        model.addAttribute("productImages",productRespon.getAllImage(idPro));
        model.addAttribute("productChiTiet",productRespon.getProductChiTiet(idPro));
        model.addAttribute("evaluate",evaluateRespon.getAllEvaluateByPro(idPro));
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
        session.setAttribute("acer",productRespon.getAccer());
        model.addAttribute("acer",productRespon.getAccer());
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

    @Autowired
    ProductService productService;
    // search
    @PostMapping("/search" )
    public String search(@RequestParam("name")String name,Model model,
                         @RequestParam("page") Optional<Integer> page,
                         @RequestParam("size") Optional<Integer> size,
                         HttpSession session){
//        model.addAttribute("proByName",productRespon.getAllName(name));
        // phân trang cho search
        session.setAttribute("searchName",name);
        int currentPage = page.orElse(1); // số trang
        int pageSize = size.orElse(8); // số sản phẩn trên 1 trang
        Page<Product> productPage = productService.findPaginatedSearch(PageRequest.of(currentPage - 1, pageSize),name);

        model.addAttribute("proByName", productPage);

        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
            model.addAttribute("maxPageNumber",pageNumbers.size());
        }
        // end phân trang
        return "user/filter/search";
    }
    @GetMapping("/Search" )
    public String Search(Model model,
                         @RequestParam("page") Optional<Integer> page,
                         @RequestParam("size") Optional<Integer> size,
                         HttpSession session){
//        model.addAttribute("proByName",productRespon.getAllName(name));
        // phân trang cho search

        String name = (String) session.getAttribute("searchName");
        int currentPage = page.orElse(1); // số trang
        int pageSize = size.orElse(8); // số sản phẩn trên 1 trang
        Page<Product> productPage = productService.findPaginatedSearch(PageRequest.of(currentPage - 1, pageSize),name);

        model.addAttribute("proByName", productPage);

        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
            model.addAttribute("maxPageNumber",pageNumbers.size());
        }
        // end phân trang
        return "user/filter/search";
    }
//    lấy ra  sản phẩm theo  phụ kiện chọn
    @GetMapping("/pk/{idCate}")
    public  String pkByIdCate(Model model,@PathVariable("idCate")Integer idCate){
        List<Product> list=productRespon.getAllByIdCate(idCate);
        model.addAttribute("cate",list);
        return "user/filter/listByIdCate";
    }
}
