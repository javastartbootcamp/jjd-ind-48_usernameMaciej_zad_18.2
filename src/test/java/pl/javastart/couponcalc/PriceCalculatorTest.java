package pl.javastart.couponcalc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceCalculatorTest {

    @Test
    public void shouldReturnZeroForNoProducts() {
        // given
        PriceCalculator priceCalculator = new PriceCalculator();

        // when
        double result = priceCalculator.calculatePrice(null, null);

        // then
        assertThat(result).isEqualTo(0.);
    }

    @Test
    public void shouldReturnPriceForSingleProductAndNoCoupons() {
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        // given
        products.add(new Product("Masło", 5.99, Category.FOOD));

        // when
        double result = priceCalculator.calculatePrice(products, null);

        // then
        assertThat(result).isEqualTo(5.99);
    }

    @Test
    public void shouldReturnPriceForSingleProductAndOneCoupon() {
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        List<Coupon> coupons = new ArrayList<>();
        // given
        products.add(new Product("Masło", 5.99, Category.FOOD));

        coupons.add(new Coupon(Category.FOOD, 20));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result).isEqualTo(4.79);
    }

    @Test
    public void shouldReturnSumProductsPriceWithOnlyOneCouponWithoutCurrentCategory() {
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        List<Coupon> coupons = new ArrayList<>();
        // given
        products.add(new Product("Szynka", 3.6, Category.FOOD));
        products.add(new Product("Ser", 5.8, Category.FOOD));
        products.add(new Product("Szczotka do włosów", 17, Category.HOME));

        coupons.add(new Coupon(null, 10));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result).isEqualTo(23.76);
    }

    @Test
    public void shouldReturnSumProductsPriceWithOnlyOneCouponWithCategory() {
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        List<Coupon> coupons = new ArrayList<>();
        // given
        products.add(new Product("Bułki", 2.5, Category.FOOD));
        products.add(new Product("Masło", 3.4, Category.FOOD));
        products.add(new Product("Odświeżacz powietrza", 17, Category.HOME));

        coupons.add(new Coupon(Category.FOOD, 20));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result).isEqualTo(4.72);
    }

    @Test
    public void shouldReturnSumProductsPriceWithMoreThanOneCoupon() {
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        List<Coupon> coupons = new ArrayList<>();
        // given
        products.add(new Product("Bułki", 2.5, Category.FOOD));
        products.add(new Product("Masło", 3.4, Category.FOOD));
        products.add(new Product("Żarówki", 17, Category.CAR));
        products.add(new Product("Alkohol", 240, Category.ENTERTAINMENT));
        products.add(new Product("Dekoracje na imprezę", 17, Category.ENTERTAINMENT));

        coupons.add(new Coupon(Category.FOOD, 10));
        coupons.add(new Coupon(Category.ENTERTAINMENT, 50));
        coupons.add(new Coupon(Category.CAR, 30));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result).isEqualTo(23.76);
    }

    @Test
    public void shouldReturnSumProductsPriceIfThereIsNoCoupons() {
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();

        // given
        products.add(new Product("Masło", 5.99, Category.FOOD));
        products.add(new Product("Ser", 3.49, Category.FOOD));
        products.add(new Product("Płyn hamulcowy", 100, Category.CAR));

        // when
        double result = priceCalculator.calculatePrice(products, null);

        // then
        assertThat(result).isEqualTo(109.48);
    }

}