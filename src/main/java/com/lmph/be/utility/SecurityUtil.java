package com.lmph.be.utility;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Utility class for Security
 *
 * @author Jeffrey John Javison
 * @since 07-Dec-2023
 */
public class SecurityUtil {


    /**
     * @author Ryan Valmoria
     * @return
     */
    public static boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    /**
     * Get the name of the logged in/authenticated user
     *
     * @author Jeffrey John Javison
     * @since 11-Dec-2023
     * @return the name of the logged in user
     */
    public static String getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
