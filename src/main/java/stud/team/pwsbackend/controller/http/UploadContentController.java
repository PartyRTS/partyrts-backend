package stud.team.pwsbackend.controller.http;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stud.team.pwsbackend.exception.user.UserNotFoundException;
import stud.team.pwsbackend.service.impl.UploadContentService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/aws")
public class UploadContentController {
    private final UploadContentService uploadContentService;

    public UploadContentController(UploadContentService uploadContentService) {
        this.uploadContentService = uploadContentService;
    }

    @PostMapping(path = "/{userId}/logo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateUserLogo(@PathVariable long userId, @RequestPart("file") MultipartFile file) throws IOException, UserNotFoundException {
        return uploadContentService.updateUserLogo(userId, file);
    }

    @PostMapping(path = "/{videoId}/preview", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadPreviewVideo(@PathVariable long videoId, @RequestPart("file") MultipartFile file) throws IOException {
        return uploadContentService.uploadPreviewVideo(videoId, file);
    }

    @PostMapping(path = "/{videoId}/video", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadVideo(@PathVariable long videoId,@RequestPart("file") MultipartFile file) throws IOException {
        return uploadContentService.uploadVideo(videoId,file);
    }
}
