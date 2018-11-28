package com.roi.service;

import com.roi.Repository.PasswordOfShopperRepository;
import com.roi.Repository.ShopperRepository;
import com.roi.domain.PasswordOfShopper;
import com.roi.exceptions.ExistsShopperForPasswordOfShopperException;
import com.roi.exceptions.NoSuchPasswordOfShopperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PasswordOfShopperService {
    @Autowired
    PasswordOfShopperRepository passwordOfShopperRepository;
    private boolean ascending;

    @Autowired
    ShopperRepository shopperRepository;

    public List<PasswordOfShopper> getAllPasswordOfShoppers() {
        return passwordOfShopperRepository.findAll();
    }

    public PasswordOfShopper getPasswordOfShopper(Long shopper_id) throws NoSuchPasswordOfShopperException {
        PasswordOfShopper passwordOfShopper = passwordOfShopperRepository.findById(shopper_id).get();//2.0.0.M7
        System.out.println(passwordOfShopper);
        if (passwordOfShopper == null) throw new NoSuchPasswordOfShopperException();
        return passwordOfShopper;
    }

    @Transactional
    public void createPasswordOfShopper(PasswordOfShopper passwordOfShopper) {
        passwordOfShopperRepository.save(passwordOfShopper);
    }

    @Transactional
    public void updatePasswordOfShopper(PasswordOfShopper passwordOfShopper, Long shopper_id) throws NoSuchPasswordOfShopperException {
        PasswordOfShopper passwordOfShopper1 = passwordOfShopperRepository.findById(shopper_id).get();//2.0.0.M7

        if (passwordOfShopper1 == null) throw new NoSuchPasswordOfShopperException();
        passwordOfShopper1.setShopper(passwordOfShopper.getShopper());
        passwordOfShopperRepository.save(passwordOfShopper1);
    }

    @Transactional
    public void updatePasswordOfShopper(Long shopper_id) throws NoSuchPasswordOfShopperException, ExistsShopperForPasswordOfShopperException {
        PasswordOfShopper passwordOfShopper = passwordOfShopperRepository.findById(shopper_id).get();//2.0.0.M7
        if (passwordOfShopper == null) throw new NoSuchPasswordOfShopperException();
        if (passwordOfShopper.getShopper().size() != 0) throw new ExistsShopperForPasswordOfShopperException();
        passwordOfShopperRepository.delete(passwordOfShopper);
    }


}
