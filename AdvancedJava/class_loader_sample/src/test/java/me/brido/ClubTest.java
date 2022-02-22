package me.brido;

import org.junit.Assert;
import org.junit.Test;


public class ClubTest {
    @Test
    public void isFull() {
        Club club = new Club();
        club.maxNumberOfAttendees = 50;
        club.numberOfEnrollment = 35;
        Assert.assertFalse(club.isEnrollmentFull());
    }
}