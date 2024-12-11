package demo.demo.controller;

import demo.demo.Module.Discount;
import demo.demo.Module.DiscountAmount;
import demo.demo.Module.User;
import demo.demo.Services.DashboardService;
import demo.demo.Services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DiscountController {

    private final DiscountService discountService;
    private final DashboardService dashboardService;

    @Autowired
    public DiscountController(DiscountService discountService, DashboardService dashboardService) {
        this.discountService = discountService;
        this.dashboardService = dashboardService;
    }

    // Endpoint to get discounts available for a user
    @GetMapping("/discount")
    public ResponseEntity<List<Discount>> getDiscountsByUserId() {
        System.out.println("customer profile opened");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserDetails obj = (UserDetails) auth.getPrincipal();
        String pEmail = obj.getUsername();
        System.out.println(pEmail);
        User customer = dashboardService.getCustomerByEmail(pEmail);
System.out.println(customer.getScore());

        List<Discount> discounts = discountService.getDiscountsForUser(customer.getScore());
        System.out.println(discounts.get(0).getDiscountId());
        System.out.println(discounts.get(0).getDiscountName());
        System.out.println(discounts.get(0).getDescription());
//        System.out.println(discounts.get(1).getDiscountId());
//        System.out.println(discounts.get(1).getDiscountName());
//        System.out.println(discounts.get(1).getDescription());
        return ResponseEntity.ok(discounts);
    }

    @PostMapping("/getamount")
    public ResponseEntity<?> getDiscountedAmount(@RequestBody DiscountAmount discountAmount) {
        System.out.println("RESHMA");
        double newCost;
        double originalCost = discountAmount.getCost();
        System.out.println(discountAmount.getDiscountID());

        switch (discountAmount.getDiscountID()) {
            case 1:
                newCost = Math.max(originalCost * 0.95, originalCost - 200); // Min 5%, 200
                break;
            case 2:
                newCost = Math.max(originalCost * 0.90, originalCost - 500); // Min 10%, 500
                break;
            case 3:
                newCost = Math.max(originalCost * 0.85, originalCost - 800); // Min 15%, 800
                break;
            case 4:
                newCost = Math.max(originalCost * 0.80, originalCost - 1200); // Min 20%, 1200
                break;
            default:
                return ResponseEntity.badRequest().body("Invalid discount code.");
        }

        // Wrap the newCost in a Map for JSON serialization
        Map<String, Double> response = new HashMap<>();
        response.put("cost", newCost);
        System.out.println(newCost);

        return ResponseEntity.ok(response);
    }



    // You can add more endpoints for CRUD operations on discounts
}
