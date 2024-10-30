package com.telusko.ecomproj.service;


import com.telusko.ecomproj.model.Favourites;
import com.telusko.ecomproj.model.Song;
import com.telusko.ecomproj.repo.FavRepo;
import com.telusko.ecomproj.repo.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongRepo repo;
    
    private FavRepo fr;
    
    @Autowired
    Favourites f;

    public List<Song> getAllSongs() {
        return repo.findAll();
    }
    
    public Song getSongById(int id)
    {
    	return repo.findById(id).orElse(null);
    }


    public Song addSong(Song song, MultipartFile imageFile, MultipartFile songFile) throws IOException {
        song.setImageName(imageFile.getOriginalFilename());
        song.setImageType(imageFile.getContentType());
        song.setImageData(imageFile.getBytes());
        song.setSongName(songFile.getOriginalFilename());
        song.setSongType(songFile.getContentType());
        song.setSongData(songFile.getBytes());

        return repo.save(song);
    }

    public Song updateSong(int id, Song song, MultipartFile imageFile) throws IOException {

        song.setImageName(imageFile.getOriginalFilename());
        song.setImageType(imageFile.getContentType());
        song.setImageData(imageFile.getBytes());
        return repo.save(song);
    }

    
    public Favourites addTofavourite( Integer songId)
    {
    	Song s = repo.findById(songId).orElseThrow(() -> new RuntimeException("Item not found"));
    	
     	f.add(s);
    	return fr.save(f);
    	
    }
  
    
    public void deleteSong(int id) {
        repo.deleteById(id);
    }


    public List<Song> searchSongs(String keyword) {
        return repo.searchSongs(keyword);
    }

	public List<Favourites> favourites() {
		
		return fr.findAll();
	}
}
