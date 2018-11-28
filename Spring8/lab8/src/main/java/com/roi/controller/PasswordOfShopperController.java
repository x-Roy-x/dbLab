package com.roi.controller;

import com.roi.DTO.impl.PasswordOfShopperDTO;
import com.roi.domain.PasswordOfShopper;

import com.roi.exceptions.*;
import com.roi.service.PasswordOfShopperService;
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
public class PasswordOfShopperController {
    @Autowired
    PasswordOfShopperService groupOfStudentService;

    @GetMapping(value = "/api/password_of_shopper")
    public ResponseEntity<Set<PasswordOfShopperDTO>> getAllPasswordOfShopper() throws NoSuchShopperException, NoSuchGoodException, NoSuchPasswordOfShopperException {
        List<PasswordOfShopper> allPasswordOfShoppers = groupOfStudentService.getAllPasswordOfShoppers();
        Link link = linkTo(methodOn(PasswordOfShopperController.class).getAllPasswordOfShopper()).withSelfRel();

        Set<PasswordOfShopperDTO> passwordOfShopperDTOS = new HashSet<>();
        for (PasswordOfShopper entity : allPasswordOfShoppers) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            PasswordOfShopperDTO dto = new PasswordOfShopperDTO(entity, selfLink);
            passwordOfShopperDTOS.add(dto);
        }

        return new ResponseEntity<>(passwordOfShopperDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/api/password_of_shopper/{password_of_shopper_id}")
    public ResponseEntity<PasswordOfShopperDTO> getPasswordOfShopper(@PathVariable Long password_of_shopper_id) throws NoSuchPasswordOfShopperException, NoSuchShopperException, NoSuchGoodException {
        PasswordOfShopper passwordOfShopper = groupOfStudentService.getPasswordOfShopper(password_of_shopper_id);
        Link link = linkTo(methodOn(PasswordOfShopperController.class).getPasswordOfShopper(password_of_shopper_id)).withSelfRel();
        System.out.println(passwordOfShopper);
        PasswordOfShopperDTO passwordOfShopperDTO = new PasswordOfShopperDTO(passwordOfShopper, link);

        return new ResponseEntity<>(passwordOfShopperDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/password_of_shopper/{password_of_shopper_id}")
    public  ResponseEntity<PasswordOfShopperDTO> addPasswordOfShopper(@RequestBody PasswordOfShopper passwordOfShopper) throws NoSuchPasswordOfShopperException, NoSuchShopperException, NoSuchGoodException {
        groupOfStudentService.createPasswordOfShopper(passwordOfShopper);
        Link link = linkTo(methodOn(PasswordOfShopperController.class).getPasswordOfShopper(passwordOfShopper.getId())).withSelfRel();

        PasswordOfShopperDTO passwordOfShopperDTO = new PasswordOfShopperDTO(passwordOfShopper, link);

        return new ResponseEntity<>(passwordOfShopperDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/password_of_shopper/{password_of_shopper_id}")
    public  ResponseEntity<PasswordOfShopperDTO> updatePasswordOfShopper(@RequestBody PasswordOfShopper passwordOfShopper, @PathVariable Long password_of_shopper_id) throws NoSuchPasswordOfShopperException, NoSuchShopperException, NoSuchGoodException {
        groupOfStudentService.updatePasswordOfShopper(passwordOfShopper, password_of_shopper_id);
        PasswordOfShopper passwordOfShopper1 = groupOfStudentService.getPasswordOfShopper(password_of_shopper_id);
        Link link = linkTo(methodOn(PasswordOfShopperController.class).getPasswordOfShopper(password_of_shopper_id)).withSelfRel();

        PasswordOfShopperDTO passwordOfShopperDTO = new PasswordOfShopperDTO(passwordOfShopper1, link);

        return new ResponseEntity<>(passwordOfShopperDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/password_of_shopper/{password_of_shopper_id}")
    public  ResponseEntity deletePasswordOfShopper(@PathVariable Long password_of_shopper_id) throws NoSuchPasswordOfShopperException, ExistsShopperForGoodException, ExistsShopperForPasswordOfShopperException {
        groupOfStudentService.updatePasswordOfShopper(password_of_shopper_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
