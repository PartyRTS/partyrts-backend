package utils;

import stud.team.pwsbackend.domain.InsertVideos;
import stud.team.pwsbackend.domain.Video;
import stud.team.pwsbackend.domain.VideoHasPlaylist;
import stud.team.pwsbackend.dto.VideoWithNumb;
import stud.team.pwsbackend.mapper.VideoMapper;
import stud.team.pwsbackend.repository.VideoRepository;

import java.util.ArrayList;
import java.util.List;

public class FullPlaylistUtil {

    private List<InsertVideos> insertVideos;
    private List<VideoHasPlaylist> videos;
    private VideoMapper videoMapper;
    private Integer nextNumb;
    private Integer currentNumb;
    private List<VideoWithNumb> result;

    public FullPlaylistUtil(List<InsertVideos> insertVideos, List<VideoHasPlaylist> videos, VideoMapper videoMapper ) {
        this.insertVideos = insertVideos;
        this.videos = videos;
        this.videoMapper = videoMapper;
    }

    public List<VideoWithNumb> getFullPlaylist(){
        result = new ArrayList<>();
        nextNumb = null;
        currentNumb = null;
        while(containsNumberInInserts() || containsNumberInPlaylist() ){
        }
        return result;
    }

    public boolean containsNumberInPlaylist() {
        for(VideoHasPlaylist videoInPl : videos){
            if(currentNumb == null || (videoInPl.getNumberVideo().equals(nextNumb))) {
                currentNumb = videoInPl.getNumberVideo();
                VideoWithNumb videoWithNumb = videoMapper.videoToNumbVideo(videoInPl.getVideo());
                videoWithNumb.setNumberVideo(currentNumb);
                result.add(videoWithNumb);
                nextNumb = videoInPl.getNumberVideo() + 1;
                return true;
            }
        }
        return false;
    }

    public boolean containsNumberInInserts() {
        for(InsertVideos insVideo : insertVideos){
            if((currentNumb == null && insVideo.getNumberPrevVideo() == null) || (insVideo.getNumberPrevVideo() != null && insVideo.getNumberPrevVideo().equals(currentNumb))){
                currentNumb = insVideo.getNumberCurrentVideo();
                nextNumb = insVideo.getNumberNextVideo();
                if(insVideo.getCurrentVideo() != null){
                    VideoWithNumb numbVideo = videoMapper.videoToNumbVideo(insVideo.getCurrentVideo());
                    numbVideo.setNumberVideo(currentNumb);
                    result.add(numbVideo);
                }
                return true;
            }
        }
        return false;
    }
}
