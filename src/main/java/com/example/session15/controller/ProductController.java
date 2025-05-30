package com.example.session15.controller;

import com.example.session15.model.Cart;
import com.example.session15.model.Order;
import com.example.session15.model.Product;
import com.example.session15.model.Review;
import com.example.session15.service.cart.CartServiceImp;
import com.example.session15.service.product.ProductServiceImp;
import com.example.session15.service.review.ReviewServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class ProductController {

    @Autowired
    private ReviewServiceImp reviewService;

    @Autowired
    private ProductServiceImp productService;

    @Autowired
    private CartServiceImp cartService;

    @GetMapping("searchProduct")
    public String searchProduct(Model model) {
        model.addAttribute("product", new Product());
        return "searchProduct";
    }

    @PostMapping("search")
    public String searchPro(@ModelAttribute("product") Product product, Model model) {
        for (Product p : productService.getProducts()) {
            if (p.getName().equals(product.getName())) {
                model.addAttribute("productFind", p);
                return "searchProduct";
            }
        }
        model.addAttribute("message", "Không tìm thấy sản phẩm nào");
        return "searchProduct";
    }

    @GetMapping("listProduct")
    public String listProduct(Model model) {
        model.addAttribute("products", productService.getProducts());
        return "listProduct";
    }

    @GetMapping("productDetail")
    public String productDetail(@RequestParam("id") int id, Model model) {
        for (Product p : productService.getProducts()) {
            if (p.getId() == id) {
                model.addAttribute("productFind", p);
                model.addAttribute("reviews", reviewService.getReviewsByProduct(id));
                return "productDetail";
            }
        }
        return "productDetail";
    }

    @GetMapping("review")
    public String review(@RequestParam("id") int id, Model model) {
        Review review = new Review();
        model.addAttribute("idProduct", id);
        model.addAttribute("review", review);
        return "witerReview";
    }

    @PostMapping("write-review")
    public String writeReview(@ModelAttribute("review") Review review, @ModelAttribute("idProduct") int idProduct) {
        review.setProductId(idProduct);
        review.setUserId(1);
        reviewService.saveReview(review);
        return "redirect:/productDetail?id=" + idProduct;
    }
    @PostMapping("addToCart")
    public String addToCart(@RequestParam("productId") int productId) {
        Cart cart = new Cart();
        cart.setIdUser(1);
        cart.setIdProduct(productId);
        cartService.addToCart(cart);
        return "redirect:/listCart";
    }

    @GetMapping("listCart")
    public String listCart(Model model) {
        List<Cart> carts = cartService.getAllCarts(1);
        List<Product> allProducts = productService.getProducts();

        List<Map<String, Object>> cartItems = new ArrayList<>();
        for (Cart c : carts) {
            for (Product p : allProducts) {
                if (p.getId() == c.getIdProduct()) {
                    Map<String, Object> item = new HashMap<>();
                    item.put("name", p.getName());
                    item.put("price", p.getPrice());
                    item.put("quantity", c.getQuantity());
                    item.put("subtotal", p.getPrice() * c.getQuantity());
                    cartItems.add(item);
                    break;
                }
            }
        }

        int total = cartService.getTotalCarts(1);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        return "listCart";
    }
    @GetMapping("checkout")
    public String showCheckout(Model model) {
        int userId = 1;
        List<Cart> carts = cartService.getAllCarts(userId);
        List<Product> allProducts = productService.getProducts();

        List<Map<String, Object>> cartItems = new ArrayList<>();
        int total = 0;

        for (Cart c : carts) {
            for (Product p : allProducts) {
                if (p.getId() == c.getIdProduct()) {
                    Map<String, Object> item = new HashMap<>();
                    item.put("name", p.getName());
                    item.put("price", p.getPrice());
                    item.put("quantity", c.getQuantity());
                    item.put("subtotal", p.getPrice() * c.getQuantity());
                    cartItems.add(item);
                    total += p.getPrice() * c.getQuantity();
                    break;
                }
            }
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);
        model.addAttribute("order", new Order());
        return "checkout";
    }

}

