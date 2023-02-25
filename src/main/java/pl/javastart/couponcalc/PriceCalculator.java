package pl.javastart.couponcalc;

import java.math.RoundingMode;
import java.util.List;

public class PriceCalculator {

    public double calculatePrice(List<Product> products, List<Coupon> coupons) {
        double result = 0;
        if (products == null) {
            return 0;
        }
        if (coupons == null) {
            for (Product product : products) {
                result += product.getPrice();
            }
        } else if (coupons.size() == 1 && products.size() > 1) {
            for (Product product : products) {
                for (Coupon coupon : coupons) {
                    if (coupon.getCategory() != null) {
                        if (coupon.getCategory().equals(product.getCategory())) {
                            result += product.getPrice() - (product.getPrice() * (coupon.getDiscountValueInPercents() * 0.01));
                        }
                    } else if (coupon.getCategory() == null) {
                        result += product.getPrice() - (product.getPrice() * 0.1);
                    }
                }
            }
        } else if (products.size() == 1 && coupons.size() == 1) {
            double price = products.get(0).getPrice();
            int discount = coupons.get(0).getDiscountValueInPercents();
            double calculateDiscount = price * (discount * 0.01);
            double afterDiscount = price - calculateDiscount;
            result = Math.round(afterDiscount * 100) / 100.0;
        } else if (coupons.size() > 1 && products.size() > 1) {
            for (Coupon coupon : coupons) {
                //tutaj nie wiem jak wlasnie napisac na wiecej niz jeden kod rabatowy.
            }
        }
        return result;
    }
}