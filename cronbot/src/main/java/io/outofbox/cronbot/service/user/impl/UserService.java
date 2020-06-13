package io.outofbox.cronbot.service.user.impl;

import com.mongodb.MongoException;
import io.outofbox.cronbot.error.NotFoundException;
import io.outofbox.cronbot.error.OperationFailureException;
import io.outofbox.cronbot.model.User;
import io.outofbox.cronbot.repository.user.UserRepository;
import io.outofbox.cronbot.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findAccountByUsername(String username) throws NotFoundException {
        Optional<User> account = userRepository.findByUsername(username);
        if (!account.isPresent()) {
            throw new NotFoundException("Account not exists");
        }
        User userObj = account.get();
        // Hide password
        userObj.setPassword("");
        return userObj;
    }

    @Override
    public User saveOrUpdate(User user) throws OperationFailureException {
        try {
            user = userRepository.save(user);
        } catch (MongoException ex) {
            throw new OperationFailureException("Failed to save account", ex);
        } finally {
            return user;
        }
    }
}
