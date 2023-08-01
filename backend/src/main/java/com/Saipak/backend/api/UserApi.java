package com.Saipak.backend.api;

import com.Saipak.backend.business.UserBusiness;
import com.Saipak.backend.exception.BaseException;
import com.Saipak.backend.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserApi {

    private final UserBusiness userBusiness;

    public UserApi(UserBusiness userBusiness) {
        this.userBusiness = userBusiness;
    }

    @PostMapping("/login")
    public ResponseEntity<MLoginResponse> login(@RequestBody MLoginRequest request) throws BaseException {
        MLoginResponse response = userBusiness.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<MRegisterResponse> login(@RequestBody MRegisterRequest request) throws BaseException {
        MRegisterResponse response = userBusiness.register(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public String test() {
        return "This is test API";
    }

    @GetMapping("/getscore")
    public ResponseEntity<ScoreResponse> getScore() throws BaseException {
        ScoreResponse response = userBusiness.getScore();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/postscore")
    public ResponseEntity<Void> postScore(@RequestBody ScoreRequest request) throws BaseException {
        userBusiness.updateScore(request.getScore());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/showhint")
    public ResponseEntity<HintResponse> showHint() throws BaseException {
        HintResponse response = userBusiness.showHint();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/buyhint")
    public ResponseEntity<Object> buyHint(@RequestBody ScoreRequest request) throws BaseException {
        userBusiness.buyHint();
        userBusiness.updateScore(request.getScore());
        return ResponseEntity.ok().build();
    }
}
