package com.roi.service;

import com.roi.Repository.ShopperRepository;
import com.roi.Repository.GoodRepository;
import com.roi.domain.Shopper;
import com.roi.domain.Good;
import com.roi.exceptions.ExistsShopperForGoodException;
import com.roi.exceptions.NoSuchShopperException;
import com.roi.exceptions.NoSuchGoodException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class GoodService {
    @Autowired
    GoodRepository goodRepository;

    @Autowired
    ShopperRepository shopperRepository;

    public Set<Good> getGoodsByShopperId(Long shooper_id) throws NoSuchShopperException {
        Shopper shopper = shopperRepository.findById(shooper_id).get();//2.0.0.M7
        if (shopper == null) throw new NoSuchShopperException();
        return shopper.getGoods();
    }

    public Good getGood(Long good_id) throws NoSuchGoodException {
        Good good = goodRepository.findById(good_id).get();//2.0.0.M7
        if (good == null) throw new NoSuchGoodException();
        return good;
    }

    public List<Good> getAllGoods() {
        return goodRepository.findAll();
    }

    @Transactional
    public void createGood(Good good) {
        goodRepository.save(good);
    }

    @Transactional
    public void updateGood(Good uGood, Long good_id) throws NoSuchGoodException {
        Good good= goodRepository.findById(good_id).get();//2.0.0.M7
        if (good == null) throw new NoSuchGoodException();
        //update
        good.setName_of_good(uGood.getName_of_good());
    }

    @Transactional
    public void deleteGood(Long good_id) throws NoSuchGoodException, ExistsShopperForGoodException {
        Good good = goodRepository.findById(good_id).get();//2.0.0.M7

        if (good == null) throw new NoSuchGoodException();
        if (good.getShopperSet().size() != 0) throw new ExistsShopperForGoodException();
        goodRepository.delete(good);
    }
}
