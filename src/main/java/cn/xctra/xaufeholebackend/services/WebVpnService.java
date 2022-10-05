package cn.xctra.xaufeholebackend.services;

import cn.xctra.xaufeholebackend.configurations.RedisCacheTool;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebVpnService {

    private final RedisCacheTool redisCacheTool;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public WebVpnService(RedisCacheTool redisCacheTool, RedisTemplate<String, Object> redisTemplate) {
        this.redisCacheTool = redisCacheTool;
        this.redisTemplate = redisTemplate;
    }

    @NotNull
    public String getCookie() {
        Object cookie = redisCacheTool.hashOperations(redisTemplate).get("webvpn", "cookie");
        return cookie == null ? "" : (String) cookie;
    }

    public void setCookie(String cookie) {
        redisCacheTool.hashOperations(redisTemplate).put("webvpn", "cookie", cookie);
    }
}
