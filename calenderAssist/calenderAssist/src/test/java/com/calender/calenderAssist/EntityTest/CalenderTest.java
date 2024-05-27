package com.calender.calenderAssist.EntityTest;

import com.calender.calenderAssist.Entity.Calender;
import com.calender.calenderAssist.Entity.Meeting;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CalendarTest {

    @Test
    public void testGetOwner() {
        Calender calendar = new Calender();
        calendar.setOwner("John");
        assertEquals("John", calendar.getOwner());
    }

    @Test
    public void testGetMeetings() {
        List<Meeting> meetings = new ArrayList<>();
        meetings.add(new Meeting("Meeting 1", LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
        meetings.add(new Meeting("Meeting 2", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(1)));

        Calender calendar = new Calender();
        calendar.setMeetings(meetings);

        assertEquals(2, calendar.getMeetings().size());
    }

    @Test
    public void testToString() {
        Calender calendar = new Calender();
        calendar.setOwner("John");
        List<Meeting> meetings = new ArrayList<>();
        meetings.add(new Meeting("Meeting 1", LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
        calendar.setMeetings(meetings);

        String expected = "Calender(owner=John, meetings=[Meeting(title=Meeting 1, startTime=" + meetings.get(0).getStartTime() + ", endTime=" + meetings.get(0).getEndTime() + ")])";
        assertEquals(expected, calendar.toString());
    }
}

