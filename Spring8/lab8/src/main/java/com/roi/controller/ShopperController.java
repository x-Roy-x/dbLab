package com.roi.controller;

import com.roi.DTO.impl.ShopperDTO;
import com.roi.domain.Shopper;
import com.roi.exceptions.*;
import com.roi.service.ShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ShopperController {
    @Autowired
    ShopperService shopperService;
// get Shopper by class id
    @GetMapping(value = "/api/shopper/password_of_shopper/{password_of_shopper_id}")
    public ResponseEntity<List<ShopperDTO>> getShoppersByPasswordId(@PathVariable Long password_of_shopper_id) throws NoSuchPasswordOfShopperException, NoSuchShopperException, NoSuchGoodException {
        Set<Shopper> shopperByPasswordId= shopperService.getShopperByPasswordId(password_of_shopper_id);

        Link link = linkTo(methodOn(ShopperController.class).getAllShoppers()).withSelfRel();

        List<ShopperDTO> shopperDTOS = new ArrayList<>();
        for (Shopper entity : shopperByPasswordId) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ShopperDTO dto = new ShopperDTO(entity, selfLink);
            shopperDTOS.add(dto);
        }

        return new ResponseEntity<>(shopperDTOS, HttpStatus.OK);
    }
// get Shopper
    @GetMapping(value = "/api/shopper/{shopper_id}")
    public ResponseEntity<ShopperDTO> getShoppers(@PathVariable Long shopper_id) throws NoSuchShopperException, NoSuchGoodException, NoSuchPasswordOfShopperException {
        Shopper shopper = shopperService.getShopper(shopper_id);
        Link link = linkTo(methodOn(ShopperController.class).getShoppers(shopper_id)).withSelfRel();

        ShopperDTO shopperDTO = new ShopperDTO(shopper, link);

        return new ResponseEntity<>(shopperDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/shopper")
    public ResponseEntity<Set<ShopperDTO>> getAllShoppers() throws NoSuchShopperException, NoSuchGoodException, NoSuchPasswordOfShopperException {
        List<Shopper> allShoppers = shopperService.getAllShoppers();
        Link link = linkTo(methodOn(ShopperController.class).getAllShoppers()).withSelfRel();

        Set<ShopperDTO> shopperDTOS = new HashSet<>();
        for (Shopper entity : allShoppers) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ShopperDTO dto = new ShopperDTO(entity, selfLink);
            shopperDTOS.add(dto);
        }

        return new ResponseEntity<>(shopperDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/api/shopper/good/{good_id}")
    public ResponseEntity<Set<ShopperDTO>> getShoppersByGoodID(@PathVariable Long good_id) throws NoSuchGoodException, NoSuchShopperException, NoSuchPasswordOfShopperException {
        Set<Shopper> shopperByGoodId = shopperService.getShopperByGoodId(good_id);
        Link link = linkTo(methodOn(ShopperController.class).getAllShoppers()).withSelfRel();

        Set<ShopperDTO> shopperDTOS = new HashSet<>();
        for (Shopper entity : shopperByGoodId) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ShopperDTO dto = new ShopperDTO(entity, selfLink);
            shopperDTOS.add(dto);
        }

        return new ResponseEntity<>(shopperDTOS, HttpStatus.OK);
    }
// add Shopper
    @PostMapping(value = "/api/shopper/password_of_shopper/{password_of_shopper_id}")
    public  ResponseEntity<ShopperDTO> addShopper(@RequestBody Shopper shopper, @PathVariable Long password_of_shopper_id)
            throws NoSuchPasswordOfShopperException, NoSuchShopperException, NoSuchGoodException {
        shopperService.createShopper(shopper, password_of_shopper_id);
        Link link = linkTo(methodOn(ShopperController.class).getShoppers(shopper.getId())).withSelfRel();

        ShopperDTO shopperDTO = new ShopperDTO(shopper, link);

        return new ResponseEntity<>(shopperDTO, HttpStatus.CREATED);
    }
//update Shopper
    @PutMapping(value = "/api/shopper/{shopper_id}/password_of_shopper/{password_of_shopper_id}")
    public  ResponseEntity<ShopperDTO> updateShopper(@RequestBody Shopper shopper,
                                                     @PathVariable Long shopper_id, @PathVariable Long password_of_shoopper_id)
            throws NoSuchPasswordOfShopperException, NoSuchShopperException, NoSuchGoodException {
        shopperService.updateShopper(shopper, shopper_id, password_of_shoopper_id);
        Shopper shopper1 = shopperService.getShopper(shopper_id);
        Link link = linkTo(methodOn(ShopperController.class).getShoppers(shopper_id)).withSelfRel();

        ShopperDTO shopperDTO = new ShopperDTO(shopper1, link);

        return new ResponseEntity<>(shopperDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/shopper/{shopper_id}")
    public  ResponseEntity deleteShopper(@PathVariable Long shopper_id) throws NoSuchShopperException, ExistsGoodForShopperException, ExistsGoodForShopperException {
        shopperService.deleteShopper(shopper_id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/api/shopper/{shopper_id}/good/{good_id}")
    public  ResponseEntity<ShopperDTO> addGoodForShopper(@PathVariable Long shopper_id, @PathVariable Long good_id)
            throws NoSuchShopperException, NoSuchGoodException, NoSuchPasswordOfShopperException, AlreadyExistsGoodInShopperException, GoodAbsentException {
        shopperService.addGoodForShopper(shopper_id,good_id);
        Shopper shopper = shopperService.getShopper(shopper_id);
        Link link = linkTo(methodOn(ShopperController.class).getShoppers(shopper_id)).withSelfRel();

        ShopperDTO shopperDTO = new ShopperDTO(shopper, link);

        return new ResponseEntity<>(shopperDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/shopper/{shopper_id}/{good_id}")
    public  ResponseEntity<ShopperDTO> removeGoodForShopper(@PathVariable Long shopper_id, @PathVariable Long good_id)
            throws NoSuchShopperException, NoSuchGoodException, NoSuchPasswordOfShopperException, ShopperHasNotGoodException {
        shopperService.removeGoodForShopper(shopper_id,good_id);
        Shopper shopper = shopperService.getShopper(shopper_id);
        Link link = linkTo(methodOn(ShopperController.class).getShoppers(good_id)).withSelfRel();

        ShopperDTO shopperDTO = new ShopperDTO(shopper, link);

        return new ResponseEntity<>(shopperDTO, HttpStatus.OK);
    }

}

