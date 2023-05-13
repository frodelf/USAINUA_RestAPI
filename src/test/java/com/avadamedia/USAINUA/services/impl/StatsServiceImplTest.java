package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.Stats;
import com.avadamedia.USAINUA.repositories.StatsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class StatsServiceImplTest {
    @MockBean
    private StatsRepository statsRepository;
    @Autowired
    private StatsServiceImpl statsService;
    @Test
    void getAllMonth() {
        List<Stats> statsList = new ArrayList<>();
        statsList.add(new Stats(1L,"January", 100L));
        statsList.add(new Stats(2L, "February", 200L));
        statsList.add(new Stats(3L, "March", 300L));

        when(statsRepository.findAll()).thenReturn(statsList);

        List<String> result = statsService.getAllMonth();
        List<String> expected = List.of("January", "February", "March");

        assertEquals(expected, result);
    }
    @Test
    void getAllValue() {
        List<Stats> statsList = new ArrayList<>();
        statsList.add(new Stats(1L,"January", 100L));
        statsList.add(new Stats(2L, "February", 200L));
        statsList.add(new Stats(3L, "March", 300L));

        when(statsRepository.findAll()).thenReturn(statsList);

        List<Long> result = statsService.getAllValue();
        List<Long> expected = List.of(100L, 200L, 300L);

        assertEquals(expected, result);
    }
}