package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.artist.infrastructure.controller;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.artist.application.service.ArtistService;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.artist.infrastructure.controller.dto.output.ArtistAlbumsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.artist.infrastructure.controller.dto.input.ArtistInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.artist.infrastructure.controller.dto.output.ArtistOutputDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ArtistController {
    @Autowired
    private ArtistService artistService;

    @PostMapping("/artists")
    public ResponseEntity<ArtistOutputDto> addArtist(@Valid @RequestBody ArtistInputDto artistInputDto) {
        return ResponseEntity.ok().body(artistService.addArtist(artistInputDto));
    }

    @GetMapping("/artists")
    public List<ArtistOutputDto> getAllArtists(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        return artistService.getAllArtists(pageable);
    }

    @GetMapping("/artists/{id}")
    public ResponseEntity<ArtistOutputDto> getArtistById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(artistService.getArtistById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/artists/{id}")
    public ResponseEntity<ArtistOutputDto> updateArtist(
            @PathVariable Long id,
            @RequestBody ArtistInputDto artistInputDto
    ) {
        try {
            ArtistOutputDto updated = artistService.updateArtist(id, artistInputDto);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/artists/{id}")
    public ResponseEntity<String> deleteArtistById(@PathVariable Long id) {
        try {
            artistService.deleteArtistById(id);
            return ResponseEntity.ok().body("Artist with id: " + id + " was deleted");
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

    @PostMapping("/artists/album")
    public ResponseEntity<String> addAlbumtoArtist(@RequestParam Long album_id, @RequestParam Long artist_id) {
        try {
            artistService.addAlbumToArtist(album_id, artist_id);
            return ResponseEntity.ok().body("Album with id: " + album_id + " added successfully to Artist with id: " + artist_id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong: " + e.getMessage());
        }
    }

    @GetMapping("/artists/albums")
    public List<ArtistAlbumsDto> getAllArtistAlbums(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        return artistService.getAllArtistAlbums(pageable);
    }

    @GetMapping("/artists/albums/{id}")
    public ResponseEntity<ArtistAlbumsDto> getArtistAlbumsById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(artistService.getArtistAlbumsById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}