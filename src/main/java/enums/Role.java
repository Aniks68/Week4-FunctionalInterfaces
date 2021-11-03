package enums;

public enum Role {
    TEACHER(1),
    SENIOR_STUDENT(2),
    JUNIOR_STUDENT(3);

    private int priority;

    Role(int priority) {
        this.priority = priority;
    }

    public int getPriority(){
        return priority;
    }
}
