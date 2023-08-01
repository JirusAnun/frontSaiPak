package com.Saipak.backend.util;

import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.swing.text.html.Option;
import java.util.Objects;
import java.util.Optional;

public class SecurityUtil {


    public static Optional<String> getCurrentUserId() {
        SecurityContext context = SecurityContextHolder.getContext();
        if( context == null) {
            return Optional.empty();
        }

        Authentication authentication = context.getAuthentication();
        if( authentication == null) {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();
        if( principal == null) {
            return Optional.empty();
        }

        String userId = principal.toString();
        return Optional.of(userId);
    }

}
