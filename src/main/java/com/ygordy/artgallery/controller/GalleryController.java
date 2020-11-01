package com.ygordy.artgallery.controller;

import com.ygordy.artgallery.model.Artist;
import com.ygordy.artgallery.model.Picture;
import com.ygordy.artgallery.service.GalleryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GalleryController {
    private static final Logger log = LoggerFactory.getLogger(GalleryController.class);

    @Autowired
    GalleryService galleryService;

    @GetMapping("/artists")
    public List<Artist> listAllArtists() {
        log.info("GET REST request to get list of artists");
        return galleryService.listAllArtists();
    }

    @GetMapping("/pictures")
    public List<Picture> listAllPictures() {
        log.info("GET REST request to get list of pictures");
//        List<Picture>  pictures = galleryService.listAllPictures();
//        pictures.forEach(picture -> System.out.println(picture.toString()));

        return galleryService.listAllPictures();
    }

    @GetMapping("/artist/name/{name}")
    public ResponseEntity<Artist> getArtistByName(@PathVariable String name) {
        log.info("GET REST request to get Artist by fullName: {}", name);
        Optional<Artist> artist = Optional.ofNullable(galleryService.findArtistByFullName(name));
        return artist.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/artist/id/{id}")
    public ResponseEntity<?> getArtistById(@PathVariable Long id) {
        log.info("GET REST request to get Artist by Id: {}", id);
        Optional<Artist> artist = galleryService.findArtistById(id);
        return artist.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/picture/add")
    public ResponseEntity<Picture> addNewPicture(@RequestParam("artistId") long artistId,
                                                 @RequestBody Picture picture) {
        log.info("POST REST request to add new Picture ");
        Picture newPicture = galleryService.addPicture(picture, artistId);
        if (newPicture != null) {
            System.out.println(newPicture.toString());
            return ResponseEntity.ok().body(newPicture);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/picture/update")
    public ResponseEntity<Picture> updatePicture(@RequestBody Picture picture) {
        log.info("POST REST request to update Picture ");
        Picture updatedPicture = galleryService.updatePicture(picture);
        if (updatedPicture != null) {
            System.out.println(updatedPicture.toString());
            return ResponseEntity.ok().body(updatedPicture);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
