package com.calender.calenderAssist.EntityTest;
import com.calender.calenderAssist.Entity.Meeting;
import com.calender.calenderAssist.Entity.MeetingRequest;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MeetingRequestTest {

    @Test
    public void testGettersAndSetters() {
        LocalDateTime startTime = LocalDateTime.of(2024, 6, 1, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 6, 1, 10, 0);
        List<String> participants = new ArrayList<>();
        participants.add("Alice");
        participants.add("Bob");

        Meeting meeting = new Meeting();
        meeting.setTitle("Team Meeting");
        meeting.setStartTime(startTime);
        meeting.setEndTime(endTime);

        MeetingRequest meetingRequest = new MeetingRequest();
        meetingRequest.setOwner("John");
        meetingRequest.setMeeting(meeting);
        meetingRequest.setParticipants(participants);

        assertEquals("John", meetingRequest.getOwner());
        assertEquals(meeting, meetingRequest.getMeeting());
        assertEquals(participants, meetingRequest.getParticipants());
    }

    @Test
    public void testToString() {
        LocalDateTime startTime = LocalDateTime.of(2024, 6, 1, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 6, 1, 10, 0);
        List<String> participants = new ArrayList<>();
        participants.add("Alice");
        participants.add("Bob");

        Meeting meeting = new Meeting("Team Meeting", startTime, endTime);

        MeetingRequest meetingRequest = new MeetingRequest("John", meeting, participants);

        String expectedToString = "MeetingRequest(owner=John, meeting=Meeting(title=Team Meeting, startTime=2024-06-01T09:00, endTime=2024-06-01T10:00), participants=[Alice, Bob])";
        assertEquals(expectedToString, meetingRequest.toString());
    }
}

