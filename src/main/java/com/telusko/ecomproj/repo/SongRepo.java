package com.telusko.ecomproj.repo;

import com.telusko.ecomproj.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepo extends JpaRepository<Song, Integer> {
    @Query("SELECT s from Song s WHERE " +
            "LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(s.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(s.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(s.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Song> searchSongs(String keyword);
   

}
