package com.Saipak.backend.service;

import com.Saipak.backend.entity.Hint;
import com.Saipak.backend.entity.Roles;
import com.Saipak.backend.entity.User;
import com.Saipak.backend.exception.BaseException;
import com.Saipak.backend.exception.UserException;
import com.Saipak.backend.model.HintResponse;
import com.Saipak.backend.repository.RoleRepository;
import com.Saipak.backend.repository.UserRepository;
import com.Saipak.backend.util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Optional<User> findById(String userId) {
        return repository.findById(userId);
    }

    public boolean matchedPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public User create(String username, String password, String confirmPassword) throws BaseException {

        //validate
        if(Objects.isNull(username)) {
            throw UserException.createUsernameNull();
        }
        if(Objects.isNull(password)) {
            throw UserException.createUPasswordNull();
        }
        if(!password.equals(confirmPassword)) {
            throw UserException.createUnmatchedPassword();
        }

        //verify
        if(repository.existsByUsername(username)){
            throw UserException.createUsernnameDuplicated();
        }

        //save
        User entity = new User();
        Optional<Roles> roleOpt = roleRepository.findByName("USER");
        Roles role;
        if(roleOpt.isEmpty()){
            role = new Roles("USER");
        }else {
            role = roleOpt.get();
        }
        entity.setUsername(username);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setRole(role);
        entity.setScore(0);

        return repository.save(entity);

    }

    public User saveUser(User user) {
        return repository.save(user);
    }

    public HintResponse showHint() throws BaseException {
        Optional<String> opt = SecurityUtil.getCurrentUserId();
        if(opt.isEmpty()) {
            throw UserException.unauthorized();
        }

        String userId = opt.get();
        Optional<User> optUser = findById(userId);
        if(optUser.isEmpty()) {
            throw UserException.unauthorized();
        }

        User user = optUser.get();
        HintResponse response = new HintResponse();

        if(user.getHint_bought()==0) {

        }
        if(user.getHint_bought()==1) {
            response.setHint1(user.getHint().getHint_1());
        }
        if(user.getHint_bought()==2) {
            response.setHint1(user.getHint().getHint_1());
            response.setHint2(user.getHint().getHint_2());
        }
        if(user.getHint_bought()==3) {
            response.setHint1(user.getHint().getHint_1());
            response.setHint2(user.getHint().getHint_2());
            response.setHint3(user.getHint().getHint_3());
        }
        if(user.getHint_bought()==4) {
            response.setHint1(user.getHint().getHint_1());
            response.setHint2(user.getHint().getHint_2());
            response.setHint3(user.getHint().getHint_3());
            response.setHint4(user.getHint().getHint_4());
        }
        if(user.getHint_bought()>=5) {
            response.setHint1(user.getHint().getHint_1());
            response.setHint2(user.getHint().getHint_2());
            response.setHint3(user.getHint().getHint_3());
            response.setHint4(user.getHint().getHint_4());
            response.setHint5(user.getHint().getHint_5());
        }
        return response;
    }
    public void buyHint() throws BaseException {
        Optional<String> opt = SecurityUtil.getCurrentUserId();
        if(opt.isEmpty()) {
            throw UserException.unauthorized();
        }

        String userId = opt.get();
        Optional<User> optUser = findById(userId);
        if(optUser.isEmpty()) {
            throw UserException.unauthorized();
        }

        User user = optUser.get();
        HintResponse response = new HintResponse();

        int hint_bought = user.getHint_bought();
        user.setHint_bought(hint_bought+1);
        repository.save(user);
    }
}
