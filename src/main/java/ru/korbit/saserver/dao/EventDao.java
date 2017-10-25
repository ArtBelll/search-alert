package ru.korbit.saserver.dao;

import lombok.NonNull;
import ru.korbit.saserver.domain.Event;
import ru.korbit.saserver.modeles.EventStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Artur Belogur on 25.10.17.
 */
public interface EventDao {

    long add(@NonNull Event event);

    Optional<Event> get(@NonNull Long eventId);

    Stream<Event> searchEvent(@NonNull List<EventStatus> status, List<Long> citiesId);
}
