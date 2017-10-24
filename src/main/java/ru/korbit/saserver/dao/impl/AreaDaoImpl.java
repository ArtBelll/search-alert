package ru.korbit.saserver.dao.impl;

import org.springframework.stereotype.Repository;
import ru.korbit.saserver.dao.AreaDao;
import ru.korbit.saserver.domain.Area;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Artur Belogur on 24.10.17.
 */
@Repository(value = "areaDao")
public class AreaDaoImpl extends SessionFactoryHolder implements AreaDao {

    @Override
    public long add(Area area) {
        super.save(area);
        return area.getId();
    }

    @Override
    public Optional<Area> get(Long id) {
        return getSession().byId(Area.class).loadOptional(id);
    }

    @Override
    public Optional<Area> getByName(String name) {
        return getSession()
                .createQuery("SELECT a FROM Area a WHERE a.name = :name", Area.class)
                .setParameter("name", name)
                .uniqueResultOptional();
    }

    @Override
    public void update(Area area) {
        super.update(area);
    }

    @Override
    public Stream<Area> getAll() {
        return getSession()
                .createQuery("SELECT a FROM Area a", Area.class)
                .stream();
    }
}
