package ru.korbit.saserver.dao;

import lombok.NonNull;
import ru.korbit.saserver.domain.Area;
import ru.korbit.saserver.domain.City;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Artur Belogur on 24.10.17.
 */
public interface CityDao {

    long add(@NonNull City city);

    Optional<City> get(@NonNull Long id);

    Optional<City> getByName(@NonNull String name);

    void update(@NonNull City city);

    Stream<City> getAll();
}
