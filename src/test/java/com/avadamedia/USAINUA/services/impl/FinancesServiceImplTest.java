package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.Finances;
import com.avadamedia.USAINUA.repositories.FinancesRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class FinancesServiceImplTest {
    @Mock
    private FinancesRepository financesRepository;
    @InjectMocks
    private FinancesServiceImpl financesService;
    @Test
    void save() {
        Finances finances = new Finances();
        finances.setSum(1000);
        financesService.save(finances);
        verify(financesRepository, times(1)).save(finances);
    }

    @Test
    void getAll() {
        List<Finances> finances = Arrays.asList(new Finances(), new Finances());
        when(financesRepository.findAll()).thenReturn(finances);
        List<Finances> result = financesService.getAll();
        assertEquals(finances, result);
    }
}