package com.schoolmanagement.configuration.streams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/streams")
@CrossOrigin(origins = "*")
public class StreamsController {

    private final StreamsService streamsService;

    @Autowired
    public StreamsController(StreamsService streamsService) {
        this.streamsService = streamsService;
    }

    // Create or Update Stream
    @PostMapping("/add")
    public ResponseEntity<StreamsEntity> createOrUpdateStream(@RequestBody StreamsEntity stream) {
        StreamsEntity savedStream = streamsService.saveStream(stream);
        return new ResponseEntity<>(savedStream, HttpStatus.CREATED);
    }

    // Get Stream by ID
    @GetMapping("/{id}")
    public ResponseEntity<StreamsEntity> getStreamById(@PathVariable Long id) {
        Optional<StreamsEntity> stream = streamsService.getStreamById(id);
        return stream.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get All Streams
    @GetMapping("/all")
    public ResponseEntity<List<StreamsEntity>> getAllStreams() {
        List<StreamsEntity> streams = streamsService.getAllStreams();
        return new ResponseEntity<>(streams, HttpStatus.OK);
    }

    // Delete Stream by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStream(@PathVariable Long id) {
        streamsService.deleteStream(id);
        return ResponseEntity.noContent().build();
    }
}
