package com.calender.calenderAssist.ControllerTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.calender.calenderAssist.Controller.CalenderController;
import com.calender.calenderAssist.Entity.Meeting;
import com.calender.calenderAssist.Service.CalenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CalenderControllerTest {

    @Mock
    private CalenderService calendarService;

    @InjectMocks
    private CalenderController calendarController;

    private Meeting sampleMeeting;
    private List<String> sampleParticipants;

    @BeforeEach
    void setUp() {
        sampleMeeting = new Meeting("Sample Meeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        sampleParticipants = Arrays.asList("participant1", "participant2");
    }

    @Test
    void testBookMeeting() {
        doNothing().when(calendarService).bookMeetings(anyString(), any(Meeting.class));

        calendarController.bookMeeting("Alice", sampleMeeting);

        verify(calendarService, times(1)).bookMeetings("Alice", sampleMeeting);
    }

    @Test
    void testFindFreeSlots() {
        List<LocalDateTime> freeSlots = Arrays.asList(LocalDateTime.now().plusHours(2));
        when(calendarService.freeSlots(anyString(), anyString(), any(Duration.class))).thenReturn(freeSlots);

        List<LocalDateTime> result = calendarController.findFreeSlots("owner1", "owner2", 30);

        assertEquals(freeSlots.size(), result.size());
        verify(calendarService, times(1)).freeSlots("owner1", "owner2", Duration.ofMinutes(30));
    }

    @Test
    void testFindConflicts() {
        List<String> conflicts = Arrays.asList("participant1");
        when(calendarService.findConflicts(any(Meeting.class), anyList())).thenReturn(conflicts);

        List<String> result = calendarController.findConflicts(sampleMeeting, sampleParticipants);

        assertEquals(conflicts.size(), result.size());
        verify(calendarService, times(1)).findConflicts(sampleMeeting, sampleParticipants);
    }
}
