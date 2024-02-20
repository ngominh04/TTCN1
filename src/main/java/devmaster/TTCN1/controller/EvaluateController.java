package devmaster.TTCN1.controller;

import devmaster.TTCN1.respository.EvaluateRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/evaluate")
public class EvaluateController {
    @Autowired
    EvaluateRespon evaluateRespon;

    @GetMapping("/evaluate/{idPro}")
    public String showAllEvaluateByIdPro(@PathVariable("idPro")Integer idPro){
        return "/admin/evaluate/showEvaluateByIdPro";
    }


}
