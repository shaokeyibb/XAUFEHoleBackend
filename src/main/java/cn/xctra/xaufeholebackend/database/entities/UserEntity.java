package cn.xctra.xaufeholebackend.database.entities;

import lombok.*;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {

    public UserEntity(long id) {
        this(id, null, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    public UserEntity(long id, String email, String password) {
        this(id, email, password, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    /**
     * 用户 ID，可能为用户学号，也可能为邮箱地址哈希值
     */
    @Id
    @Column(nullable = false)
    private long id;

    private String email;

    private String password;

    @OneToMany(mappedBy = "poster")
    private List<PostEntity> createdPosts;

    @OneToMany(mappedBy = "poster")
    private List<CommentEntity> createdComments;

    @ManyToMany
    @JoinTable(name = "STARRED_POSTS")
    private List<PostEntity> starredPosts;


}
