package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.application.GenreService;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.GenreArtistsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.input.GenreInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.output.GenreOutputDto;
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
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping("/genres")
    public ResponseEntity<GenreOutputDto> addGenre(@Valid @RequestBody GenreInputDto genreInputDto) {
        return ResponseEntity.ok().body(genreService.addGenre(genreInputDto));
    }

    @GetMapping("/genres")
    public List<GenreOutputDto> getAllGenres(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        return genreService.getAllGenres(pageable);
    }

    @GetMapping("/genres/{id}")
    public ResponseEntity<GenreOutputDto> getGenreById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(genreService.getGenreById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/genres/{id}")
    public ResponseEntity<GenreOutputDto> updateGenre(
            @PathVariable Long id,
            @RequestBody GenreInputDto genreInputDto
    ) {
        try {
            GenreOutputDto updated = genreService.updateGenre(id, genreInputDto);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/genres/{id}")
    public ResponseEntity<String> deleteGenreById(@PathVariable Long id) {
        try {
            genreService.deleteGenreById(id);
            return ResponseEntity.ok().body("Genre with id: " + id + " was deleted");
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

    @PostMapping("/genres/artist")
    public ResponseEntity<String> addArtistToGenre(@RequestParam Long artist_id, @RequestParam Long genre_id) {
        try {
            genreService.addArtistToGenre(artist_id, genre_id);
            return ResponseEntity.ok().body("Artist with id: " + artist_id + " added successfully to Genre with id: " + genre_id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong: " + e.getMessage());
        }
    }

    @GetMapping("/genres/artists")
    public List<GenreArtistsDto> getAllGenreArtists(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        return genreService.getAllGenreArtists(pageable);
    }

    @GetMapping("/genres/artists/{id}")
    public ResponseEntity<GenreArtistsDto> getGenreArtistsById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(genreService.getGenreArtistsById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}