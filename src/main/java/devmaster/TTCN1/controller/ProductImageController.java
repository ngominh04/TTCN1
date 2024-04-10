package devmaster.TTCN1.controller;

import devmaster.TTCN1.domain.Category;
import devmaster.TTCN1.domain.Product;
import devmaster.TTCN1.domain.ProductImage;
import devmaster.TTCN1.respository.CategoryRespon;
import devmaster.TTCN1.respository.EvaluateRespon;
import devmaster.TTCN1.respository.ProductImageRespon;
import devmaster.TTCN1.respository.ProductRespon;
import devmaster.TTCN1.service.ProductImageService;
import devmaster.TTCN1.service.UploadFileService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/image")
public class ProductImageController {
    @Autowired
    ProductRespon productRespon;
    @Autowired
    ProductImageRespon productImageRespon;
    @Autowired
    UploadFileService uploadFileService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    EvaluateRespon evaluateRespon;
    @Autowired
    CategoryRespon categoryRespon;

    // quản lí lưu các ảnh của sản phẩm
    @PostMapping("/updateImgPro/{idPro}")
    public String updatePro(HttpSession session, Model model,
                            @PathVariable("idPro")Integer idPro,
                            @RequestParam("idImg")Integer idImg
                            ,@RequestParam("file1")MultipartFile file1,
                            @RequestParam("file2")MultipartFile file2,
                            @RequestParam("file3")MultipartFile file3
                            ,@RequestParam("img1")Integer img1,
                            @RequestParam("img2")Integer img2,
                            @RequestParam("img3")Integer img3
    ){
        ProductImage productImage = productImageRespon.getById(idImg);
        // lưu ảnh chính

        Product product = productRespon.getProductChiTiet(idPro);
        product.setImage(productImage);

        productRespon.save(product);

        uploadFileService.store(file1);
        uploadFileService.store(file2);
        uploadFileService.store(file3);
        // lưu lại ảnh của pro
        String fileName1 = StringUtils.cleanPath(file1.getOriginalFilename());
        String fileName2 = StringUtils.cleanPath(file2.getOriginalFilename());
        String fileName3 = StringUtils.cleanPath(file3.getOriginalFilename());
        int index = 1;
        // Lấy ra toan bộ ảnh của 1 product
        List<ProductImage> image = productImageRespon.getByIdPro(idPro);
        // trường hợp chỉ có 1 ảnh và 2 ảnh
        if (image.size() == 1 || image.size() == 2){
            for (ProductImage image_0:image) {
                if (img1 == image_0.getId() && fileName1 !=""){ // lưu thay đổi ảnh thứ nhất nếu có
                    image_0.setUrl(fileName1);
                    // replace("chuỗi cần thay","thay bằng")
                    String nameFile = fileName1.replace(".","");
                    image_0.setName(nameFile);
                    productImageService.save(image_0);
                }
                if (img2 == image_0.getId() && fileName2 !="" && image.size() == 2) { // lưu thay đổi ảnh thứ nhất nếu có
                    image_0.setUrl(fileName2);
                    // replace("chuỗi cần thay","thay bằng")
                    String nameFile = fileName2.replace(".", "");
                    image_0.setName(nameFile);
                    productImageService.save(image_0);
                }
            }
            if (image.size() == 1 && fileName2 !=""){ // lưu ảnh thứ 2 nếu có, ở trường hợp 1 ảnh
                ProductImage image1 = new ProductImage();
                image1.setIdProduct(product);
                image1.setUrl(fileName2);
                // replace("chuỗi cần thay","thay bằng")
                String nameFile = fileName2.replace(".","");
                image1.setName(nameFile);
                productImageService.save(image1);
            }
            if (image.size() == 1 && fileName3 !=""){ // lưu ảnh thứ 3 nếu có, ở trường hợp 1 ảnh
                ProductImage image1 = new ProductImage();
                image1.setIdProduct(product);
                image1.setUrl(fileName3);
                // replace("chuỗi cần thay","thay bằng")
                String nameFile = fileName3.replace(".","");
                image1.setName(nameFile);
                productImageService.save(image1);
            }
            if (image.size() == 2 && fileName3 !=""){ // lưu ảnh thứ 3 nếu có, ở trường hợp 2 ảnh
                ProductImage image1 = new ProductImage();
                image1.setIdProduct(product);
                image1.setUrl(fileName3);
                // replace("chuỗi cần thay","thay bằng")
                String nameFile = fileName3.replace(".","");
                image1.setName(nameFile);
                productImageService.save(image1);
            }
            return "redirect:/product/detailPro/{idPro}";
        }
        // trường hợp thay đổi ảnh khi có đủ 3 ảnh
        for (ProductImage image0:image) {
            if (img1 == image0.getId() && fileName1 !="" && image.size() == 3){ // lưu thay đổi ảnh thứ 1 nếu có
                image0.setUrl(fileName1);
                // replace("chuỗi cần thay","thay bằng")
                String nameFile = fileName1.replace(".","");
                image0.setName(nameFile);
                productImageService.save(image0);
            }
            if (img2 == image0.getId() && fileName2 !="" && image.size() == 3){ // lưu thay đổi ảnh thứ 2 nếu có
                image0.setUrl(fileName2);
                // replace("chuỗi cần thay","thay bằng")
                String nameFile = fileName2.replace(".","");
                image0.setName(nameFile);
                productImageService.save(image0);
            }
            if (img3 == image0.getId() && fileName3 !="" && image.size() == 3){ // lưu thay đổi ảnh thứ 3 nếu có
                image0.setUrl(fileName3);
                // replace("chuỗi cần thay","thay bằng")
                String nameFile = fileName3.replace(".","");
                image0.setName(nameFile);
                productImageService.save(image0);
            }

        }
        return "redirect:/product/detailPro/{idPro}";
    }
}
