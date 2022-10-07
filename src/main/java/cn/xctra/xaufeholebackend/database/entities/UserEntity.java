package cn.xctra.xaufeholebackend.database.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
