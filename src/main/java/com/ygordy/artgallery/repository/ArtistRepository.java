package com.ygordy.artgallery.repository;

import com.ygordy.artgallery.model.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {

    Artist findByFullName(String fullName);

}
