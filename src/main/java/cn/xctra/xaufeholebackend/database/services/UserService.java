package cn.xctra.xaufeholebackend.database.services;

import cn.hutool.crypto.digest.BCrypt;
import cn.xctra.xaufeholebackend.configurations.RedisCacheTool;
import cn.xctra.xaufeholebackend.database.entities.UserEntity;
import cn.xctra.xaufeholebackend.database.repositories.UserRepository;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    public UserEntity getUser(long uid) {
        return userRepository.findById(uid).orElse(null);
    }

    public UserEntity saveUser(long uid) {
        return userRepository.save(new UserEntity(uid));
    }

    public UserEntity saveUser(String email, String password) {
        return userRepository.save(new UserEntity(email.hashCode(), email, BCrypt.hashpw(password)));
    }

    public boolean hasUser(long uid) {
        return userRepository.existsById(uid);
    }

}
