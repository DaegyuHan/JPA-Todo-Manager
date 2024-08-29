package com.sparta.springtodoprogram.entity;

import com.sparta.springtodoprogram.dto.todoDto.RegistTodoReqDto;
import com.sparta.springtodoprogram.dto.todoDto.UpdateTodoReqDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "todo")
public class Todo extends Timestamed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;                // 할일 ID

    // @Column(nullable = false, unique = true)
    private Long userId;            // 작성 유저ID

    private String todoTitle;           // 할일 제목

    private String todoContent;         // 할일 내용

    private String weather;



    // Comment table 과 1:N 관계
    @OneToMany(mappedBy = "todo", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();

    // Management table 과 1:N 관계
    @OneToMany(mappedBy = "todo",fetch = FetchType.LAZY)
    private List<Management> managementList = new ArrayList<>();


    // 해당 일정의 댓글 개수
    @Transient
    public int getCommentCount() {
        return commentList.size();
    }

    public Todo(RegistTodoReqDto requestDto,  String todayWeateher) {
        this.userId = requestDto.getUserId();
        this.todoTitle = requestDto.getTodoTitle();
        this.todoContent = requestDto.getTodoContent();
        this.weather = todayWeateher;
    }

    public void update(UpdateTodoReqDto requestDto) {
        this.userId = requestDto.getUserId();
        this.todoTitle = requestDto.getTodoTitle();
        this.todoContent = requestDto.getTodoContent();

    }

    public void addComment(Comment comment) {
        commentList.add(comment);
        comment.setTodo(this);
    }

    public void addManagement(Management management) {
        managementList.add(management);
        management.setTodo(this);
    }
}
