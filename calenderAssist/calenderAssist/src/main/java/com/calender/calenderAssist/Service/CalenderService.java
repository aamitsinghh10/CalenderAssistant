package com.calender.calenderAssist.Service;

import com.calender.calenderAssist.Entity.Calender;
import com.calender.calenderAssist.Entity.Meeting;
import com.calender.calenderAssist.Repository.CalenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CalenderService {
    private final CalenderRepository calenderRepository;
    @Autowired
    public CalenderService(CalenderRepository calenderRepository) {
        this.calenderRepository = calenderRepository;
    }

    public void bookMeetings(String owner, Meeting meeting) {
        Calender calender = calenderRepository.getCalender(owner);
        if (calender == null) {
            calender = new Calender();
            calender.setOwner(owner);
            calender.setMeetings(new ArrayList<>());
        }
        calender.getMeetings().add(meeting);
        calenderRepository.saveCalender(calender);
    }

    public List<LocalDateTime> freeSlots(String owner1, String owner2, Duration duration) {
        Calender calender1 = calenderRepository.getCalender(owner1);
        Calender calender2 = calenderRepository.getCalender(owner2);

        List<Meeting> meetings1 = calender1 != null ? calender1.getMeetings() : Collections.emptyList();
        List<Meeting> meetings2 = calender2 != null ? calender2.getMeetings() : Collections.emptyList();

        List<Meeting> allMeetings = new ArrayList<>(meetings1);
        allMeetings.addAll(meetings2);
        allMeetings.sort(Comparator.comparing(Meeting::getStartTime));

        LocalDateTime start = LocalDateTime.now();
        LocalDateTime endOfWorkday = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);

        List<LocalDateTime> freeSlots = new ArrayList<>();

        for (Meeting meeting : allMeetings) {
            LocalDateTime end = meeting.getStartTime();
            if (Duration.between(start, end).compareTo(duration) >= 0) {
                freeSlots.add(start);
            }
            start = meeting.getEndTime();
        }

        if (Duration.between(start, endOfWorkday).compareTo(duration) >= 0) {
            freeSlots.add(start);
        }

        return freeSlots;
    }

    public List<String> findConflicts(Meeting meeting, List<String> participants) {
        List<String> conflicts = new ArrayList<>();

        for (String participant : participants) {
            Calender calender = calenderRepository.getCalender(participant);
            if (calender != null) {
                for (Meeting existingMeeting : calender.getMeetings()) {
                    if (meeting.getStartTime().isBefore(existingMeeting.getEndTime()) &&
                            meeting.getEndTime().isAfter(existingMeeting.getStartTime())) {
                        conflicts.add(participant);
                        break;
                    }
                }
            }
        }
        return conflicts;
    }
}
