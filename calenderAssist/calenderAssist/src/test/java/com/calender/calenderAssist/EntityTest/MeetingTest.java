package com.calender.calenderAssist.EntityTest;

import com.calender.calenderAssist.Entity.Meeting;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeetingTest {

    @Test
    public void testGettersAndSetters() {
        LocalDateTime startTime = LocalDateTime.of(2024, 6, 1, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 6, 1, 10, 0);

        Meeting meeting = new Meeting();
        meeting.setTitle("Team Meeting");
        meeting.setStartTime(startTime);
        meeting.setEndTime(endTime);

        assertEquals("Team Meeting", meeting.getTitle());
        assertEquals(startTime, meeting.getStartTime());
        assertEquals(endTime, meeting.getEndTime());
    }

    @Test
    public void testToString() {
        LocalDateTime startTime = LocalDateTime.of(2024, 6, 1, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 6, 1, 10, 0);

        Meeting meeting = new Meeting("Team Meeting", startTime, endTime);

        String expectedToString = "Meeting(title=Team Meeting, startTime=2024-06-01T09:00, endTime=2024-06-01T10:00)";
        assertEquals(expectedToString, meeting.toString());
    }
}

