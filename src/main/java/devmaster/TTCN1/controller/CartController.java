package devmaster.TTCN1.controller;

import devmaster.TTCN1.domain.Cart;
import devmaster.TTCN1.domain.Customer;
import devmaster.TTCN1.domain.Product;
import devmaster.TTCN1.respository.CartRespon;
import devmaster.TTCN1.respository.ProductRespon;
import devmaster.TTCN1.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/shopping_cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    ProductRespon productRespon;
    @Autowired
    CartRespon cartRespon;

    @GetMapping("/cart/{idCus}")
    public String showCart(Model model,@PathVariable("idCus") Integer idCus){
        model.addAttribute("cart",cartRespon.getCart(idCus));
        List<Cart> carts = cartRespon.getCart(idCus);
        double total = 0;
        for (Cart cart:carts) {
            total = total+ cart.getQuantity()*cart.getPrice();
            model.addAttribute("total",total);
        }

        return "user/cart/Cart";
    }
    @GetMapping("/add/{idCus}/{idPro}")
    public String getAddCart(@PathVariable("idCus")Integer idCus, @PathVariable("idPro") Integer idPro, HttpSession session){
        Customer customer= (Customer) session.getAttribute("saveCus");
        Product product = productRespon.findAllById(idPro.intValue());
        boolean checkCart = false; // biến kiểm tra idPro trong giỏ
        if (product != null){
            List<Cart> carts = cartRespon.getCart(idCus);
            for (Cart cart: carts) {
                if (product.getId().equals(cart.getIdPrduct())){ // nếu tồn tại idPro của cart == id của product
                    cart.setQuantity(cart.getQuantity()+1);
                    cart.setName(product.getName());
                    cart.setPrice(product.getPrice());
                    cart.setImage(product.getImage());
                    cart.setIdPrduct(product.getId());
                    cart.setIdCustomer(customer.getId());
                    cartService.save(cart);
                    checkCart = false;
                    break; // thoát vòng lặp nếu tồn tại điều kiện
                }else  {
                    checkCart=true;
                }
            }
            if (checkCart || carts.size() == 0){ // khi duyệt hết vòng lặp nếu checkCart == true thì tạo 1 cart mới
                Cart cart1 = new Cart();
                cart1.setName(product.getName());
                cart1.setPrice(product.getPrice());
                cart1.setQuantity(1);
                cart1.setImage(product.getImage());
                cart1.setIdPrduct(product.getId());
                cart1.setIdCustomer(customer.getId());
                cartService.save(cart1);
            }
        }

        return "redirect:/shopping_cart/cart/{idCus}";
    }

    @GetMapping("/update1/{idCus}/{idCart}")// cập nhật khi ấn giảm số lượng
    public String update1(Model model,@PathVariable("idCart")Integer idCart,@PathVariable("idCus")Integer idCus){
        Cart cart = cartRespon.findAllById(idCart);
        if (cart.getQuantity() > 1){
            cart.setQuantity(cart.getQuantity() - 1);
        }else {
            cart.setQuantity(cart.getQuantity());
        }

        cartRespon.save(cart);

        return "redirect:/shopping_cart/cart/{idCus}";
    }
    @GetMapping("/update2/{idCus}/{idPro}/{idCart}")// cập nhật khi ấn tăng số lượng
    public String update2(@PathVariable("idCus")Integer idCus,@PathVariable("idCart")Integer idCart,@PathVariable("idPro")Integer idPro){
        Product product = productRespon.findAllById(idPro);

        Cart cart = cartRespon.findAllById(idCart);
        boolean checkQty = false;
        if (cart.getQuantity() >= 100){
            cart.setQuantity(100);
            checkQty = true;
        }
        if (cart.getQuantity() >= product.getQuatity()){
            cart.setQuantity(product.getQuatity());
            checkQty = true;
        }
        if (checkQty == false){
            cart.setQuantity(cart.getQuantity()+1);
        }
        cartRespon.save(cart);

        return "redirect:/shopping_cart/cart/{idCus}";
    }
    @PostMapping("update3/{idCus}") // cập nhật khi người dùng nhập vào ô input sô lượng sản phẩm
    public String update3(@RequestParam("idPro")Integer idPro,
                          @RequestParam("quantity")Integer quantity,
                          @RequestParam("idCart")Integer idCart,
                          @PathVariable("idCus")Integer idCus
                          ){
        Product product = productRespon.findAllById(idPro);
        Cart cart= cartRespon.findAllById(idCart);
        boolean checkCart = false;
        if(quantity >= 100){
            cart.setQuantity(100);
            checkCart =true;
        }
        if (quantity >= product.getQuatity()){
            cart.setQuantity(product.getQuatity()-1);
            checkCart =true;
        }
        if (quantity <= 1){
            cart.setQuantity(1);
            checkCart =true;
        }
        if (checkCart== false){
            cart.setQuantity(quantity);
        }
        cartService.save(cart);
        return "redirect:/shopping_cart/cart/{idCus}";
    }
    @GetMapping("/remove/{idCus}/{idCart}")
    public String removeCart(@PathVariable("idCart")Integer idCart,@PathVariable("idCus")Integer idCus){
        Cart cart = cartRespon.findAllById(idCart);
        cartService.delete(cart);
        return "redirect:/shopping_cart/cart/{idCus}";
    }

}
