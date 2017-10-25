package ru.korbit.saserver.controllers;

import lombok.val;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.korbit.saserver.Constants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by Artur Belogur on 25.10.17.
 */
@RestController
@RequestMapping(value = "images")
public class ImagesController {

    @GetMapping(value = "{imageName}/")
    public ResponseEntity<?> getImage(@PathVariable("imageName") String imageName) throws IOException {
        val file = new File(String.format(Constants.IMAGE_TEMPLATE, imageName));
        byte[] image = FileUtils.readFileToByteArray(file);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(Files.probeContentType(file.toPath())));
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
}
