package stud.team.pwsbackend.mapper;

import org.mapstruct.*;
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

    @Mappings({
            @Mapping(target="idVote", source="userVote.vote.idVote"),
            @Mapping(target="idUser", source="userVote.user.idUser")
    })
    UserVoteDto userVoteToDto(UserVote userVote);

    List<UserVote> listDtoToListUserVote(List<UserVoteDto> userVoteDtoList);

    List<UserVoteDto> listUserVoteToListDto(List<UserVote> userVoteList);
}
