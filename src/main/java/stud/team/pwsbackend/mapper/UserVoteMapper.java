package stud.team.pwsbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import stud.team.pwsbackend.domain.UserVote;
import stud.team.pwsbackend.dto.UserVoteDto;

import java.util.List;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface UserVoteMapper {

    UserVote dtoToUserVote(UserVoteDto userVoteDto);

    UserVoteDto userVoteToDto(UserVote userVote);

    List<UserVote> listDtoToListUserVote(List<UserVoteDto> userVoteDtoList);

    List<UserVoteDto> listUserVoteToListDto(List<UserVote> userVoteList);
}
