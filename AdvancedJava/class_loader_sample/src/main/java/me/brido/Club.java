package me.brido;

public class Club {
    int maxNumberOfAttendees;

    int numberOfEnrollment;

    public boolean isEnrollmentFull() {
        if (maxNumberOfAttendees == 0) {
            return false;
        }
        return numberOfEnrollment >= maxNumberOfAttendees;
    }
}
