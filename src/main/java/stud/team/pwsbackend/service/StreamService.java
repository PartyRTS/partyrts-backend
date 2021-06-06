package stud.team.pwsbackend.service;

import stud.team.pwsbackend.dto.CategoryDto;
import stud.team.pwsbackend.dto.MessageDto;
import stud.team.pwsbackend.dto.StreamDto;
import stud.team.pwsbackend.dto.VoteDto;

import java.util.List;

public interface StreamService {

    List<StreamDto> getAllStream();

    void deleteAllStream();

    StreamDto getStreamById(Long streamId);

    StreamDto addStream(StreamDto streamDto);

    void deleteStreamById(Long streamId);

    List<MessageDto> getAllMessageByStream(Long streamId);

    List<CategoryDto> getAllCategoryByStream(Long streamId);

    List<VoteDto> getAllVoteByStream(Long streamId);

    void addVoteToStream(Long streamId, Long voteId) throws Exception;

}
