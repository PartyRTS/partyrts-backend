package stud.team.pwsbackend.mapper;

import org.mapstruct.*;
import stud.team.pwsbackend.domain.VoteAdd;
import stud.team.pwsbackend.dto.VoteAddDto;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface VoteAddMapper {

    VoteAdd dtoToVoteAdd(VoteAddDto voteAddDto);

    @Mappings({
            @Mapping(target="idIVote", source="voteAdd.vote.idVote"),
            @Mapping(target="idAddVideo", source="voteAdd.addVideo.idVideo")
    })
    VoteAddDto voteAddToDto(VoteAdd voteAdd);

    List<VoteAdd> listDtoToListVoteAdd(List<VoteAddDto> voteAddDtoList);

    List<VoteAddDto> listVoteAddToListDto(List<VoteAdd> voteAddList);
}
