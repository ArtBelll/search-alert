package ru.korbit.saserver.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.korbit.saserver.Constants;
import ru.korbit.saserver.Environment;
import ru.korbit.saserver.dao.EventDao;
import ru.korbit.saserver.domain.Event;
import ru.korbit.saserver.modeles.EventStatus;
import ru.korbit.saserver.modeles.SearchParameters;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.stream.Collectors;

/**
 * Created by Artur Belogur on 24.10.17.
 */
@RestController
@Slf4j
@Transactional
@RequestMapping(value = "events")
public class EventsCollection {

    private final String IMAGE_URL_TEMPLATE;

    private final EventDao eventDao;

    private final ObjectMapper mapper;

    @Autowired
    public EventsCollection(EventDao eventDao, Environment environment) {
        this.eventDao = eventDao;
        this.mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        this.IMAGE_URL_TEMPLATE = environment.getHost() + "/api/images/%s";
    }

    @PostMapping
    public ResponseEntity<?> addEvent(@RequestParam("event") MultipartFile data,
                                      @RequestParam("images") MultipartFile[] images) throws IOException {

        Event event = mapper.readValue(data.getInputStream(), Event.class);
        event.setStatus(EventStatus.FUTURE);

        for (MultipartFile image : images) {
            image.getBytes();
            val path = String.format(Constants.IMAGE_STORE_TEMPLATE, image.getOriginalFilename());
            val file = new File(path);
            image.transferTo(file);

            event.getPhotoUrls().add(new URL(String.format(
                    IMAGE_URL_TEMPLATE, image.getOriginalFilename())));

            log.info("Upload file: {}", file.getAbsoluteFile());
        }

        eventDao.add(event);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "{eventId}")
    public ResponseEntity<?> getEvent(@PathVariable("eventId") Long eventId) {
        return eventDao.get(eventId)
                .map(event -> new ResponseEntity<>(event, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PostMapping(value = "/search")
    public ResponseEntity<?> search(@RequestBody SearchParameters parameters) {
        val events = eventDao
                .searchEvent(parameters.getStatuses(), parameters.getCitiesId())
                .collect(Collectors.toList());
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
