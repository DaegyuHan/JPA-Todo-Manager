package com.sparta.springtodoprogram.entity;

import com.sparta.springtodoprogram.dto.CommentDto.RegistCommentReqDto;
import com.sparta.springtodoprogram.dto.CommentDto.UpdateCommentReqDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

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

    private String userName;            // 작성 유저명



    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    public Comment(RegistCommentReqDto requestDto) {
        this.commentContent = requestDto.getCommentContent();
        this.userName = requestDto.getUserName();
    }

    public void update(UpdateCommentReqDto requestDto) {
        this.commentContent = requestDto.getCommentContent();
        this.userName = requestDto.getUserName();
    }
}
