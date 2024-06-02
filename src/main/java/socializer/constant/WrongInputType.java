package socializer.constant;

import lombok.Getter;

@Getter
public enum WrongInputType {
    NO_USER_WITH_EMAIL_OR_PASSWORD("there is no user with this email or password"),
    USER_IS_DISABLED("user is disabled"),
    EMAIL_IS_ALREADY_IN_USE("email is already in use"),
    NO_USER_WITH_THIS_ID("there is no user with this id"),
    WRONG_PASSWORD("wrong password"),
    COURSE_ALREADY_EXISTS("course already exists"),
    NO_LECTURER_WITH_THIS_ID("there is no lecturer with this id"),
    NO_COURSE_WITH_THIS_ID("there is no course with this id"),
    STUDENT_ALREADY_ENROLLED("student already enrolled"),
    NO_STUDENT_COURSE_WITH_THIS_ID("there is no student course with this id"),
    NOT_LECTURER_OF_THIS_COURSE("you are not lecturer of this course"),
    TASK_ALREADY_EXISTS("task already exists"),
    NO_TASK_WITH_THIS_ID("there is no task with this id"),
    STUDENT_NOT_ENROLLED("student not enrolled for this course"),
    STUDENT_ALREADY_COMPLETED("student already completed this task"),
    NO_STUDENT_TASK_WITH_THIS_ID("there is no student task with this id"),
    GRADE_EXCEED_LIMIT("grade cannot exceed limit"),

    NO_SUCH_FILE("no such file"),
    NO_QUESTION_WITH_THIS_ID("there is no question with this id"),

    WRONG_INPUT_TYPE("wrong input type");

    private final String message;

    WrongInputType(String message) {
        this.message = message;
    }
}
