package cn.xctra.xaufeholebackend.controllers;

import cn.xctra.xaufeholebackend.database.dto.BilibiliVideoDetailDto;
import cn.xctra.xaufeholebackend.services.BilibiliVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/bilibili/")
public class BilibiliVideoController {

    private final BilibiliVideoService bilibiliVideoService;

    @Autowired
    public BilibiliVideoController(BilibiliVideoService bilibiliVideoService) {
        this.bilibiliVideoService = bilibiliVideoService;
    }

    @GetMapping("view")
    public BilibiliVideoDetailDto view(@RequestParam String bvid) throws IOException {
        return bilibiliVideoService.getDetail(bvid);
    }

    @GetMapping("uniqueKeyToBvid")
    public String uniqueKeyToBvid(@RequestParam String unique_k) throws IOException {
        return bilibiliVideoService.uniqueKeyToBvid(unique_k);
    }

}
