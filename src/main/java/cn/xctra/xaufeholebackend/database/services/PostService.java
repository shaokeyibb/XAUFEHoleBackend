package cn.xctra.xaufeholebackend.database.services;

import cn.hutool.core.collection.CollectionUtil;
import cn.xctra.xaufeholebackend.database.dto.PostPreviewDto;
import cn.xctra.xaufeholebackend.database.dto.PostViewDto;
import cn.xctra.xaufeholebackend.database.entities.CommentEntity;
import cn.xctra.xaufeholebackend.database.entities.PostEntity;
import cn.xctra.xaufeholebackend.database.entities.UserEntity;
import cn.xctra.xaufeholebackend.database.repositories.PostRepository;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostPreviewDto> list(int page, int size) {
        Page<PostEntity> result = postRepository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));
        return result.map(this::buildPostPreview).toList();
    }

    @Nullable
    public PostViewDto view(long id) {
        return postRepository.findById(id).map(this::buildPostView).orElse(null);
    }

    public PostEntity save(PostEntity post) {
        return postRepository.save(post);
    }

    @Nullable
    public PostEntity findPostById(long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Nullable
    public PostEntity addComments(long id, CommentEntity... comment) {
        PostEntity post = findPostById(id);
        if (post == null) return null;
        post.setComments(new ArrayList<>(CollectionUtil.addAll(post.getComments(), comment)));
        return postRepository.save(post);
    }

    private PostPreviewDto buildPostPreview(PostEntity post) {
        List<UserEntity> commenter = post.getComments().parallelStream().map(CommentEntity::getPoster).distinct().collect(Collectors.toList());
        return new PostPreviewDto(post.getId(),
                post.getPostTime().getTime(),
                post.getContent().substring(0, Math.min(post.getContent().length(), 200)),
                post.getComments().size(),
                post.getStarredUsers().size(),
                post.getAttributes(),
                post.getTags(),
                post.getComments().parallelStream()
                        .limit(2)
                        .map(it -> new PostPreviewDto.CommentPreview(it.getSubId(), commenter.indexOf(it.getPoster()), it.getPostTime().getTime(), it.getContent()))
                        .collect(Collectors.toList()));
    }

    private PostViewDto buildPostView(PostEntity post) {
        List<UserEntity> commenter = post.getComments().parallelStream().map(CommentEntity::getPoster).distinct().collect(Collectors.toList());
        List<PostViewDto.PostsBean> posts = new ArrayList<>();
        posts.add(new PostViewDto.PostsBean(post.getPostTime().getTime(), post.getContent(), post.getAttributes(), post.getTags()));
        posts.addAll(post.getComments().parallelStream()
                .map(it -> new PostViewDto.PostsBean(it.getSubId(), commenter.indexOf(it.getPoster()), it.getPostTime().getTime(), it.getContent(), Collections.emptyList(), Collections.emptyList()))
                .collect(Collectors.toList()));
        return new PostViewDto(post.getId(), posts);
    }
}
