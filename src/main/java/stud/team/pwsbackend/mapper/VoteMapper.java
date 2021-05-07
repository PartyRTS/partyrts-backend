package stud.team.pwsbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import stud.team.pwsbackend.domain.Vote;
import stud.team.pwsbackend.dto.VoteDto;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface VoteMapper {

    Vote dtoToVote(VoteDto voteDto);

    VoteDto voteToDto(Vote vote);

    List<Vote> listDtoToListVote(List<VoteDto> voteDtoList);

    List<VoteDto> listVoteToListDto(List<Vote> voteList);
}
