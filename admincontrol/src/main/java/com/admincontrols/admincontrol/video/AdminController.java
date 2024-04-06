package com.admincontrols.admincontrol.video;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    public final NotifyService notifyService;

    @GetMapping("/getVideos")
    public List<Video> getAllVideos() {
        return notifyService.getUnApprovedVideos();
    }

    @PostMapping("/approve")
    public ResponseEntity<String> approveContent(@RequestBody VideoRequest videoRequest) {
        Optional<Video> optionalVideo = notifyService.getVideoById(videoRequest.id());
        Video video = optionalVideo.orElseThrow(() -> new RuntimeException("Video not found"));
        Boolean result = notifyService.saveApprovedRequest(video);
        if(result){
            return ResponseEntity.ok("Content Approved");
        }            
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to Approve Content");
    }

    @PostMapping("/reject")
    public ResponseEntity<String> rejectContent(@RequestBody VideoRequest videoRequest) {
        Optional<Video> optionalVideo = notifyService.getVideoById(videoRequest.id());
        Video video = optionalVideo.orElseThrow(() -> new RuntimeException("Video not found"));
        Boolean result = notifyService.rejectRequest(video.getId());
        if(result){
            return ResponseEntity.ok("Content Rejected");
        }            
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to Reject Content");
    }
    
}
