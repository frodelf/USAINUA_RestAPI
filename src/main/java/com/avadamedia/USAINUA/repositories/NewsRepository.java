package com.avadamedia.USAINUA.repositories;

import com.avadamedia.USAINUA.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {

}
