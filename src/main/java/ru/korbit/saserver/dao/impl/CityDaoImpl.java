package ru.korbit.saserver.dao.impl;

import org.springframework.stereotype.Repository;
import ru.korbit.saserver.dao.CityDao;
import ru.korbit.saserver.domain.Area;
import ru.korbit.saserver.domain.City;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Artur Belogur on 24.10.17.
 */
@Repository(value = "cityDao")
public class CityDaoImpl extends SessionFactoryHolder implements CityDao {

    @Override
    public long add(City city) {
        super.save(city);
        return city.getId();
    }

    @Override
    public Optional<City> get(Long id) {
        return super.get(id, City.class);
    }

    @Override
    public Optional<City> getByName(String name) {
        return getSession()
                .createQuery("SELECT c FROM City c WHERE c.name = :name", City.class)
                .setParameter("name", name)
                .uniqueResultOptional();
    }

    @Override
    public void update(City city) {
        super.update(city);
    }

    @Override
    public Stream<City> getAll() {
        return getSession()
                .createQuery("SELECT c FROM City c", City.class)
                .stream();
    }
}
