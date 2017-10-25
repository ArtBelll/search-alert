package ru.korbit.saserver.controllers;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.korbit.saserver.Constants;
import ru.korbit.saserver.domain.Event;

import java.io.File;
import java.io.IOException;

/**
 * Created by Artur Belogur on 24.10.17.
 */
@RestController
@Slf4j
@Transactional
@RequestMapping(value = "events")
public class EventsCollection {

    @PostMapping
    public ResponseEntity<?> addEvent(@RequestParam("event") Event event,
                                      @RequestParam("images") MultipartFile[] images) throws IOException {

        for (MultipartFile image : images) {
            image.getBytes();
            val path = String.format(Constants.IMAGE_TEMPLATE, image.getOriginalFilename());
            val file = new File(path);
            image.transferTo(file);
            log.info("Upload file: {}", file.getAbsoluteFile());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
