package alex.falendish.dao;

import alex.falendish.model.PromoCode;

import java.util.Optional;

public interface PromoCodeDAO {

    PromoCode create(PromoCode promoCode);

    Optional<PromoCode> findOneActive(String promoCode);
}
