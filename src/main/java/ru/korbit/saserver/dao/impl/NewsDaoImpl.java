package ru.korbit.saserver.dao.impl;

import org.springframework.stereotype.Repository;
import ru.korbit.saserver.dao.NewsDao;
import ru.korbit.saserver.domain.Event;
import ru.korbit.saserver.domain.News;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Artur Belogur on 25.10.17.
 */
@Repository(value = "newsDao")
public class NewsDaoImpl extends SessionFactoryHolder implements NewsDao {

    @Override
    public long add(News news) {
        super.save(news);
        return news.getId();
    }

    @Override
    public Optional<News> get(Long newsId) {
        return super.get(newsId, News.class);
    }

    @Override
    public Stream<News> searchNews(List<Long> citiesId) {
        return getSession()
                .createQuery("SELECT n FROM News n " +
                        "JOIN City c ON c.id IN (:ids)", News.class)
                .setParameter("ids", citiesId)
                .stream();
    }
}
