package com.sparta.springtodoprogram.entity;

import com.sparta.springtodoprogram.dto.userDto.ToTalUserReqDto;
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
@Table(name = "user")
public class User extends Timestamed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;                // 유저 ID

    @Column(nullable = false, unique = true)
    private String userName;        // 유저 이름

    @Column(nullable = false, unique = true)
    private String userEmail;       // 유저 Email

    private String userRole;            // 사용자 권한

    @Column(nullable = false)
    private String password;        // 유저 password


    // Management table 과 1:N 관계
    @OneToMany(mappedBy = "user")
    private List<Management> managementList = new ArrayList<>();

    public User(String userName, String userEmail, String password, UserRoleEnum userRoleEnum) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
        this.userRole = String.valueOf(userRoleEnum);
    }



    public static User addUser(String userName, String userEmail, String password, UserRoleEnum userRoleEnum) {
        return new User(userName, userEmail, password, userRoleEnum);
    }

    public void update(ToTalUserReqDto requestDto) {
        this.userName = requestDto.getUserName();
        this.userEmail = requestDto.getUserEmail();
    }
}
