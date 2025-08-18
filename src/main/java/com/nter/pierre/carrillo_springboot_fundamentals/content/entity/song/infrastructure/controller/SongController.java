package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.song.infrastructure.controller;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.song.application.service.SongService;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.song.infrastructure.controller.dto.output.SongPlaylistsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.song.infrastructure.controller.dto.input.SongInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.song.infrastructure.controller.dto.output.SongOutputDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping("/songs")
    public ResponseEntity<SongOutputDto> addSong(@Valid @RequestBody SongInputDto songInputDto){
        return ResponseEntity.ok().body(songService.addSong(songInputDto));
    }

    @GetMapping("/songs")
    public List<SongOutputDto> getAllSongs(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        return songService.getAllSongs(pageable);
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<SongOutputDto> getSongById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(songService.getSongById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/songs/{id}")
    public ResponseEntity<SongOutputDto> updateSong(
            @PathVariable Long id,
            @RequestBody SongInputDto songInputDto
    ) {
        try {
            SongOutputDto updated = songService.updateSong(id, songInputDto);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/songs/{id}")
    public ResponseEntity<String> deleteSongById(@PathVariable Long id) {
        try {
            songService.deleteSongById(id);
            return ResponseEntity.ok().body("Song with id: " + id + " was deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String>
    handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @PostMapping("/songs/playlist")
    public ResponseEntity<String> addPlaylistToSong(@RequestParam Long song_id, @RequestParam Long playlist_id) {
        try {
            songService.addPlaylistToSong(playlist_id, song_id);
            return ResponseEntity.ok().body("Playlist with id: " + playlist_id + " was successfully added to Song with id: " + song_id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong: " + e.getMessage());
        }
    }

    @GetMapping("/songs/playlists")
    public LinkedHashSet<SongPlaylistsDto> getAllSongPlaylists(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        return songService.getAllSongPlaylists(pageable);
    }

    @GetMapping("/songs/playlists/{id}")
    public ResponseEntity<SongPlaylistsDto> getSongPlaylistsById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(songService.getSongPlaylistsById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}