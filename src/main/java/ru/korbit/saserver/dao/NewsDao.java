package ru.korbit.saserver.dao;

import lombok.NonNull;
import ru.korbit.saserver.domain.Event;
import ru.korbit.saserver.domain.News;
import ru.korbit.saserver.modeles.EventStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Artur Belogur on 25.10.17.
 */
public interface NewsDao {

    long add(@NonNull News news);

    Optional<News> get(@NonNull Long newsId);

    Stream<News> searchNews(@NonNull List<Long> citiesId);
}
