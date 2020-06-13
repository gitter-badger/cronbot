package io.outofbox.cronbot.security.util;

import io.outofbox.cronbot.security.config.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.login.FailedLoginException;

/**
 *  example @PreAuthorize("@securityHelper.canAccessOwn(#token, #id)")
 * @author ahelmy
 */
@Component("securityHelper")
public class SecurityHelper {
    @Autowired
    private TokenProvider jwtTokenUtil;

    public boolean canAccessOwn(String token, Long id){
        token = token.replace(TokenProvider.TOKEN_PREFIX,"");
        try {
            return jwtTokenUtil.isSameUser(token, id);
        } catch (FailedLoginException e) {
            return false;
        }
    }
}
