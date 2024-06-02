package devmaster.TTCN1.controller;

import devmaster.TTCN1.domain.Category;
import devmaster.TTCN1.respository.CategoryRespon;
import devmaster.TTCN1.service.CategoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryRespon categoryRespon;
    @Autowired
    CategoryService categoryService;

    // click vào sửa
    @GetMapping("/updateCate/{idCate}")
    public String updateCate(@PathVariable("idCate")Integer idCate, HttpSession session, Model model){
        Category category = categoryRespon.findAllById(idCate);
        model.addAttribute("updateCate",category);
        session.setAttribute("updateCate",category);
        return "/admin/category/updateCate";
    }

    // khi ấn submit form
    @PostMapping("/updateCate/{idCate}")
    public String updateCate(@PathVariable("idCate")Integer idCate,
                             @RequestParam("idParent")Integer idParent,
                             @RequestParam("name")String name,
                             @RequestParam("notes")String notes,
                             HttpSession session){
//        Category category = (Category) session.getAttribute("updateCate");
        Category category = categoryRespon.findAllById(idCate);
        category.setIdparent(idParent);
        category.setName(name);
        category.setNotes(notes);
        category.setUpdatedDate(String.valueOf(LocalDateTime.now()));
        categoryService.save(category);
        session.removeAttribute("updateCate");
        return "redirect:/admin#category";
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
    // lưu thêm mới cate
    @PostMapping("/newCate")
    public String newCate(@RequestParam("idParent")Integer idParent,
                          @RequestParam("name")String name,
                          @RequestParam("notes")String notes){
        Category category = new Category();
        category.setIdparent(idParent);
        category.setName(name);
        category.setNotes(notes);
        category.setCreatedDate(String.valueOf(LocalDateTime.now()));
        category.setIsActive((byte) 1);
        category.setIsDelete((byte) 1);
        categoryService.save(category);
        return "redirect:/admin#category";
    }

}
