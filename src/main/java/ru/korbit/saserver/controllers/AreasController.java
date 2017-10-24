package ru.korbit.saserver.controllers;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.korbit.saserver.dao.AreaDao;
import ru.korbit.saserver.domain.Area;

import java.util.stream.Collectors;

/**
 * Created by Artur Belogur on 24.10.17.
 */
@RestController
@RequestMapping(value = "area")
public class AreasController {

    private final AreaDao areaDao;

    @Autowired
    public AreasController(AreaDao areaDao) {
        this.areaDao = areaDao;
    }

    @GetMapping
    public ResponseEntity<?> getAreas() {
        val areas = areaDao.getAll().collect(Collectors.toList());
        return new ResponseEntity<>(areas, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addAreas(@RequestBody Area area) {
        if (area != null) {
            areaDao.add(area);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{areaId}")
    public ResponseEntity<?> getArea(@PathVariable Long areaId) {
        val area = areaDao.get(areaId);
        return new ResponseEntity<>(area, HttpStatus.OK);
    }
}
