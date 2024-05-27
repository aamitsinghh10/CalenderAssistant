package com.calender.calenderAssist.Entity;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Meeting {
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
