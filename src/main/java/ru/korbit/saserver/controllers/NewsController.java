package ru.korbit.saserver.controllers;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.korbit.saserver.dao.NewsDao;
import ru.korbit.saserver.domain.News;
import ru.korbit.saserver.modeles.SearchParameters;

/**
 * Created by Artur Belogur on 25.10.17.
 */
@RestController
@RequestMapping(value = "news")
public class NewsController {

    private final NewsDao newsDao;

    @Autowired
    public NewsController(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @PostMapping(value = "search")
    public ResponseEntity<?> searchNews(@RequestBody SearchParameters parameters) {
        val news = newsDao.searchNews(parameters.getCitiesId());
        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping(value = "{newsId}")
    public ResponseEntity<?> getNews(@PathVariable("newsId") Long newsId) {
        return newsDao.get(newsId)
                .map(news -> new ResponseEntity<>(news, HttpStatus.OK))
                .orElse(new ResponseEntity<News>(HttpStatus.BAD_REQUEST));
    }

}
