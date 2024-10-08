package com.sparta.springtodoprogram.config.management;

import com.sparta.springtodoprogram.domain.todo.entity.Todo;
import com.sparta.springtodoprogram.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "management")
public class Management {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Management(User user, Todo todo) {
        this.user = user;
        this.todo = todo;
    }
}
