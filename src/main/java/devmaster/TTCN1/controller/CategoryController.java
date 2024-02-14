package devmaster.TTCN1.controller;

import devmaster.TTCN1.domain.Category;
import devmaster.TTCN1.respository.CategoryRespon;
import devmaster.TTCN1.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryRespon categoryRespon;
    @Autowired
    CategoryService categoryService;

    // click vào sửa
    @GetMapping("/updateCate")
    public String updateCate(){
        return "/admin/category/updateCate";
    }
    // click vào tắt ở Is active -> chuyển isactive = 0
    @GetMapping("/activeCateOff/{idCate}")
    public String acctiveCateOff(@PathVariable("idCate")Integer idCate){
        Category category = categoryRespon.findAllById(idCate);
        category.setIsActive((byte) 0);
        categoryService.save(category);
        return "redirect:/admin#category";
    }
    // click vào bật ở Is active -> chuyển isactive = 1
    @GetMapping("/activeCateOn/{idCate}")
    public String activeCateOn(@PathVariable("idCate")Integer idCate){
        Category category = categoryRespon.findAllById(idCate);
        category.setIsActive((byte) 1);
        categoryService.save(category);
        return "redirect:/admin#category";
    }
    // click vào tắt ở Is delete -> chuyển isdelete = 0
    @GetMapping("/deleteCateOff/{idCate}")
    public String deleteCateOff(@PathVariable("idCate")Integer idCate){
        Category category = categoryRespon.findAllById(idCate);
        category.setIsDelete((byte) 0);
        categoryService.save(category);
        return "redirect:/admin#category";
    }
    // click vào bật ở Is delete -> chuyển isdelete = 1
    @GetMapping("/deleteCateOn/{idCate}")
    public String deleteCateOn(@PathVariable("idCate")Integer idCate){
        Category category = categoryRespon.findAllById(idCate);
        category.setIsDelete((byte) 1);
        categoryService.save(category);
        return "redirect:/admin#category";
    }
    // click thêm mới category
    @GetMapping("newCate")
    public String newCate(){
        return "/admin/category/newCate";
    }
}
