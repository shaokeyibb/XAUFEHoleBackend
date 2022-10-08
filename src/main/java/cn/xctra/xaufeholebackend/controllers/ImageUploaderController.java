package cn.xctra.xaufeholebackend.controllers;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.xctra.xaufeholebackend.database.dto.SMMSUploadImageResponseDto;
import cn.xctra.xaufeholebackend.services.ImageUploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image/")
public class ImageUploaderController {

    private final ImageUploaderService imageUploaderService;

    @Autowired
    public ImageUploaderController(ImageUploaderService imageUploaderService) {
        this.imageUploaderService = imageUploaderService;
    }

    @SaCheckLogin
    @PostMapping("upload")
    public ResponseEntity<SMMSUploadImageResponseDto> upload(@RequestParam MultipartFile file) throws IOException {
        if (file.getSize() > 5 * 1024 * 1024) return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).build();
        if (file.getContentType() == null) return ResponseEntity.badRequest().build();
        SMMSUploadImageResponseDto result = imageUploaderService.upload(file);
        if (result == null) return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok(result);
    }
}
