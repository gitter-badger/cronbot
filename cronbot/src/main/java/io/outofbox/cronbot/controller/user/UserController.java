package io.outofbox.cronbot.controller.user;

import io.outofbox.cronbot.error.NotFoundException;
import io.outofbox.cronbot.model.User;
import io.outofbox.cronbot.security.config.TokenProvider;
import io.outofbox.cronbot.service.user.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.FailedLoginException;

/**
 * @author ahelmy
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private TokenProvider tokenProvider;
    private UserService userService;

    @Autowired
    public UserController(TokenProvider tokenProvider, UserService userService){
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getMyself(@RequestHeader(TokenProvider.HEADER_STRING) String authorization) throws FailedLoginException, NotFoundException {
        String username = tokenProvider.getUsernameFromToken(authorization);

        User user = userService.findAccountByUsername(username);

        return  ResponseEntity.ok(user);
    }
}
