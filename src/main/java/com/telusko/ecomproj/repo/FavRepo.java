package com.telusko.ecomproj.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telusko.ecomproj.model.Favourites;
import com.telusko.ecomproj.model.Song;

public interface FavRepo extends JpaRepository<Favourites, Integer>{

//	Favourites save(Song s);

	

}
