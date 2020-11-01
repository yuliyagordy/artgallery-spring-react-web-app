package com.ygordy.artgallery.service;

import com.ygordy.artgallery.model.Artist;
import com.ygordy.artgallery.model.Picture;
import com.ygordy.artgallery.repository.ArtistRepository;
import com.ygordy.artgallery.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GalleryService {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private PictureRepository pictureRepository;

    public List<Artist> listAllArtists() {
        return (List<Artist>) artistRepository.findAll();
    }

    public Optional<Artist> findArtistById(long id) {
        return artistRepository.findById(id);
    }

    public Artist findArtistByFullName(String fullName) {
        return artistRepository.findByFullName(fullName);
    }

    public Optional<Picture> findPictureById(Long id) {
        return pictureRepository.findById(id);
    }

    public List<Picture> listAllPictures() {
        return (List<Picture>) pictureRepository.findAll();
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
        updatedPicture = pictureRepository.save(updatedPicture);
        return updatedPicture;
    }
}
