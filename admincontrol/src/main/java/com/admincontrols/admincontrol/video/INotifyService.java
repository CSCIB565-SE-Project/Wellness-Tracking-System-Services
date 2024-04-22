package com.admincontrols.admincontrol.video;
import java.util.List;
import java.util.Optional;

public interface INotifyService {
    List<Video> getUnApprovedVideos();
    Optional<Video> getVideoById(String id);
    boolean saveApprovedRequest(Video video);
    boolean rejectRequest(String id);
}