package com.roi.DTO.impl;

import com.roi.DTO.DTO;
import com.roi.domain.Good;
import com.roi.exceptions.NoSuchGoodException;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class GoodDTO extends DTO<Good> {
    public GoodDTO(Good customer, Link link) throws NoSuchGoodException {
        super(customer, link);
    }

    public Long getGoodId() {
        return getEntity().getId();
    }

    public String getName_of_good() {
        return getEntity().getName_of_good();
    }

    public String getCountry_of_manufacture() {
        return getEntity().getCountry_of_manufacture();
    }
}
