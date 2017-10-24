package ru.korbit.saserver.controllers;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.korbit.saserver.dao.AreaDao;
import ru.korbit.saserver.domain.Area;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Artur Belogur on 24.10.17.
 */
@RestController
@RequestMapping(value = "areas")
@Transactional
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
        val areas = areaDao.getAll().collect(Collectors.toList());
        return new ResponseEntity<>(areas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addAreas(@RequestBody Area area) {
        if (area != null) {
            areaDao.add(area);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{areaId}")
    public ResponseEntity<?> getArea(@PathVariable Long areaId) {
        val area = areaDao.get(areaId);
        return new ResponseEntity<>(area, HttpStatus.OK);
    }
}
