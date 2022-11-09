package cn.xctra.xaufeholebackend.database.services;

import cn.hutool.core.collection.CollectionUtil;
import cn.xctra.xaufeholebackend.database.dto.PostPreviewDto;
import cn.xctra.xaufeholebackend.database.dto.PostViewDto;
import cn.xctra.xaufeholebackend.database.dto.UserProfileDto;
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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostPreviewDto> getTopPosts(boolean isLogin, long userId) {
        return postRepository.findAllByAttributesContains("Top")
                .stream()
                .sorted(Comparator.comparingLong(PostEntity::getId).reversed())
                .limit(2)
                .map(it -> buildPostPreview(it, isLogin, userId))
                .collect(Collectors.toList());
    }

    public List<PostPreviewDto> list(int page, int size, boolean isLogin, long userId) {
        Page<PostEntity> result = postRepository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));
        return result.map(it -> buildPostPreview(it, isLogin, userId)).toList();
    }

    @Nullable
    public PostViewDto view(long id, boolean isLogin, long userId, boolean isAdmin) {
        return postRepository.findById(id).map(it -> buildPostView(it, isLogin, userId, isAdmin)).orElse(null);
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

    @Nullable
    public PostEntity starPost(UserEntity user, long id, boolean star) {
        PostEntity post = findPostById(id);
        if (post == null) return null;
        if (post.getStarredUsers().parallelStream().anyMatch(user::equals) && star) {
            return post;
        } else if (post.getStarredUsers().parallelStream().noneMatch(user::equals) && !star) {
            return post;
        }
        if (star) {
            post.setStarredUsers(new ArrayList<>(CollectionUtil.addAll(post.getStarredUsers(), user)));
        } else {
            post.setStarredUsers(new ArrayList<>(CollectionUtil.removeAny(post.getStarredUsers(), user)));
        }
        return postRepository.save(post);
    }

    private PostPreviewDto buildPostPreview(PostEntity post, boolean isLogin, long userId) {
        List<UserEntity> commenter = post.getComments().parallelStream().map(CommentEntity::getPoster).distinct().filter(it -> it != post.getPoster()).collect(Collectors.toList());
        return new PostPreviewDto(post.getId(),
                post.getPostTime().getTime(),
                post.getAttributes().contains("NSFW") ? "" : (post.getContent().substring(0, Math.min(post.getContent().length(), 200)) + (post.getContent().length() >= 200 ? "..." : "")),
                post.getComments().size(),
                post.getStarredUsers().size(),
                post.getAttributes(),
                post.getTags(),
                post.getComments().parallelStream()
                        .skip(Math.max(post.getComments().size() - 2, 0))
                        .map(it -> new PostPreviewDto.CommentPreview(it.getSubId(), it.getPoster() == post.getPoster() ? -1 : commenter.indexOf(it.getPoster()), it.getPostTime().getTime(), it.getContent().substring(0, Math.min(it.getContent().length(), 200)) + (it.getContent().length() >= 200 ? "..." : "")))
                        .collect(Collectors.toList()),
                isLogin && post.getStarredUsers().parallelStream().anyMatch(it -> it.getId() == userId));
    }

    private PostViewDto buildPostView(PostEntity post, boolean isLogin, long userId, boolean isAdmin) {
        List<UserEntity> commenter = post.getComments().parallelStream().map(CommentEntity::getPoster).distinct().filter(it -> it != post.getPoster()).collect(Collectors.toList());
        List<PostViewDto.PostsBean> posts = new ArrayList<>();

        posts.add(new PostViewDto.PostsBean(post.getPostTime().getTime(), post.getContent(), post.getAttributes(), post.getTags(), isAdmin ? new UserProfileDto(post.getPoster()) : null));
        posts.addAll(post.getComments().parallelStream()
                .map(it -> new PostViewDto.PostsBean(it.getSubId(), it.getPoster() == post.getPoster() ? -1 : commenter.indexOf(it.getPoster()), it.getPostTime().getTime(), it.getContent(), Collections.emptyList(), Collections.emptyList(), isAdmin ? new UserProfileDto(it.getPoster()) : null))
                .collect(Collectors.toList()));
        return new PostViewDto(post.getId(), posts, isLogin && post.getStarredUsers().parallelStream().anyMatch(it -> it.getId() == userId));
    }
}
