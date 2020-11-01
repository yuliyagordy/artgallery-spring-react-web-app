package com.ygordy.artgallery.service;

import com.ygordy.artgallery.model.Artist;
import com.ygordy.artgallery.model.Picture;
import com.ygordy.artgallery.model.Style;
import com.ygordy.artgallery.repository.ArtistRepository;
import com.ygordy.artgallery.repository.PictureRepository;
import com.ygordy.artgallery.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class GalleryService {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private StyleRepository styleRepository;

    public List<Artist> listAllArtists() {
        return (List<Artist>) artistRepository.findAll();
    }

    public List<Picture> listAllPictures() {
        return (List<Picture>) pictureRepository.findAll();
    }

    public List<Style> listAllStyles() {
        return (List<Style>) styleRepository.findAll();
    }

    public Optional<Artist> findArtistById(long id) {
        return artistRepository.findById(id);
    }

    public Artist findArtistByFullName(String fullName) {
        return artistRepository.findByFullName(fullName);
    }

    public Artist addArtist(Artist artist) {
        Artist na = artistRepository.save(artist);
        if (na != null) {
            System.out.println(na.toString());
        }
        return na;
    }

    public void removeArtist(long artistId) {
        artistRepository.deleteById(artistId);
    }

    public Optional<Picture> findPictureById(Long id) {
        return pictureRepository.findById(id);
    }


    public Picture addPicture(Picture picture, long artistId) {
        Optional<Artist> optional = artistRepository.findById(artistId);
        picture.setVersion((long) 0);
        picture.setArtist(optional.get());
        Picture np = pictureRepository.save(picture);
        if (np != null) {
            System.out.println("new Picture Id = " + np.getId());
        }
        return np;
    }

    public Picture updatePicture(Picture picture) {
        Optional<Picture> optional = pictureRepository.findById(picture.getId());
        Picture updatedPicture = optional.get();
        if (!updatedPicture.getTitle().equals(picture.getTitle())) {
            updatedPicture.setTitle(picture.getTitle());
        }
        if (updatedPicture.getWidth() != picture.getWidth()) {
            updatedPicture.setWidth(picture.getWidth());
        }
        if (updatedPicture.getHeight() != picture.getHeight()) {
            updatedPicture.setHeight(picture.getHeight());
        }
        if (picture.getStyles() != null) {
            updatedPicture.getStyles().clear();
            updatedPicture.getStyles().addAll(picture.getStyles());
        }
        updatedPicture = pictureRepository.save(updatedPicture);
        return updatedPicture;
    }

    public void removePicture(long pictureId) {
        pictureRepository.deleteById(pictureId);
    }

    public Style addStyle(Style style) {
        Style ns = styleRepository.save(style);
        if (ns != null) {
            System.out.println("new Style Id = " + ns.getId());
        }
        return ns;
    }

    public void removeStyle(long styleId) {
        styleRepository.deleteById(styleId);
    }


}
