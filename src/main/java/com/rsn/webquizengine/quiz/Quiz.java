package com.rsn.webquizengine.quiz;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rsn.webquizengine.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @NotBlank(message = "Title is empty")
    private String title;

    @NotBlank(message = "Text is empty")
    private String text;

    @NotNull(message = "Options are null")
    @Size(min = 2, message = "Options are less than 2")
    private String[] options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer[] answer;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Quiz() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public Integer[] getAnswer() {
        return answer;
    }

    public void setAnswer(Integer[] answer) {
        this.answer = answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return id == quiz.id &&
                Objects.equals(title, quiz.title) &&
                Objects.equals(text, quiz.text) &&
                Arrays.equals(options, quiz.options) &&
                Arrays.equals(answer, quiz.answer) &&
                Objects.equals(user, quiz.user);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, title, text, user);
        result = 31 * result + Arrays.hashCode(options);
        result = 31 * result + Arrays.hashCode(answer);
        return result;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + Arrays.toString(options) +
                ", answer=" + Arrays.toString(answer) +
                ", owner=" + user +
                '}';
    }
}
