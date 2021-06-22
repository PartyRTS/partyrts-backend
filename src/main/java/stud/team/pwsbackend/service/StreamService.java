package stud.team.pwsbackend.service;

import stud.team.pwsbackend.dto.*;

import java.util.List;

public interface StreamService {

    List<StreamDto> getAllStream();

    void deleteAllStream();

    StreamDto getStreamById(Long streamId);

    StreamDto addStream(StreamDto streamDto) throws Exception;

    void deleteStreamById(Long streamId);

    List<MessageDto> getAllMessageByStream(Long streamId);

    void setNextVideoStream(Long streamId) throws Exception;

    StreamDto setStopStream(Long streamId,boolean stop) throws Exception;

    MessageDto addMessageToStream(Long streamId, MessageDto messageDto);

    List<CategoryDto> getAllCategoryByStream(Long streamId);

    List<VoteDto> getAllVoteByStream(Long streamId);

    void addVoteAddToStream(Long streamId, VoteAddDto voteAddDto) throws Exception;

    void addVoteSkipToStream(Long streamId, VoteSkipDto voteSkipDto) throws Exception;

    List<VoteAddDto> getAllVoteAddByStream(Long streamId) throws Exception;

    List<VoteSkipDto> getAllVoteSkipByStream(Long streamId) throws Exception;

    void addCategoriesToStream(Long streamId,List<Long> categoriesId) throws Exception;

    void addUserToStream(Long streamId,Long userId) throws Exception;

    void addInsertVideoToStream(Long streamId,InsertVideosDto insVideoDto) throws Exception;

    List<StreamDto> findStreamsByTitle(String search);

    List<VideoWithNumb> getFullPlaylistByStream(Long streamId) throws Exception;

}
