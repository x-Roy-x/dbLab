package com.roi.service;

import com.roi.Repository.PasswordOfShopperRepository;
import com.roi.Repository.ShopperRepository;
import com.roi.Repository.GoodRepository;
import com.roi.domain.PasswordOfShopper;
import com.roi.domain.Shopper;
import com.roi.domain.Good;
import com.roi.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class ShopperService {
    @Autowired
    ShopperRepository shopperRepository;

    @Autowired
    PasswordOfShopperRepository passwordOfShopperRepository;

    @Autowired
    GoodRepository goodRepository;

    public Set<Shopper> getShopperByPasswordId(Long shopper_id) throws NoSuchPasswordOfShopperException {
        PasswordOfShopper passwordOfShopper = passwordOfShopperRepository.findById(shopper_id).get();//2.0.0.M7
        if (passwordOfShopper == null) throw new NoSuchPasswordOfShopperException();
        return passwordOfShopper.getShopper();
    }

    public Shopper getShopper(Long shopper_id) throws NoSuchShopperException {
        Shopper shopper = shopperRepository.findById(shopper_id).get();//2.0.0.M7
        if (shopper == null) throw new NoSuchShopperException();
        return shopper;
    }

    public List<Shopper> getAllShoppers() {
        return shopperRepository.findAll();
    }

    public Set<Shopper> getShopperByGoodId(Long good_id) throws NoSuchGoodException {
        Good good = goodRepository.findById(good_id).get();//2.0.0.M7
        if (good == null) throw new NoSuchGoodException();
        return good.getShopperSet();
    }

    @Transactional
    public void createShopper(Shopper shopper, Long password_of_shopper_id) throws NoSuchPasswordOfShopperException {
        if (password_of_shopper_id > 0) {
            PasswordOfShopper passwordOfShopper = passwordOfShopperRepository.findById(password_of_shopper_id).get();//2.0.0.M7
            if (passwordOfShopper == null) throw new NoSuchPasswordOfShopperException();
            shopper.setPasswordByPassword(passwordOfShopper);
        }
        shopperRepository.save(shopper);
    }

    @Transactional
    public void updateShopper(Shopper uShopper, Long shopper_id, Long shopperId) throws NoSuchPasswordOfShopperException, NoSuchShopperException {
        PasswordOfShopper passwordOfShopper = passwordOfShopperRepository.findById(shopperId).get();//2.0.0.M7
        if (shopperId > 0) {
            if (passwordOfShopper == null) throw new NoSuchPasswordOfShopperException();
        }
        Shopper shopper = shopperRepository.findById(shopper_id).get();//2.0.0.M7
        if (shopper == null) throw new NoSuchShopperException();
        shopper.setName_of_shopper(uShopper.getName_of_shopper());
        shopper.setSurname_of_shopper(uShopper.getSurname_of_shopper());
        if (shopperId > 0) shopper.setPasswordByPassword(passwordOfShopper);
        else shopper.setPasswordByPassword(null);
        shopperRepository.save(shopper);
    }

    @Transactional
    public void deleteShopper(Long shopper_id) throws NoSuchShopperException, ExistsGoodForShopperException {
        Shopper shopper = shopperRepository.findById(shopper_id).get();//2.0.0.M7
        if (shopper == null) throw new NoSuchShopperException();
        if (shopper.getGoods().size() != 0) throw new ExistsGoodForShopperException();
        shopperRepository.delete(shopper);
    }

    @Transactional
    public void addGoodForShopper(Long shopper_id, Long good_id)
            throws NoSuchShopperException, NoSuchGoodException, AlreadyExistsGoodInShopperException, GoodAbsentException {
        Shopper shopper = shopperRepository.findById(shopper_id).get();//2.0.0.M7
        if (shopper == null) throw new NoSuchShopperException();
        Good lecturer = goodRepository.findById(good_id).get();//2.0.0.M7
        if (lecturer == null) throw new NoSuchGoodException();
        if (shopper.getGoods().contains(lecturer) == true) throw new AlreadyExistsGoodInShopperException();
        shopper.getGoods().add(lecturer);
        shopperRepository.save(shopper);
    }

    @Transactional
    public void removeGoodForShopper(Long shopper_id, Long good_id)
            throws NoSuchShopperException, NoSuchGoodException, ShopperHasNotGoodException {
        Shopper shopper = shopperRepository.findById(shopper_id).get();//2.0.0.M7
        if (shopper == null) throw new NoSuchShopperException();
        Good good= goodRepository.findById(good_id).get();//2.0.0.M7
        if (good == null) throw new NoSuchGoodException();
        if (shopper.getGoods().contains(good) == false) throw new ShopperHasNotGoodException();
        shopper.getGoods().remove(good);
        shopperRepository.save(shopper);
    }
}
