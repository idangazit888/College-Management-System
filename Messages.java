package Collegeproject;

public enum Messages {
    INVALID_ID("Invalid ID, type again."),
    INVALID_NAME("Invalid name, type again."),
    INVALID_DEGREE("Invalid degree, type again (BA, MA, DR, PROF)."),
    INVALID_SALARY("Invalid salary, type again."),
    INVALID_DEPARTMENT("Invalid department, type again."),
    INVALID_COMMITTEE("Invalid committee, type again."),
    INVALID_INPUT("Invalid input, try again.");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
