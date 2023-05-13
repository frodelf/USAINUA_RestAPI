package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.AdditionalService;
import com.avadamedia.USAINUA.repositories.AdditionalServicesRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AdditionalServicesServiceImplTest {

    @Mock
    private AdditionalServicesRepository additionalServicesRepository;

    @InjectMocks
    private AdditionalServicesServiceImpl additionalServicesService;

    @Test
    public void testSave() {
        AdditionalService additionalService = new AdditionalService();
        additionalService.setName("Test Service");
        additionalServicesService.save(additionalService);
        verify(additionalServicesRepository, times(1)).save(additionalService);
    }

    @Test
    void getByName() {
        AdditionalService additionalService = new AdditionalService();
        additionalService.setName("Test Service");
        when(additionalServicesRepository.findByName("Test Service")).thenReturn(Optional.of(additionalService));
        AdditionalService result = additionalServicesService.getByName("Test Service");
        assertEquals(additionalService, result);
    }

    @Test
    void getById() {
        AdditionalService additionalService = new AdditionalService();
        additionalService.setId(1L);
        when(additionalServicesRepository.findById(1L)).thenReturn(Optional.of(additionalService));
        AdditionalService result = additionalServicesService.getById(1L);
        assertEquals(additionalService, result);
    }

    @Test
    void getAll() {
        List<AdditionalService> additionalServices = Arrays.asList(new AdditionalService(), new AdditionalService());
        when(additionalServicesRepository.findAll()).thenReturn(additionalServices);
        List<AdditionalService> result = additionalServicesService.getAll();
        assertEquals(additionalServices, result);
    }

    @Test
    public void testDeleteById() {
        long serviceId = 1L;
        additionalServicesService.deleteById(serviceId);
        verify(additionalServicesRepository, times(1)).deleteById(serviceId);
    }
}