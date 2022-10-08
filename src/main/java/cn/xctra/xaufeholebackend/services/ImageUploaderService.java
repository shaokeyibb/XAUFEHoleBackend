package cn.xctra.xaufeholebackend.services;

import cn.xctra.xaufeholebackend.database.dto.SMMSUploadImageResponseDto;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class ImageUploaderService {

    private final OkHttpClient client;
    private final Gson gson;
    @Value("${xaufe-hole.image-uploader.token}")
    private String token;

    @Autowired
    public ImageUploaderService(OkHttpClient client, Gson gson) {
        this.client = client;
        this.gson = gson;
    }

    public SMMSUploadImageResponseDto upload(MultipartFile file) throws IOException {
        try (Response response = client.newCall(
                new Request.Builder()
                        .url("https://smms.app/api/v2/upload")
                        .header("Authorization", token)
                        .header("Content-Type", "multipart/form-data")
                        .header("User-Agent", "Mozilla/5.0 (Linux; Android 11; Pixel 5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.91 Mobile Safari/537.36 Edg/106.0.0.0")
                        .post(
                                new MultipartBody.Builder()
                                        .setType(MultipartBody.FORM)
                                        .addFormDataPart("smfile", file.getOriginalFilename(), RequestBody.create(file.getBytes(), MediaType.parse(Objects.requireNonNull(file.getContentType()))))
                                        .addFormDataPart("format", "json")
                                        .build()
                        )
                        .build()
        ).execute()) {
            if (!response.isSuccessful()) return null;
            ResponseBody body = response.body();
            if (body == null) return null;
            return gson.fromJson(body.charStream(), SMMSUploadImageResponseDto.class);
        }
    }
}
