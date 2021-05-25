package stud.team.pwsbackend.service;


import stud.team.pwsbackend.dto.InsertVideosDto;

import java.util.List;

public interface InsertVideosService {

    List<InsertVideosDto> getAllInsertVideos();

    void deleteAllInsertVideos();

    InsertVideosDto getInsertVideosById(Long insertVideosId);

    InsertVideosDto addInsertVideos(InsertVideosDto insertVideosDto);

    void deleteInsertVideosById(Long insertVideosId);
}
