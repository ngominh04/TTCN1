package devmaster.TTCN1.controller;

import devmaster.TTCN1.domain.Category;
import devmaster.TTCN1.domain.Product;
import devmaster.TTCN1.domain.ProductImage;
import devmaster.TTCN1.respository.CategoryRespon;
import devmaster.TTCN1.respository.EvaluateRespon;
import devmaster.TTCN1.respository.ProductRespon;
import devmaster.TTCN1.service.ProductImageService;
import devmaster.TTCN1.service.ProductService;
import devmaster.TTCN1.service.UploadFileService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    UploadFileService uploadFileService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    ProductRespon productRespon;
    @Autowired
    ProductService productService;
    @Autowired
    EvaluateRespon evaluateRespon;
    @Autowired
    CategoryRespon categoryRespon;
    // click vào tắt ở Is active -> chuyển isactive = 0
    @GetMapping("/activeProOff/{idPro}")
    public String activeProOff(@PathVariable("idPro")Integer idPro){
        Product product = productRespon.findAllById(idPro);
        product.setIsactive((byte) 0);
        productRespon.save(product);
        return "redirect:/admin#product";
    }
    // click vào bật ở Is active -> chuyển isactive = 1
    @GetMapping("/activeProOn/{idPro}")
    public String activeProOn(@PathVariable("idPro")Integer idPro){
        Product product = productRespon.findAllById(idPro);
        product.setIsactive((byte) 1);
        productRespon.save(product);
        return "redirect:/admin#product";
    }
    // click vào tắt ở Is delete -> chuyển isdelete = 0
    @GetMapping("/deleteProOff/{idPro}")
    public String deleteProOff(@PathVariable("idPro")Integer idPro){
        Product product = productRespon.findAllById(idPro);
        product.setIsdelete((byte)0);
        productRespon.save(product);
        return "redirect:/admin#product";
    }
    // click vào bật ở Is delete -> chuyển isdelete = 1
    @GetMapping("/deleteProOn/{idPro}")
    public String deleteProOn(@PathVariable("idPro")Integer idPro){
        Product product = productRespon.findAllById(idPro);
        product.setIsdelete((byte) 1);
        productRespon.save(product);
        return "redirect:/admin#product";
    }
    // xem chi tiết product
    @GetMapping("/detailPro/{idPro}")
    public String detailPro(Model model, @PathVariable("idPro")Integer idPro){
        model.addAttribute("productImages",productRespon.getAllImage(idPro));
        model.addAttribute("proImg",productRespon.getProByIdCateByUrl(idPro));
        model.addAttribute("productDetail",productRespon.getProductChiTiet(idPro));
        model.addAttribute("cate",productRespon.getProByIdCateByUrl(idPro));
        model.addAttribute("evaluate",evaluateRespon.getAllEvaluateAdminByPro(idPro));

//        model.addAttribute("cateAll",categoryRespon.getAll());
        return "/admin/product/detailPro";
    }
    // click ấn chỉnh sửa thông tin sp
    @GetMapping("/updatePro/{idPro}")
    public String updatePro(Model model,@PathVariable("idPro")Integer idPro, HttpSession session){
        model.addAttribute("productImages",productRespon.getAllImage(idPro));
        model.addAttribute("proImg",productRespon.getProByIdCateByUrl(idPro));
        model.addAttribute("productDetail",productRespon.getProductChiTiet(idPro));
        model.addAttribute("cate",productRespon.getProByIdCateByUrl(idPro));
        model.addAttribute("evaluate",evaluateRespon.getAllEvaluateAdminByPro(idPro));
        model.addAttribute("cateAll",categoryRespon.getAll());
        session.setAttribute("product",productRespon.getProductChiTiet(idPro));
        return "/admin/product/updatePro";
    }
    // lưu cập nhật sản phẩm
    @PostMapping("/updatePro/{idPro}")
    public String updatePro(HttpSession session,
                            @PathVariable("idPro")Integer idPro,
                            @RequestParam("namePro")String namePro,
                            @RequestParam("notes")String notes,
                            @RequestParam("description")String description,
                            @RequestParam("idCate")Integer idCate,
                            @RequestParam("price")String priceStr,
                            @RequestParam("quantity")int quantity){
        Product product = (Product) session.getAttribute("product");
        Category category = categoryRespon.findAllById(idCate);
        product.setName(namePro);
        product.setDescription(description);
        product.setNotes(notes);
        product.setIdCategory(category.getId());
        // replace("chuỗi cần thay","thay bằng")
        double price = Double.parseDouble(priceStr.replace(".",""));
        product.setPrice(price);
        product.setUpdatedDate(String.valueOf(LocalDateTime.now()));
        product.setQuatity(quantity);
        productService.save(product);
        session.removeAttribute("product");
        return "redirect:/product/detailPro/{idPro}";
    }

    // click quản lí ảnh
    @GetMapping("/updateProImg/{idPro}")
    public String updateProImg(Model model,@PathVariable("idPro")Integer idPro, HttpSession session){
        model.addAttribute("productImages",productRespon.getAllImage(idPro));
        model.addAttribute("proImg",productRespon.getProByIdCateByUrl(idPro));
        model.addAttribute("productDetail",productRespon.getProductChiTiet(idPro));
        model.addAttribute("cate",productRespon.getProByIdCateByUrl(idPro));
        model.addAttribute("evaluate",evaluateRespon.getAllEvaluateAdminByPro(idPro));
        model.addAttribute("cateAll",categoryRespon.getAll());
        session.setAttribute("product",productRespon.getProductChiTiet(idPro));
        return "/admin/product/updateProImg";
    }

    // click thêm mới 1 product
    @GetMapping("/newPro")
    public String newPro(Model model){
        model.addAttribute("cateAll",categoryRespon.getAll());
        return "/admin/product/newPro";
    }
    // lưu lại khi thêm mới
    @PostMapping("/newPro")
    public String newPro(@RequestParam("name")String name,
                         @RequestParam("description")String description,
                         @RequestParam("price")double price,
                         @RequestParam("quantity")Integer quantity,
                         @RequestParam("notes")String notes,
                         @RequestParam("category")Integer category,
                         @RequestParam("file")MultipartFile file){
        Product product = new Product();
        product.setQuatity(quantity);
        product.setNotes(notes);
        product.setName(name);
        product.setDescription(description);
        product.setIsactive((byte) 1);
        product.setIsdelete(1);
        product.setPrice(price);
        product.setCreatedDate(String.valueOf(LocalDateTime.now()));
        product.setIdCategory(category);
        productService.save(product);

        uploadFileService.store(file);

        // lưu lại ảnh vào product
        String fileName1 = StringUtils.cleanPath(file.getOriginalFilename());
        // tạo mới 1 ảnh
        ProductImage image = new ProductImage();
        image.setIdProduct(product);
        image.setUrl(fileName1);
        // replace("chuỗi cần thay","thay bằng")
        String nameFile = fileName1.replace(".","");
        image.setName(nameFile);

        product.setImage(image);
        productImageService.save(image);
        Product product1 = productRespon.findAllById(product.getId());
        product1.setImage(image);
        productService.save(product1);
        return "redirect:/admin#product";
    }
}
