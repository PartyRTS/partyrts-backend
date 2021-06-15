package stud.team.pwsbackend.mapper;

import org.mapstruct.*;
import stud.team.pwsbackend.domain.Vote;
import stud.team.pwsbackend.dto.VoteDto;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface VoteMapper {

    Vote dtoToVote(VoteDto voteDto);

    @Mappings({
            @Mapping(target="idStream", source="vote.stream.idStream"),
            @Mapping(target="idVoteAdds", source="vote.voteAdds.idVote"),
            @Mapping(target="idVoteSkips", source="vote.voteSkips.idVote")
    })
    VoteDto voteToDto(Vote vote);

    List<Vote> listDtoToListVote(List<VoteDto> voteDtoList);

    List<VoteDto> listVoteToListDto(List<Vote> voteList);
}
