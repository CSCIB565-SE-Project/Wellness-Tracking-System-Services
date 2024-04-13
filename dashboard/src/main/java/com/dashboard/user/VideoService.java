package com.dashboard.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashboard.exception.NotFoundException;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public Optional<Video> getVideoById(Long id) {
        return videoRepository.findById(id);
    }

    public Video createVideo(Video video) {
        return videoRepository.save(video);
    }

    public Video updateVideo(Long id, Video updatedVideo) {
        // Check if the video exists
        if (!videoRepository.existsById(id)) {
            throw new NotFoundException("Video not found");
        }
        updatedVideo.setId(id); // Ensure the ID is set
        return videoRepository.save(updatedVideo);
    }

    public void deleteVideo(Long id) {
        // Check if the video exists
        if (!videoRepository.existsById(id)) {
            throw new NotFoundException("Video not found");
        }
        videoRepository.deleteById(id);
    }
}

