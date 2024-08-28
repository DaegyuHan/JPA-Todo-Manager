package com.sparta.springtodoprogram.repository;

import com.sparta.springtodoprogram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 해당 유저 조회
    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));
    }

    Optional<User> findByUserName(String userName);

    Optional<User> findByuserEmail(String userEmail);
}
