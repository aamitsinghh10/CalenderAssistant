package com.calender.calenderAssist.ServiceTest;

import com.calender.calenderAssist.Entity.Calender;
import com.calender.calenderAssist.Entity.Meeting;
import com.calender.calenderAssist.Repository.CalenderRepository;
import com.calender.calenderAssist.Service.CalenderService;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalenderServiceTest {

    @Test
    void testFreeSlots_NoMeetings() {
        CalenderRepository calenderRepository = mock(CalenderRepository.class);
        CalenderService calenderService = new CalenderService(calenderRepository);

        when(calenderRepository.getCalender(anyString())).thenReturn(null);
        List<LocalDateTime> freeSlots = calenderService.freeSlots("owner1", "owner2", Duration.ofMinutes(30));
        assertEquals(48, freeSlots.size()); // 24 hours * 2 owners = 48 slots
    }

    @Test
    void testFreeSlots_WithMeetings() {
        CalenderRepository calenderRepository = mock(CalenderRepository.class);
        CalenderService calenderService = new CalenderService(calenderRepository);

        Calender calender1 = new Calender();
        List<Meeting> meetings1 = new ArrayList<>();
        meetings1.add(new Meeting("Meeting 1", LocalDateTime.of(2024, 5, 27, 10, 0), LocalDateTime.of(2024, 5, 27, 11, 0)));
        calender1.setMeetings(meetings1);

        when(calenderRepository.getCalender("owner1")).thenReturn(calender1);
        when(calenderRepository.getCalender("owner2")).thenReturn(null);

        List<LocalDateTime> freeSlots = calenderService.freeSlots("owner1", "owner2", Duration.ofMinutes(30));
        assertEquals(47, freeSlots.size()); // One slot should be occupied by the meeting
    }

    @Test
    void testFindConflicts() {
        CalenderRepository calenderRepository = mock(CalenderRepository.class);
        CalenderService calenderService = new CalenderService(calenderRepository);

        Calender calender = new Calender();
        Meeting existingMeeting = new Meeting("Existing Meeting", LocalDateTime.of(2024, 5, 27, 10, 0), LocalDateTime.of(2024, 5, 27, 11, 0));
        calender.getMeetings().add(existingMeeting);

        when(calenderRepository.getCalender("participant")).thenReturn(calender);

        Meeting newMeeting = new Meeting("New Meeting", LocalDateTime.of(2024, 5, 27, 10, 30), LocalDateTime.of(2024, 5, 27, 11, 30));
        List<String> participants = List.of("participant");
        List<String> conflicts = calenderService.findConflicts(newMeeting, participants);
        assertEquals(1, conflicts.size());
        assertEquals("participant", conflicts.get(0));
    }
}

