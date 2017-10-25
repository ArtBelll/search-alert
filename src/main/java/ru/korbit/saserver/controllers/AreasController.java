package ru.korbit.saserver.controllers;

import javassist.tools.web.BadHttpRequest;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.korbit.saserver.dao.AreaDao;
import ru.korbit.saserver.domain.Area;
import ru.korbit.saserver.exeptions.AlreadyExist;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Artur Belogur on 24.10.17.
 */
@RestController
@RequestMapping(value = "areas")
@Transactional
@Slf4j
public class AreasController {

    private final AreaDao areaDao;

    @Autowired
    public AreasController(AreaDao areaDao) {
        this.areaDao = areaDao;
    }

    @GetMapping
    public ResponseEntity<?> getAreas() {
        val areas = areaDao.getAll()
                .peek(area -> area.setCities(new ArrayList<>()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(areas, HttpStatus.OK);
    }

    @GetMapping(value = "/cities")
    public ResponseEntity<?> getAreasWithCities() {
        val areas = areaDao.getAll()
                .peek(area -> Hibernate.initialize(area.getCities()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(areas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addAreas(@RequestBody Area area) {
        if (area == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (areaDao.getByName(area.getName()).isPresent()) {
            throw new AlreadyExist("This area already exist");
        }

        areaDao.add(area);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{areaId}")
    public ResponseEntity<?> getArea(@PathVariable Long areaId) {
        return areaDao.get(areaId)
                .map(area -> {
                    Hibernate.initialize(area.getCities());
                    return new ResponseEntity<>(area, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
