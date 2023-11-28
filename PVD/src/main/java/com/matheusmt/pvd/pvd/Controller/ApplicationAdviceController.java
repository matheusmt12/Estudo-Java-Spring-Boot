package com.matheusmt.pvd.pvd.Controller;


import com.matheusmt.pvd.pvd.DTO.ResponseDTO;
import com.matheusmt.pvd.pvd.Exceptions.InvaledOperationException;
import com.matheusmt.pvd.pvd.Exceptions.NoItemException;
import com.matheusmt.pvd.pvd.Exceptions.PassWordNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationAdviceController {

    @ExceptionHandler(NoItemException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseDTO handleNoItem (NoItemException exception){
        return new ResponseDTO(exception.getMessage());
    }

    @ExceptionHandler(InvaledOperationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseDTO handleInvaled(InvaledOperationException exception){
        return new ResponseDTO(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO handleUserNotValid(MethodArgumentNotValidException exception){
        List<String> erros = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach((erro) ->{

            String menssagesErro = erro.getDefaultMessage();
            erros.add(menssagesErro);

        });
        return new ResponseDTO(erros);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseDTO handleUserNameNotFoundException(UsernameNotFoundException exception){
        return new ResponseDTO(exception.getMessage());
    }

    @ExceptionHandler(PassWordNotFound.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseDTO handlePassWordNorFound(PassWordNotFound exception){
        return new ResponseDTO(exception.getMessage());
    }

}

