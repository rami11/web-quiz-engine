package com.rsn.webquizengine.quiz;

import com.rsn.webquizengine.security.UserPrinciple;
import com.rsn.webquizengine.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class QuizController {

    private static final int PAGE_SIZE = 10;

    private final CompletedQuizRepository completedQuizRepository;
    private final QuizRepository quizRepository;
    private final HttpServletRequest httpServletRequest;

    @Autowired
    public QuizController(QuizRepository quizRepository, CompletedQuizRepository completedQuizRepository, HttpServletRequest httpServletRequest) {
        this.completedQuizRepository = completedQuizRepository;
        this.httpServletRequest = httpServletRequest;
        this.quizRepository = quizRepository;
    }

    private User getAuthenticatedUser() {
        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) httpServletRequest.getUserPrincipal();
        UserPrinciple userPrinciple = (UserPrinciple) authToken.getPrincipal();
        return userPrinciple.getUser();
    }

    @GetMapping(path = "/quizzes")
    public Page<Quiz> getAllQuizzes(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        return quizRepository.findAll(pageable);
    }

    @GetMapping(path = "/quizzes/{id}")
    public Quiz getQuizById(@PathVariable int id) {
        Optional<Quiz> quizOpt = quizRepository.findById(id);
        if (quizOpt.isEmpty()) {
            throw new QuizNotFoundException("Quiz not found for Id " + id);
        }
        return quizOpt.get();
    }

    @GetMapping(path = "/quizzes/completed")
    public Page<CompletedQuiz> getCompletedQuizzes(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "completedAt"));
        return completedQuizRepository.findAllSolvedBy(getAuthenticatedUser(), pageable);
    }

    @PostMapping(path = "/quizzes", consumes = "application/json")
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz) {
        User user = getAuthenticatedUser();
        quiz.setUser(user);

        quizRepository.save(quiz);
        return quiz;
    }

    @PostMapping(path = "/quizzes/{id}/solve", consumes = "application/json")
    public QuizResponse solveQuiz(@PathVariable int id, @RequestBody Answer answer) {
        Optional<Quiz> quizOpt = quizRepository.findById(id);
        if (quizOpt.isEmpty()) {
            throw new QuizNotFoundException();
        }
        Quiz quiz = quizOpt.get();
        Set<Integer> userAnswer = answer.getAnswer() == null ? Set.of() : Set.of(answer.getAnswer());
        Set<Integer> trueAnswer = quiz.getAnswer() == null ? Set.of() : Set.of(quiz.getAnswer());
        boolean correctAnswer = userAnswer.equals(trueAnswer);
        if (correctAnswer) {
            CompletedQuiz completedQuiz = new CompletedQuiz(quiz.getId(), getAuthenticatedUser());
            completedQuizRepository.save(completedQuiz);
            return new QuizResponse(true);
        }
        return new QuizResponse(false);
    }

    @DeleteMapping(path = "/quizzes/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable int id) {
        Optional<Quiz> quizOpt = quizRepository.findById(id);
        if (quizOpt.isEmpty()) {
            throw new QuizNotFoundException();
        }
        Quiz quiz = quizOpt.get();
        User authenticatedUser = getAuthenticatedUser();
        if (Objects.equals(quiz.getUser(), authenticatedUser)) {
            quizRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
