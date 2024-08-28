package com.sparta.springtodoprogram.entity;

import com.sparta.springtodoprogram.dto.UserDto.ToTalUserReqDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "user")
public class User extends Timestamed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;                // 유저 ID

    private String userName;        // 유저 이름

    @Column(unique = true)
    private String userEmail;       // 유저 Email

    // Management table 과 1:N 관계
    @OneToMany(mappedBy = "user")
    private List<Management> managementList = new ArrayList<>();

    public User(ToTalUserReqDto requestDto) {
        this.userName = requestDto.getUserName();
        this.userEmail = requestDto.getUserEmail();
    }

    public void update(ToTalUserReqDto requestDto) {
        this.userName = requestDto.getUserName();
        this.userEmail = requestDto.getUserEmail();
    }
}
