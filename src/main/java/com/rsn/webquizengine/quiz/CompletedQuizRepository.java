package com.rsn.webquizengine.quiz;

import com.rsn.webquizengine.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompletedQuizRepository extends JpaRepository<CompletedQuiz, Integer> {

    @Query("SELECT x FROM CompletedQuiz x WHERE x.user = :user")
    Page<CompletedQuiz> findAllSolvedBy(@Param("user") User user, Pageable pageable);
}
