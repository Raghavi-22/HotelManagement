package demo.demo.Services;

import demo.demo.Module.Discount;
import demo.demo.Repository.DiscountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {

    private final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    // Get discounts available to the user
    public List<Discount> getDiscountsForUser(int score) {
        return discountRepository.findDiscountsByScore(score);
    }
    public Integer getScoreRequired(int discountId) {
        return discountRepository.getScoreRequiredByDiscountId(discountId);
    }


    // You can add more business logic related to discounts here
}
