package com.sparta.springtodoprogram.domain.user.entity;

import com.sparta.springtodoprogram.domain.user.dto.request.ToTalUserReqDto;
import com.sparta.springtodoprogram.config.management.Management;
import com.sparta.springtodoprogram.config.Timestamed;
import com.sparta.springtodoprogram.config.UserRole;
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


    private String userName;        // 유저 이름

    @Column(nullable = false, unique = true)
    private String userEmail;       // 유저 Email

    @Enumerated(EnumType.STRING)
    private UserRole userRole;            // 사용자 권한

    @Column(nullable = false)
    private String password;        // 유저 password


    // Management table 과 1:N 관계
    @OneToMany(mappedBy = "user")
    private List<Management> managementList = new ArrayList<>();

    public User(String userName, String userEmail, String password, UserRole userRole) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
        this.userRole = userRole;
    }



    public static User addUser(String userName, String userEmail, String password, UserRole userRole) {
        return new User(userName, userEmail, password, userRole);
    }

    public void update(ToTalUserReqDto requestDto) {
        this.userName = requestDto.getUserName();
        this.userEmail = requestDto.getUserEmail();
    }
}
