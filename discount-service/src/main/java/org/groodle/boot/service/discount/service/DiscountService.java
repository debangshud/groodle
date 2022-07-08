package org.groodle.boot.service.discount.service;

import lombok.extern.slf4j.Slf4j;
import org.groodle.boot.service.discount.model.Discount;
import org.groodle.boot.service.discount.model.HotelInventory;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DiscountService {

    private KieContainer kContainer;

    public DiscountService(KieContainer kContainer) {
        this.kContainer = kContainer;
    }

    public Discount getDiscount(Long id){
        HotelInventory hotelInventory = new HotelInventory();
        hotelInventory.setRoomType("King");
        Discount discount = new Discount();
        KieSession kieSession = kContainer.newKieSession();
        kieSession.setGlobal("discount", discount);
        kieSession.insert(hotelInventory);
        kieSession.fireAllRules();
        kieSession.dispose();
        log.info("Discount: " + discount);
        return discount;
    }
}
