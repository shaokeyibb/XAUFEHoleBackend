package cn.xctra.xaufeholebackend.database.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "posts")
public class PostEntity {
    public PostEntity(UserEntity poster, List<CommentEntity> comments, List<UserEntity> starredUsers, Date postTime, String content, List<String> attributes, List<String> tags) {
        this(0, poster, comments, starredUsers, postTime, content, attributes, tags);
    }

    public PostEntity(UserEntity poster, Date postTime, String content, List<String> attributes, List<String> tags) {
        this(poster, Collections.emptyList(), Collections.emptyList(), postTime, content, attributes, tags);
    }

    @Id
    @Column(nullable = false)
    @SequenceGenerator(name = "post_id_seq", sequenceName = "post_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_id_seq")
    private long id;

    @ManyToOne
    @Column(nullable = false)
    private UserEntity poster;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<CommentEntity> comments;

    @ManyToMany(mappedBy = "starredPosts")
    private List<UserEntity> starredUsers;

    @Column(nullable = false)
    private Date postTime;

    @Column(nullable = false, length = 65535, columnDefinition = "Text")
    private String content;

    @ElementCollection
    private List<String> attributes;

    @ElementCollection
    private List<String> tags;

}
