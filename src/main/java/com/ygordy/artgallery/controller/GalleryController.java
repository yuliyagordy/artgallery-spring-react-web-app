package com.ygordy.artgallery.controller;

import com.ygordy.artgallery.model.Artist;
import com.ygordy.artgallery.model.Picture;
import com.ygordy.artgallery.model.Style;
import com.ygordy.artgallery.service.GalleryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        log.info("GET REST request to get list of Artists");
        return galleryService.listAllArtists();
    }

    @GetMapping("/pictures")
    public List<Picture> listAllPictures() {
        log.info("GET REST request to get list of Pictures");
        return galleryService.listAllPictures();
    }

    @GetMapping("/styles")
    public List<Style> listAllStyles() {
        log.info("GET REST request to get list of Styles");
        return galleryService.listAllStyles();
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

    @PostMapping("/artist/add")
    public ResponseEntity<Artist> addNewArtist(@RequestBody Artist artist) {
        log.info("POST REST request to add new Artist ");
        Artist newArtist = galleryService.addArtist(artist);
        if (newArtist != null) {
            System.out.println(newArtist.toString());
            return ResponseEntity.ok().body(newArtist);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/artist/remove")
    public ResponseEntity removeArtist(@RequestParam("artistId") long artistId) {
        log.info("POST REST request to remove Artist id=" + artistId);
        galleryService.removeArtist(artistId);
        return ResponseEntity.ok().body("Artist has been removed");
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

    @PostMapping("/picture/remove")
    public ResponseEntity removePicture(@RequestParam("pictureId") long pictureId) {
        log.info("POST REST request to remove Picture id=" + pictureId);
        galleryService.removePicture(pictureId);
        return ResponseEntity.ok().body("Picture has been removed");
    }

    @PostMapping("/style/add")
    public ResponseEntity<Style> addStyle(@RequestBody Style style) {
        log.info("POST REST request to add Style ");
        Style newStyle = galleryService.addStyle(style);
        if (newStyle != null) {
            System.out.println(newStyle.toString());
            return ResponseEntity.ok().body(newStyle);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/style/remove")
    public ResponseEntity removeStyle(@RequestParam("styleId") long styleId) {
        log.info("POST REST request to remove Style id=" + styleId);
        galleryService.removeStyle(styleId);
        return ResponseEntity.ok().body("Style has been removed");
    }
}
