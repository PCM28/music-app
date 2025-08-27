package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.album.infrastructure.controller;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.album.application.service.AlbumService;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.album.infrastructure.controller.dto.output.AlbumSongsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.album.infrastructure.controller.dto.input.AlbumInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.album.infrastructure.controller.dto.output.AlbumOutputDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping("/albums")
    public ResponseEntity<AlbumOutputDto> addAlbum(@Valid @RequestBody AlbumInputDto albumInputDto) {
        return ResponseEntity.ok().body(albumService.addAlbum(albumInputDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/albums")
    public List<AlbumOutputDto> getAllAlbums(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        return albumService.getAllAlbums(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/albums/{id}")
    public ResponseEntity<AlbumOutputDto> getAlbumById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(albumService.getAlbumById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/albums/{id}")
    public ResponseEntity<AlbumOutputDto> updateAlbum(
            @PathVariable Long id,
            @RequestBody AlbumInputDto albumInputDto
    ) {
        try {
            AlbumOutputDto updated = albumService.updateAlbum(id, albumInputDto);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/albums/{id}")
    public ResponseEntity<String> deleteAlbumById(@PathVariable Long id) {
        try {
            albumService.deleteAlbumById(id);
            return ResponseEntity.ok().body("Album with id: " + id + " was deleted");
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

    @PostMapping("/albums/song")
    public ResponseEntity<String> addSongToAlbum(@RequestParam Long song_id, @RequestParam Long album_id) {
        try {
            albumService.addSongToAlbum(song_id, album_id);
            return ResponseEntity.ok().body("Song with id: " + song_id + " added successfully to Album with id: " + album_id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong: " + e.getMessage());
        }
    }

    @GetMapping("/albums/songs")
    public List<AlbumSongsDto> getAllAlbumSongs(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        return albumService.getAllAlbumSongs(pageable);
    }

    @GetMapping("/albums/songs/{id}")
    public ResponseEntity<AlbumSongsDto> getAlbumSongsById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(albumService.getAlbumSongsById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

}