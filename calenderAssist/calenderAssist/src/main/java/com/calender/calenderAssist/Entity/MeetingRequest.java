package com.calender.calenderAssist.Entity;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MeetingRequest {
    private String owner;
    private Meeting meeting;
    private List<String> participants;
}

