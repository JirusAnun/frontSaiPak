package com.Saipak.backend.exception;

import com.Saipak.backend.entity.User;

public class UserException extends BaseException{

    public UserException(String code){
        super("user." + code);
    }

    //CREATE
    public static UserException createUsernameNull() {
        return new UserException("create.username.null");
    }
    public static UserException createUPasswordNull() {
        return new UserException("create.password.null");
    }

    public static UserException createUnmatchedPassword() {
        return new UserException("create.unmatched.password");
    }

    public static UserException createUsernnameDuplicated() {
        return new UserException("create.dulplicated.user");
    }

    //LOGIN
    public static UserException loginFail() {
        return new UserException("login.fail");
    }

    //check
    public static UserException unauthorized() {
        return new UserException("unauthorized");
    }
}
