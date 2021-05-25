package stud.team.pwsbackend.controller.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import stud.team.pwsbackend.domain.Category;
import stud.team.pwsbackend.domain.Message;
import stud.team.pwsbackend.domain.Stream;
import stud.team.pwsbackend.domain.Vote;
import stud.team.pwsbackend.dto.CategoryDto;
import stud.team.pwsbackend.dto.MessageDto;
import stud.team.pwsbackend.dto.StreamDto;
import stud.team.pwsbackend.dto.VoteDto;
import stud.team.pwsbackend.service.StreamService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path ="api/v1/streams",
        produces = "application/json")
@Slf4j
public class StreamController {

    StreamService streamService;

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
    public StreamDto addStream(@RequestBody StreamDto streamDto) {
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
        return  streamService.getAllVoteByStream(streamId);
    }

    @PostMapping("/{streamId}/votes/{voteId}")
    public void addVoteToStream(@PathVariable Long streamId,@PathVariable  Long voteId)  throws Exception {
        streamService.addVoteToStream(streamId,voteId);
    }
}
