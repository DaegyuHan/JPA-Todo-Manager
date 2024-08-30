package com.sparta.springtodoprogram.domain.comment.entity;

import com.sparta.springtodoprogram.domain.comment.dto.request.RegistCommentReqDto;
import com.sparta.springtodoprogram.domain.comment.dto.request.UpdateCommentReqDto;
import com.sparta.springtodoprogram.config.Timestamed;
import com.sparta.springtodoprogram.domain.todo.entity.Todo;
import com.sparta.springtodoprogram.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "comment")

public class Comment extends Timestamed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;                    // 댓글 ID

    private String commentContent;      // 댓글 내용
    private String userName;            // 댓글 작성자 유저 Name


    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    public Comment(RegistCommentReqDto requestDto, String userName) {
        this.commentContent = requestDto.getCommentContent();
        this.userName = userName;
    }

    public static Comment addComment(RegistCommentReqDto requestDto, String userName) {
        return new Comment(requestDto, userName);
    }


    public void update(UpdateCommentReqDto requestDto, String userName) {
        this.commentContent = requestDto.getCommentContent();
        this.userName = userName;
    }
}
