package ru.korbit.saserver.controllers;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.korbit.saserver.Constants;
import ru.korbit.saserver.dao.EventDao;
import ru.korbit.saserver.domain.Event;
import ru.korbit.saserver.modeles.SearchParameters;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Artur Belogur on 24.10.17.
 */
@RestController
@Slf4j
@Transactional
@RequestMapping(value = "events")
public class EventsCollection {

    private final EventDao eventDao;

    @Autowired
    public EventsCollection(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @PostMapping
    public ResponseEntity<?> addEvent(@RequestParam("event") Event event,
                                      @RequestParam("images") MultipartFile[] images) throws IOException {

        for (MultipartFile image : images) {
            image.getBytes();
            val path = String.format(Constants.IMAGE_TEMPLATE, image.getOriginalFilename());
            val file = new File(path);
            image.transferTo(file);

            event.getPhotoUrls().add(new URL(String.format(
                    Constants.IMAGE_URL_TEMPLATE, image.getOriginalFilename())));

            log.info("Upload file: {}", file.getAbsoluteFile());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<?> search(@RequestBody SearchParameters parameters) {
        val events = eventDao
                .searchEvent(parameters.getStatuses(), parameters.getCitiesId())
                .collect(Collectors.toList());
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
