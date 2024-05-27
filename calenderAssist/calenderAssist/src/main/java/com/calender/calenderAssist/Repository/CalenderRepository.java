package com.calender.calenderAssist.Repository;

import com.calender.calenderAssist.Entity.Calender;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class CalenderRepository {
    public HashMap<String, Calender> calenders = new HashMap<>();

    public Calender getCalender(String owner){
        return calenders.get(owner);
    }

    public void saveCalender(Calender calender){
        calenders.put(calender.getOwner(), calender);
    }
}
