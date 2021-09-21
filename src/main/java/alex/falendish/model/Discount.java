package alex.falendish.model;

import alex.falendish.utils.DiscountType;

import java.math.BigDecimal;

public class Discount {

    private DiscountType discountType;
    private String promoCode;
    private BigDecimal discount;

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
