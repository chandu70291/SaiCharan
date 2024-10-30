package com.telusko.ecomproj.controller;


import com.telusko.ecomproj.model.Favourites;
import com.telusko.ecomproj.model.Song;
import com.telusko.ecomproj.service.SongService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SongController {

	private static Logger logger = LoggerFactory.getLogger(SongController.class);
	
    @Autowired
    private SongService service;
    
    @Autowired
    Favourites fav;


    @GetMapping("/products")
    public ResponseEntity<List<Song>> getAllSongs() {
   

        return new ResponseEntity<>(service.getAllSongs(), HttpStatus.OK);
    }
    

    @GetMapping("/product/{id}")
    public ResponseEntity<Song> getSong(@PathVariable int id) {
        Song song = service.getSongById(id);
        if (song != null) {
        	logger.info("getting the song with the id");
            return new ResponseEntity<>(song, HttpStatus.OK);
        } else {
        	logger.error("No song found with that id");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
   

    @PostMapping("/product")
    public ResponseEntity<?> addSong(@RequestPart Song product, @RequestPart MultipartFile imageFile, @RequestPart MultipartFile songFile) {
        try {
            Song song1 = service.addSong(product, imageFile, songFile);
            logger.info("song details added");
            return new ResponseEntity<>(song1, HttpStatus.CREATED);
        } catch (Exception e) {
        	logger.info("Unable to add the song details");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);


        }
    }


    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageBySongId(@PathVariable(value="productId") int songId) {
        Song song = service.getSongById(songId);
        byte[] imageFile = song.getImageData();

        return ResponseEntity.ok().contentType(MediaType.valueOf(song.getImageType())).body(imageFile);

    }
    
    
    @GetMapping("product/{productId}/song")
    public ResponseEntity<byte[]> getSongBySongId(@PathVariable(value="productId") int songId){
    	Song song = service.getSongById(songId);
    	byte[] songFile = song.getSongData();
    	
    	return ResponseEntity.ok().contentType(MediaType.valueOf(song.getSongType())).body(songFile);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateSong(@PathVariable int id, @RequestPart Song song, @RequestPart MultipartFile imageFile) {

        Song song1 = null;
        try {
            song1 = service.updateSong(id, song, imageFile);
            logger.info("song details updated succesfully");
        } catch (IOException e) {
        	logger.error("failed to update the song details");
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }
        if (song1 != null) {
            return new ResponseEntity<>("updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }
    }
   //Api for add to favourites
    @PostMapping("/product/Fav/{songId}")
    public ResponseEntity<Favourites> addToFavourites(@PathVariable Integer songId)
    {
    
    	return new ResponseEntity<>(service.addTofavourite( songId), HttpStatus.OK);
		
    	
    }
    
    @GetMapping("/product/favourite")
    public List<Favourites> favourite()
    {
    	return service.favourites();
    }
   
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable int id) {
        Song product = service.getSongById(id);
        if (product != null) {
            service.deleteSong(id);
            logger.info("Song deleted succesfully");
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
        	logger.error("song details not found");
            return new ResponseEntity<>("Song not found", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Song>> searchSongs(@RequestParam String keyword) {

        List<Song> songs = service.searchSongs(keyword);
        System.out.println("searching with " + keyword);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
    
    
    
    

}