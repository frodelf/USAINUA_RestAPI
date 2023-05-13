package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.News;
import com.avadamedia.USAINUA.repositories.NewsRepository;
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
class NewsServiceImplTest {
    @Mock
    private NewsRepository newsRepository;
    @InjectMocks
    private NewsServiceImpl newsService;
    @Test
    void getById() {
        News news = new News();
        news.setId(1L);
        when(newsRepository.findById(1L)).thenReturn(Optional.of(news));
        News result = newsService.getById(1L);
        assertEquals(news, result);
    }

    @Test
    void getAll() {
        List<News> news = Arrays.asList(new News(), new News());
        when(newsRepository.findAll()).thenReturn(news);
        List<News> result = newsService.getAll();
        assertEquals(news, result);
    }

    @Test
    void save() {
        News news = new News();
        news.setId(1L);
        newsService.save(news);
        verify(newsRepository, times(1)).save(news);
    }

    @Test
    void deleteById() {
        newsService.deleteById(1L);
        verify(newsRepository, times(1)).deleteById(1L);
    }
}