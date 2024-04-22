package com.admincontrols.admincontrol.video;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotifyService implements INotifyService{
    private final VideoRepository videoRepository;

    @Override
    public List<Video> getUnApprovedVideos(){
        List<Video> videos = videoRepository.findByIsApprovedFalse();
        return videos;
    }

    @Override
    public Optional<Video> getVideoById(String id){
        Optional<Video> video = videoRepository.findById(id);
        return video;
    }

    @Override
    public boolean saveApprovedRequest(Video video){
        try{
            video.setApproved(true);
            return true;
        }
        catch(Exception exception){
            return false;
        }
    }

    @Override
    public boolean rejectRequest(String id){
        try{
            videoRepository.deleteById(id);
            return true;
        }
        catch(Exception exception){
            return false;
        }
    }
}

