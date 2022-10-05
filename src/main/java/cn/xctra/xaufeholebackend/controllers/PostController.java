package cn.xctra.xaufeholebackend.controllers;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import cn.xctra.xaufeholebackend.database.dto.CreateNewPostDto;
import cn.xctra.xaufeholebackend.database.dto.PostPreviewDto;
import cn.xctra.xaufeholebackend.database.dto.PostViewDto;
import cn.xctra.xaufeholebackend.database.dto.ReplyPostDto;
import cn.xctra.xaufeholebackend.database.entities.CommentEntity;
import cn.xctra.xaufeholebackend.database.entities.PostEntity;
import cn.xctra.xaufeholebackend.database.services.PostService;
import cn.xctra.xaufeholebackend.database.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post/")
public class PostController {

    private final PostService postService;

    private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("list")
    public List<PostPreviewDto> list(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                     @RequestParam(value = "size", required = false, defaultValue = "20") int size) {
        return postService.list(page, size);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<PostViewDto> id(@PathVariable("id") long id) {
        PostViewDto view = postService.view(id);
        if (view == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(view);
    }

    @SaCheckLogin
    @PostMapping("create")
    public void create(@RequestBody CreateNewPostDto create) {
        if (create.getAttributes() != null && !create.getAttributes().isEmpty()) {
            StpUtil.checkRole("admin");
        }
        postService.save(new PostEntity(userService.getUser(StpUtil.getLoginIdAsLong()), DateTime.now(), create.getContent(), create.getAttributes(), create.getTags()));
    }

    @SaCheckLogin
    @PutMapping("reply")
    public void reply(@RequestBody ReplyPostDto reply) {
        PostEntity postEntity = postService.addComments(reply.getId(), new CommentEntity(userService.getUser(StpUtil.getLoginIdAsLong()), postService.findPostById(reply.getId()), DateTime.now(), reply.getContent()));
    }

}
