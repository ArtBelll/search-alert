package ru.korbit.saserver.dao;

import lombok.NonNull;
import ru.korbit.saserver.domain.Area;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Artur Belogur on 24.10.17.
 */
public interface AreaDao {

    long add(@NonNull Area area);

    Optional<Area> get(@NonNull Long id);

    Optional<Area> getByName(@NonNull String name);

    void update(@NonNull Area area);

    Stream<Area> getAll();
}
