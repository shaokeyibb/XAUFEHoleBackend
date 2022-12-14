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

    /**
     * 用户 ID，可能为用户学号哈希值，也可能为邮箱地址哈希值
     */
    @Id
    @Column(nullable = false)
    private long id;

    private long studentID;

    private String email;

    private String password;

    @OneToMany(mappedBy = "poster")
    private List<PostEntity> createdPosts;

    @OneToMany(mappedBy = "poster")
    private List<CommentEntity> createdComments;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "starredUsers")
    private List<PostEntity> starredPosts;

    @ElementCollection
    private List<String> permissions;
    @ElementCollection
    private List<String> roles;


    public UserEntity(long id) {
        this(String.valueOf(id).hashCode(), id, null, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.singletonList("user"));
    }

    public UserEntity(String email, String password) {
        this(email.hashCode(), -1, email, password, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.singletonList("user"));
    }

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
