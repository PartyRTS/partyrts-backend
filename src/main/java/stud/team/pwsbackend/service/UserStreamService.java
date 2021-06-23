package stud.team.pwsbackend.service;

import stud.team.pwsbackend.dto.StreamDto;

import java.util.List;

public interface UserStreamService {
    List<StreamDto> getAllStreams(long userId);
}
