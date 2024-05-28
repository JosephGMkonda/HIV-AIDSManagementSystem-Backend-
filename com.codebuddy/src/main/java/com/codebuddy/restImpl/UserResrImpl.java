package com.codebuddy.restImpl;

import com.codebuddy.constents.SystemsConstants;
import com.codebuddy.rest.UserRest;
import com.codebuddy.service.UserService;
import com.codebuddy.utils.SystemsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class UserResrImpl implements UserRest {
    @Autowired
    UserService userService;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try{
            return userService.signUp(requestMap);



        }catch(Exception ex){
            ex.printStackTrace();
        }
        return SystemsUtils.getResponseEntity(SystemsConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);


    }
}
