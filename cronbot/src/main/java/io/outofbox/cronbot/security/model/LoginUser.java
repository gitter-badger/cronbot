package io.outofbox.cronbot.security.model;

import lombok.Data;

/**
 * @author ahelmy
 */
@Data
public class LoginUser {
    private Long id;
    private String username;
    private String password;
}
