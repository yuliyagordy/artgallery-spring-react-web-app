package com.ygordy.artgallery.repository;

import com.ygordy.artgallery.model.Artist;
import com.ygordy.artgallery.model.Picture;
import com.ygordy.artgallery.model.Style;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Long> {

    List<Picture> findByArtist(Artist artist, Sort sort);

//    List<Picture> findByStyle(Style style, Sort sort);

}
