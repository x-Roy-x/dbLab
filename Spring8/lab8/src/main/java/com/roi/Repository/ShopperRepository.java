package com.roi.Repository;

import com.roi.domain.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper, Long> {

}
