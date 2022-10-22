package com.example.dream.repository;

import com.example.dream.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<com.example.dream.entity.Member> findByUsername(String username);
}
