package com.roi.controller;

import com.roi.DTO.impl.MessageDTO;
import com.roi.exceptions.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchPasswordOfShopperException.class)
    ResponseEntity<MessageDTO> handleNoSushCityException(){
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such city not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchGoodException.class)
    ResponseEntity<MessageDTO> handleNoSushPersonException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such person not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchShopperException.class)
    ResponseEntity<MessageDTO> handleNoSushBookException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such book not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistsShopperForPasswordOfShopperException.class)
    ResponseEntity<MessageDTO> handleExistsPersonsForCityException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are persons for this city"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsShopperForProjectException.class)
    ResponseEntity<MessageDTO> handleExistsBooksForPersonException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are books for this person"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsGoodForShopperException.class)
    ResponseEntity<MessageDTO> handleExistsPersonsForBookException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are persons for this book"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AlreadyExistsShopperInGoodException.class)
    ResponseEntity<MessageDTO> handleAlreadyExistsBookInPersonExceptionException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Add imposible. The person already contain this book"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ShopperAbsentException.class)
    ResponseEntity<MessageDTO> handleBookAbsentException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Now this book is absent"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GoodHasNotShopperException.class)
    ResponseEntity<MessageDTO> handlePersonHasNotBookException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("The person hasn't this book"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchLogException.class)
    ResponseEntity<MessageDTO> handleNoSuchLogException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such log not found"), HttpStatus.NOT_FOUND);
    }

}
