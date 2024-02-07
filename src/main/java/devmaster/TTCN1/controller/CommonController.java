package devmaster.TTCN1.controller;

import devmaster.TTCN1.domain.Customer;
import devmaster.TTCN1.domain.Product;
import devmaster.TTCN1.projection.ICountCart;
import devmaster.TTCN1.respository.CartRespon;
import devmaster.TTCN1.respository.ProductRespon;
import devmaster.TTCN1.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("")
public class CommonController {
    @Autowired
    ProductRespon productRespon;
    @Autowired
    CartRespon cartRespon;
    @Autowired
    ProductService productService;
    @GetMapping("/")
    public String showUser(Model model, HttpSession session,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size){
        session.getAttribute("saveCus");
        if (session.getAttribute("saveCus") != null){
            Customer customer = (Customer) session.getAttribute("saveCus");
            int idCus = customer.getId();
            ICountCart countCart = cartRespon.getCount(idCus);
            model.addAttribute("countCart",countCart.getCount());// đếm số lượng cartItem khi đăng nhập
        }

//        model.addAttribute("product",productService.getAllProduct(p));
        // phân trang cho trang chủ
        int currentPage = page.orElse(1); // số trang
        int pageSize = size.orElse(8); // số sản phẩn trên 1 trang
        Page<Product> productPage = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("proPage", productPage);

        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
            model.addAttribute("maxPageNumber",pageNumbers.size());
        }
        // end phân trang
        return "user/index";
    }

    @GetMapping("/admin")
    public String showAdmin(){
        return "admin/index";
    }
}
