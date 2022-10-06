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

    public CommentEntity(UserEntity poster, PostEntity post, Date postTime, String content) {
        this(0, poster, post, postTime, content);
    }

    @Id
    @Column(nullable = false)
    @SequenceGenerator(name = "comment_id_seq", sequenceName = "comment_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_seq")
    private long id;

    @ManyToOne
    private UserEntity poster;

    @ManyToOne
    private PostEntity post;

    @Column(nullable = false)
    private Date postTime;

    @Column(nullable = false)
    private String content;

}
