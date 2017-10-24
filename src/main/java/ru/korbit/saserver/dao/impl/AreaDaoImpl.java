package ru.korbit.saserver.dao.impl;

import org.springframework.stereotype.Repository;
import ru.korbit.saserver.dao.AreaDao;
import ru.korbit.saserver.domain.Area;

import java.util.Optional;

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
    public void update(Area area) {
        super.update(area);
    }
}
