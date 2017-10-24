package ru.korbit.saserver.dao;

import lombok.NonNull;
import ru.korbit.saserver.domain.Area;

import java.util.Optional;

/**
 * Created by Artur Belogur on 24.10.17.
 */
public interface AreaDao {

    long add(@NonNull Area area);

    Optional<Area> get(@NonNull Long id);

    void update(@NonNull Area area);
}
