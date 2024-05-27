package com.calender.calenderAssist.Controller;

import com.calender.calenderAssist.Service.CalenderService;
import com.calender.calenderAssist.Entity.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/calendar")
public class CalenderController {

    @Autowired
    private CalenderService calendarService;

    @PostMapping("/{owner}/meetings")
    public void bookMeeting(@PathVariable String owner, @RequestBody Meeting meeting) {
        calendarService.bookMeetings(owner, meeting);
    }

    @GetMapping("/freeslots")
    public List<LocalDateTime> findFreeSlots(@RequestParam String owner1, @RequestParam String owner2, @RequestParam long durationMinutes) {
        return calendarService.freeSlots(owner1, owner2, Duration.ofMinutes(durationMinutes));
    }

    @PostMapping("/conflicts")
    public List<String> findConflicts(@RequestBody Meeting meeting, @RequestParam List<String> participants) {
        return calendarService.findConflicts(meeting, participants);
    }
}
