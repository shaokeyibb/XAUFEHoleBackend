package cn.xctra.xaufeholebackend.database.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "comments")
public class CommentEntity {

    // Never use this because it's just for internal
    @Id
    @Column(nullable = false)
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private long subId;

    public CommentEntity(UserEntity poster, PostEntity post, Date postTime, String content) {
        this(0, post.getComments().size() + 1 /* start from 1 */, poster, post, postTime, content);
    }

    @ManyToOne
    private UserEntity poster;

    @ManyToOne
    private PostEntity post;

    @Column(nullable = false)
    private Date postTime;

    @Column(nullable = false)
    private String content;

}
