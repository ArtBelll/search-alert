package ru.korbit.saserver.controllers;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.korbit.saserver.dao.CityDao;
import ru.korbit.saserver.domain.City;
import ru.korbit.saserver.exeptions.AlreadyExist;

import java.util.stream.Collectors;

/**
 * Created by Artur Belogur on 24.10.17.
 */
@RestController
@RequestMapping(value = "cities")
@Transactional
public class CitiesController {

    private final CityDao cityDao;

    @Autowired
    public CitiesController(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @GetMapping
    public ResponseEntity<?> getCities() {
        val cities = cityDao.getAll().collect(Collectors.toList());
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCity(@RequestBody City city) {
        if (city == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (cityDao.getByName(city.getName()).isPresent()) {
            throw new AlreadyExist("This city already exist");
        }

        cityDao.add(city);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
