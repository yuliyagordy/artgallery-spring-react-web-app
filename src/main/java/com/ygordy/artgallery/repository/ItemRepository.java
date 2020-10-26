package com.ygordy.artgallery.repository;

import com.ygordy.artgallery.model.Artist;
import com.ygordy.artgallery.model.Item;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> findByArtist(Artist artist, Sort sort);

}
