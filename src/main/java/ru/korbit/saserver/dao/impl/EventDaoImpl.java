package ru.korbit.saserver.dao.impl;

import org.springframework.stereotype.Repository;
import ru.korbit.saserver.dao.EventDao;
import ru.korbit.saserver.domain.Event;
import ru.korbit.saserver.modeles.EventStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Artur Belogur on 25.10.17.
 */
@Repository(value = "eventDao")
public class EventDaoImpl extends SessionFactoryHolder implements EventDao {

    @Override
    public long add(Event event) {
        super.save(event);
        return event.getId();
    }

    @Override
    public Optional<Event> get(Long eventId) {
        return super.get(eventId, Event.class);
    }

    @Override
    public Stream<Event> searchEvent(List<EventStatus> statuses, List<Long> citiesId) {
        return getSession()
                .createQuery("SELECT e FROM Event e " +
                        "JOIN City c ON c.id IN (:ids) " +
                        "WHERE e.status IN (:statuses)", Event.class)
                .setParameter("ids", citiesId)
                .setParameter("statuses", statuses)
                .stream();
    }
}
