package cn.xctra.xaufeholebackend.services;

import cn.xctra.xaufeholebackend.database.dto.BilibiliVideoDetailDto;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class BilibiliVideoService {
    private final OkHttpClient okHttpClient;

    private final Gson gson;

    @Autowired
    public BilibiliVideoService(OkHttpClient okHttpClient, Gson gson) {
        this.okHttpClient = okHttpClient;
        this.gson = gson;
    }

    public BilibiliVideoDetailDto getDetail(String bvid) throws IOException {
        try (Response response = okHttpClient.newCall(
                new Request.Builder()
                        .get()
                        .url("https://api.bilibili.com/x/web-interface/view?bvid=" + bvid)
                        .build()
        ).execute()) {
            return gson.fromJson(Objects.requireNonNull(response.body()).charStream(), BilibiliVideoDetailDto.class);
        }
    }

    public String uniqueKeyToBvid(String uniqueKey) throws IOException {
        try (Response response = okHttpClient.newCall(
                new Request.Builder()
                        .get()
                        .url("https://b23.tv/" + uniqueKey)
                        .build()
        ).execute()) {
            return response.request().url().pathSegments().get(1);
        }
    }

}
