package com.roi.controller;

import com.roi.DTO.impl.GoodDTO;
import com.roi.domain.Good;
import com.roi.exceptions.ExistsShopperForGoodException;
import com.roi.exceptions.NoSuchShopperException;
import com.roi.exceptions.NoSuchGoodException;
import com.roi.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class GoodController {
    @Autowired
    GoodService lecturerService;

    @GetMapping(value = "/api/Good/Shopper/{shopper_id}")
    public ResponseEntity<Set<GoodDTO>> getGoodByShopperID(@PathVariable Long shopper_id) throws NoSuchShopperException, NoSuchGoodException {
        Set<Good> goodSet = lecturerService.getGoodsByShopperId(shopper_id);
        Link link = linkTo(methodOn(GoodController.class).getAllGoods()).withSelfRel();

        Set<GoodDTO> goodDTOS = new HashSet<>();
        for (Good entity : goodSet) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            GoodDTO dto = new GoodDTO(entity, selfLink);
            goodDTOS.add(dto);
        }

        return new ResponseEntity<>(goodDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/api/Good/{good_id}")
    public ResponseEntity<GoodDTO> getGood(@PathVariable Long good_id) throws NoSuchGoodException, NoSuchShopperException {
        Good good = lecturerService.getGood(good_id);
        Link link = linkTo(methodOn(GoodController.class).getGood(good_id)).withSelfRel();

        GoodDTO goodDTO = new GoodDTO(good, link);

        return new ResponseEntity<>(goodDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/Good")
    public ResponseEntity<Set<GoodDTO>> getAllGoods() throws NoSuchGoodException, NoSuchShopperException {
        List<Good> allGoods = lecturerService.getAllGoods();
        Link link = linkTo(methodOn(GoodController.class).getAllGoods()).withSelfRel();

        Set<GoodDTO> goodDTOS = new HashSet<>();
        for (Good entity : allGoods) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            GoodDTO dto = new GoodDTO(entity, selfLink);
            goodDTOS.add(dto);
        }

        return new ResponseEntity<>(goodDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/api/Good")
    public ResponseEntity<GoodDTO> addGood(@RequestBody Good newGood) throws NoSuchGoodException, NoSuchShopperException {
        lecturerService.createGood(newGood);
        Link link = linkTo(methodOn(GoodController.class).getGood(newGood.getId())).withSelfRel();

        GoodDTO goodDTO = new GoodDTO(newGood, link);

        return new ResponseEntity<>(goodDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/Good{good_id}")
    public ResponseEntity<GoodDTO> updateGood(@RequestBody Good uGood, @PathVariable Long good_id) throws NoSuchGoodException, NoSuchShopperException {
        lecturerService.updateGood(uGood, good_id);
        Good good = lecturerService.getGood(good_id);
        Link link = linkTo(methodOn(GoodController.class).getGood(good_id)).withSelfRel();

        GoodDTO goodDTO = new GoodDTO(good, link);

        return new ResponseEntity<>(goodDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/Good/{good_id}")
    public  ResponseEntity deleteGood(@PathVariable Long good_id) throws ExistsShopperForGoodException, NoSuchGoodException {
        lecturerService.deleteGood(good_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
