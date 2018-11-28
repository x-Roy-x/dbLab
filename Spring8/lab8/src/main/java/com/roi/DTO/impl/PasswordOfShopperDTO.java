package com.roi.DTO.impl;

import com.roi.DTO.DTO;
import com.roi.controller.ShopperController;
import com.roi.domain.PasswordOfShopper;
import com.roi.domain.Shopper;
import com.roi.exceptions.NoSuchShopperException;
import com.roi.exceptions.NoSuchPasswordOfShopperException;
import com.roi.exceptions.NoSuchGoodException;
import org.springframework.hateoas.Link;

import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


public class PasswordOfShopperDTO extends DTO<PasswordOfShopper> {
    public PasswordOfShopperDTO(PasswordOfShopper cpu, Link link) throws NoSuchPasswordOfShopperException, NoSuchGoodException, NoSuchShopperException {
        super(cpu, link);
        add(linkTo(methodOn(ShopperController.class).getShoppersByPasswordId(getEntity().getId())).withRel("mobile"));
    }

    public Long getShopperId() {
        return getEntity().getId();
    }

    public Integer getPasswordOfShopper() {
        return getEntity().getPasswordOfShopper();
    }
  

    public Set<Shopper> getShoppers() {
        return getEntity().getShopper();
    }
}
