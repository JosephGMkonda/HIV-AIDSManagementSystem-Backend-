package com.codebuddy.serviceImpl;

import com.codebuddy.POJO.User;
import com.codebuddy.constents.SystemsConstants;
import com.codebuddy.dao.UserDao;
import com.codebuddy.service.UserService;
import com.codebuddy.utils.SystemsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;


    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signUp {}", requestMap);
        try {

            if (validateSignupMap(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));
                    return SystemsUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);

                } else {
                    return SystemsUtils.getResponseEntity("Email already exists.", HttpStatus.BAD_REQUEST);
                }


            } else {
                return SystemsUtils.getResponseEntity(SystemsConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return SystemsUtils.getResponseEntity(SystemsConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private boolean validateSignupMap(Map<String, String> requestMap){
        if(requestMap.containsKey("name") &&
                requestMap.containsKey("phoneNumber")
                && requestMap.containsKey("email")
                && requestMap.containsKey("password")){
            return true;
        }
        else {
            return false;
        }

    }

    private User getUserFromMap(Map<String, String> requestMap){
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setPhoneNumber(requestMap.get("phoneNumber"));
        user.setPassword(requestMap.get("password"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }
}
