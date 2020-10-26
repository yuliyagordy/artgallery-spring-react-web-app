package com.ygordy.artgallery.repository;

import com.ygordy.artgallery.model.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Long> {

    Artist findByFullName(String fullName);
}
