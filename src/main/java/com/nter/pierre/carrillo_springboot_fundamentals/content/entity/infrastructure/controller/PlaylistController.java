package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.application.PlaylistService;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.PlaylistSongsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.input.PlaylistInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.output.PlaylistOutputDto;
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
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/playlists")
    public ResponseEntity<PlaylistOutputDto> addPlaylist(@Valid @RequestBody PlaylistInputDto playlistInputDto) {
        return ResponseEntity.ok().body(playlistService.addPlaylist(playlistInputDto));
    }

    @GetMapping("/playlists")
    public List<PlaylistOutputDto> getAllPlaylists(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        return playlistService.getAllPlaylists(pageable);
    }

    @GetMapping("/playlists/{id}")
    public ResponseEntity<PlaylistOutputDto> getPlaylistById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(playlistService.getPlaylistById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/playlists/{id}")
    public ResponseEntity<PlaylistOutputDto> updatePlaylist(
            @PathVariable Long id,
            @RequestBody PlaylistInputDto playlistInputDto
    ) {
        try{
            PlaylistOutputDto updated = playlistService.updatePlaylist(id, playlistInputDto);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/playlists/{id}")
    public ResponseEntity<String> deletePlaylistById(@PathVariable Long id) {
        try {
            playlistService.deletePlaylistById(id);
            return ResponseEntity.ok().body("Playlist with id: " + id + " was deleted");
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

    @GetMapping("/playlists/songs")
    public LinkedHashSet<PlaylistSongsDto> getAllPlaylistSongs(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        return playlistService.getAllPlaylistSongs(pageable);
    }

    @GetMapping("/playlists/songs/{id}")
    public ResponseEntity<PlaylistSongsDto> getPlaylistSongsById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(playlistService.getPlaylistSongsById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}