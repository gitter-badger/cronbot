package io.outofbox.cronbot.service.user;

import io.outofbox.cronbot.error.NotFoundException;
import io.outofbox.cronbot.error.OperationFailureException;
import io.outofbox.cronbot.model.User;

public interface IUserService {
    User findAccountByUsername(String username) throws NotFoundException;

    User saveOrUpdate(User user) throws OperationFailureException;
}
