package com.schoolmanagement.configuration.streams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StreamsService {

    private final StreamsRepository streamsRepository;

    @Autowired
    public StreamsService(StreamsRepository streamsRepository) {
        this.streamsRepository = streamsRepository;
    }

    // Create or Update Stream
    public StreamsEntity saveStream(StreamsEntity stream) {
        return streamsRepository.save(stream);
    }

    // Get Stream by ID
    public Optional<StreamsEntity> getStreamById(Long id) {
        return streamsRepository.findById(id);
    }

    // Get All Streams
    public List<StreamsEntity> getAllStreams() {
        return streamsRepository.findAll();
    }

    // Delete Stream by ID
    public void deleteStream(Long id) {
        streamsRepository.deleteById(id);
    }
}

