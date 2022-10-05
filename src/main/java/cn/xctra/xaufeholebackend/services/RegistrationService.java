package cn.xctra.xaufeholebackend.services;

import cn.hutool.core.util.RandomUtil;
import cn.xctra.xaufeholebackend.configurations.RedisCacheTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final RedisCacheTool redisCacheTool;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RegistrationService(RedisCacheTool redisCacheTool, RedisTemplate<String, Object> redisTemplate) {
        this.redisCacheTool = redisCacheTool;
        this.redisTemplate = redisTemplate;
    }

    public String requireRegistrationCode(String username) {
        String code = RandomUtil.randomString(10);
        redisCacheTool.hashOperations(redisTemplate).put("registrationCode", username, code);
        return code;
    }

    public boolean verifyRegistrationCode(String username, String code) {
        String required = (String) redisCacheTool.hashOperations(redisTemplate).get("registrationCode", username);
        boolean matches = code.equals(required);
        redisCacheTool.hashOperations(redisTemplate).delete("registrationCode", username);
        return matches;
    }

}
