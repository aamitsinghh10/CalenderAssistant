package com.calender.calenderAssist.RepositoryTest;

import com.calender.calenderAssist.Entity.Calender;
import com.calender.calenderAssist.Repository.CalenderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CalenderRepositoryTest {

    private CalenderRepository calenderRepository;

    @BeforeEach
    void setUp() {
        calenderRepository = new CalenderRepository();
    }

    @Test
    void testGetCalender() {
        String owner = "John";
        Calender expectedCalender = new Calender();
        expectedCalender.setOwner(owner);
        calenderRepository.saveCalender(expectedCalender);
        Calender retrievedCalender = calenderRepository.getCalender(owner);

        assertEquals(expectedCalender, retrievedCalender);
    }

    @Test
    void testSaveCalender() {
        Calender calenderToSave = new Calender();
        calenderToSave.setOwner("Alice");

        calenderRepository.saveCalender(calenderToSave);
        assertEquals(1, calenderRepository.calenders.size());
        assertEquals(calenderToSave, calenderRepository.calenders.get(calenderToSave.getOwner()));
    }
}
