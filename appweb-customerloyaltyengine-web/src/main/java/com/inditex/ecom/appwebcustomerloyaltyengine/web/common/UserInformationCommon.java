package com.inditex.ecom.appwebcustomerloyaltyengine.web.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.inditex.seguridad.sso.UserInformation;

/**
 * The Class UserInformationCommon.
 */
public class UserInformationCommon {

    /** The username. */
    private static String username = null;

    /** The roles. */
    private static String roles = null;

    /**
     * Instantiates a new user information common.
     */
    private UserInformationCommon() {

    }

    /**
     * Gets the username.
     * 
     * @return the username
     */
    public static String getUsername() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            username = ((UserInformation) authentication.getDetails()).getNombre();
        }
        return username;
    }

    /**
     * Gets the roles.
     * 
     * @return the roles
     */
    public static String getRoles() {

        roles = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {

            for (GrantedAuthority rol : authentication.getAuthorities()) {
                if (roles.length() == 0) {
                    roles = rol.toString();
                } else {
                    roles += ", " + rol.toString();
                }
            }
        }
        return roles;
    }
}
