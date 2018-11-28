package com.roi.DTO.impl;

import com.roi.DTO.DTO;
import com.roi.domain.PasswordOfShopper;
import com.roi.domain.Shopper;
import com.roi.exceptions.NoSuchShopperException;
import com.roi.exceptions.NoSuchPasswordOfShopperException;
import com.roi.exceptions.NoSuchGoodException;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class ShopperDTO extends DTO<Shopper> {
    public ShopperDTO(Shopper mobile, Link link) throws NoSuchGoodException, NoSuchPasswordOfShopperException, NoSuchShopperException {
        super(mobile, link);
    }

    public Long getShopperId() {
        return getEntity().getId();
    }

    public String getName_of_shopper() {
        return getEntity().getName_of_shopper();
    }

    public String getSurname_of_shopper() {
        return getEntity().getSurname_of_shopper();
    }

    public PasswordOfShopper getPassword_by_shopper() {
        return getEntity().getPasswordByPassword();
    }


}
