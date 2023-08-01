package com.Saipak.backend.business;

import com.Saipak.backend.entity.Hint;
import com.Saipak.backend.entity.User;
import com.Saipak.backend.exception.BaseException;
import com.Saipak.backend.exception.UserException;
import com.Saipak.backend.model.*;
import com.Saipak.backend.service.TokenService;
import com.Saipak.backend.service.UserService;
import com.Saipak.backend.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserBusiness {

    private final UserService userService;

    private final TokenService tokenService;

    public UserBusiness(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    public MLoginResponse login(MLoginRequest request) throws BaseException {

        Optional<User> opt = userService.findByUsername(request.getUsername());
        if(opt.isEmpty()) {
            throw UserException.loginFail();
        }

        User user = opt.get();
        if(!userService.matchedPassword(request.getPassword(), user.getPassword())){
            throw UserException.loginFail();
        }

        MLoginResponse response = new MLoginResponse();
        response.setToken(tokenService.tokenize(user));
        response.setScore(user.getScore());
        return response;
    }

    public MRegisterResponse register(MRegisterRequest request) throws BaseException {
        User user = userService.create(request.getUsername(), request.getPassword(), request.getConfirmpassword());
        MRegisterResponse response = new MRegisterResponse();
        response.setSuccess(true);
        return response;
    }

    public ScoreResponse getScore() throws BaseException {
        Optional<String> opt = SecurityUtil.getCurrentUserId();
        if(opt.isEmpty()) {
            throw UserException.unauthorized();
        }

        String userId = opt.get();
        Optional<User> optUser = userService.findById(userId);
        if(optUser.isEmpty()) {
            throw UserException.unauthorized();
        }

        User user = optUser.get();
        ScoreResponse response = new ScoreResponse();
        response.setScore(user.getScore());
        return response;
    }

    public void updateScore(int newScore) throws BaseException {
        Optional<String> opt = SecurityUtil.getCurrentUserId();
        if(opt.isEmpty()) {
            throw UserException.unauthorized();
        }

        String userId = opt.get();
        Optional<User> optUser = userService.findById(userId);
        if(optUser.isEmpty()) {
            throw UserException.unauthorized();
        }

        User user = optUser.get();
        user.setScore(newScore);
        userService.saveUser(user);
    }

    public HintResponse showHint() throws BaseException {
        HintResponse response = userService.showHint();
        return response;
    }

    public void buyHint() throws BaseException {
        userService.buyHint();
    }

}
