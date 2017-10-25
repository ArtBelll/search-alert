package ru.korbit.saserver.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.korbit.saserver.domain.City;

import java.io.Serializable;
import java.util.Optional;

/**
 * Created by Artur Belogur on 11.10.17.
 */
public abstract class SessionFactoryHolder {
    @Autowired
    protected SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected <T> Optional<T> get(Serializable id, Class<T> tClass) {
        return getSession().byId(tClass).loadOptional(id);
    }

    protected <T> void save(T obj) {
        getSession().save(obj);
    }

    protected <T> void update(T obj) {
        getSession().update(obj);
    }

    protected <T> void delete(T obj) {
        getSession().delete(obj);
    }
}
