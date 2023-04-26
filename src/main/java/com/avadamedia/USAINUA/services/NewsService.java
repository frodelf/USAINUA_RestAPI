package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.entity.News;

import java.util.List;

public interface NewsService {
    News getById(long id);
    List<News> getAll();
    void save(News news);
    void deleteById(long id);
}
