package devmaster.TTCN1.controller;

import devmaster.TTCN1.projection.IProduct;
import devmaster.TTCN1.respository.ProductRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/statistics")
// thống kê dữ liệu controller
public class dataStatisticsController {
    @Autowired
    ProductRespon productRespon;

    @PostMapping("")
    public String showProductSold_12month(Model model,
                                          @RequestParam("fromDate")String fromDate,
                                          @RequestParam("toDate")String toDate){
//        String fromDateStr = String.valueOf(fromDate);
//        String toDateStr = String.valueOf(toDate);
        List<IProduct> list = productRespon.getProductSold(fromDate,toDate);;

        double totalPrice = 0;
        for (IProduct product: list) {
            totalPrice += product.getPrice()* product.getSold();
        }
        model.addAttribute("totalPrice",totalPrice);
        model.addAttribute("productStatistics",list);
        return "admin/index";
    }
}
