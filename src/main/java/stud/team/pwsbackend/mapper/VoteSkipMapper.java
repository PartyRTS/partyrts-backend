package stud.team.pwsbackend.mapper;

import org.mapstruct.*;
import stud.team.pwsbackend.domain.VoteSkip;
import stud.team.pwsbackend.dto.VoteSkipDto;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface VoteSkipMapper {

    VoteSkip dtoToVoteSkip(VoteSkipDto voteSkipDto);

    @Mappings({
            @Mapping(target="idIVote", source="voteSkip.vote.idVote")
    })
    VoteSkipDto voteSkipToDto(VoteSkip voteSkip);

    List<VoteSkip> listDtoToListVoteSkip(List<VoteSkipDto> voteSkipDtoList);

    List<VoteSkipDto> listVoteSkipToListDto(List<VoteSkip> voteSkipList);
}
