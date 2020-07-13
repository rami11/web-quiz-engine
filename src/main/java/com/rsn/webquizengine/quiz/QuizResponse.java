package com.rsn.webquizengine.quiz;

public class QuizResponse {
    private final boolean success;
    private final String feedback;

    public QuizResponse(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public QuizResponse(boolean success) {
        this(success, success ? "Congratulations, you're right!" : "Wrong answer! Please, try again.");
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }
}
