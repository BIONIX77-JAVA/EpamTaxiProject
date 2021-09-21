package alex.falendish.utils;

import java.math.BigDecimal;

public enum DiscountType {

    BONUS_RIDE(BigDecimal.valueOf(50)),
    PROMO_CODE(BigDecimal.ZERO);


    private final BigDecimal percent;

    DiscountType(BigDecimal percent) {
        this.percent = percent;
    }

    public BigDecimal getPercent() {
        return percent;
    }
}
