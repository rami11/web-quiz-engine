package com.rsn.webquizengine.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsn.webquizengine.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "completed_quiz")
public class CompletedQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty(value = "completed_id", access = JsonProperty.Access.WRITE_ONLY)
    private int id;

    @Column(name = "quiz_id")
    @JsonProperty("id")
    private int quizId;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    public CompletedQuiz() {
    }

    public CompletedQuiz(int quizId, User user) {
        this.quizId = quizId;
        this.completedAt = LocalDateTime.now();
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
