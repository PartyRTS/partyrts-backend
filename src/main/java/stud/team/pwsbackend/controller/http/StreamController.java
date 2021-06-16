package stud.team.pwsbackend.controller.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import stud.team.pwsbackend.dto.*;
import stud.team.pwsbackend.service.StreamService;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/streams",
        produces = "application/json")
@Slf4j
public class StreamController {

    private final StreamService streamService;

    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @GetMapping
    public List<StreamDto> getAllStream() {
        return streamService.getAllStream();
    }

    @DeleteMapping
    public void deleteAllStream() {
        streamService.deleteAllStream();
    }

    @GetMapping("/{streamId}")
    public StreamDto getStreamById(@PathVariable Long streamId) {
        return streamService.getStreamById(streamId);
    }

    @PostMapping
    public StreamDto addStream(@RequestBody StreamDto streamDto) throws Exception {
        return streamService.addStream(streamDto);
    }

    @DeleteMapping("/{streamId}/messages")
    public void deleteStreamById(@PathVariable Long streamId) {
        streamService.deleteStreamById(streamId);
    }

    public List<MessageDto> getAllMessageByStream(@PathVariable Long streamId) {
        return streamService.getAllMessageByStream(streamId);
    }

    @GetMapping("/{streamId}/categories")
    public List<CategoryDto> getAllCategoryByStream(@PathVariable Long streamId) {
        return streamService.getAllCategoryByStream(streamId);
    }

    @GetMapping("/{streamId}/votes")
    public List<VoteDto> getAllVoteByStream(@PathVariable Long streamId) {
        return streamService.getAllVoteByStream(streamId);
    }

    @PostMapping("/{streamId}/addVoteAdd")
    public void addVoteAddToStream(@PathVariable Long streamId,@RequestBody VoteAddDto voteAddDto) throws Exception {
        streamService.addVoteAddToStream(streamId,voteAddDto);
    }

    @PostMapping("/{streamId}/addSkipVote")
    public void addVoteSkipToStream(@PathVariable Long streamId,@RequestBody VoteSkipDto voteSkipDto) throws Exception {
        streamService.addVoteSkipToStream(streamId,voteSkipDto);
    }

    @GetMapping("/{streamId}/voteAdds")
    public List<VoteAddDto> getAllVoteAddByStream(@PathVariable Long streamId) throws Exception {
        return streamService.getAllVoteAddByStream(streamId);
    }

    @GetMapping("/{streamId}/voteSkips")
    public List<VoteSkipDto> getAllVoteSkipByStream(@PathVariable Long streamId) throws Exception {
        return streamService.getAllVoteSkipByStream(streamId);
    }

    @PostMapping("/{streamId}/addCategories")
    public void addCategoriesToStream(@PathVariable Long streamId,@RequestBody List<Long> categoriesId) throws Exception {
        streamService.addCategoriesToStream(streamId,categoriesId);
    }

    @PostMapping("/{streamId}/addWatcher/{userId}")
    public void addUserToStream(@PathVariable Long streamId,@PathVariable  Long userId) throws Exception {
        streamService.addUserToStream(streamId,userId);
    }

    @PostMapping("/{streamId}/addInsertVideo")
    public void addInsertVideoToStream(@PathVariable Long streamId,@RequestBody InsertVideosDto insVideoDto) throws Exception {
        streamService.addInsertVideoToStream(streamId,insVideoDto);
    }
}
