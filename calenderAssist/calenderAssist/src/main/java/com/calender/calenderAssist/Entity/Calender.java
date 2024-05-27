package com.calender.calenderAssist.Entity;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Calender {
    private String owner;
    private List<Meeting> meetings;
}
