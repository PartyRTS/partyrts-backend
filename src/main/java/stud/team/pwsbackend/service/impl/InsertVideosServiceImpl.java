package stud.team.pwsbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.domain.Category;
import stud.team.pwsbackend.domain.InsertVideos;
import stud.team.pwsbackend.dto.InsertVideosDto;
import stud.team.pwsbackend.mapper.InsertVideosMapper;
import stud.team.pwsbackend.repository.InsertVideosRepository;
import stud.team.pwsbackend.service.InsertVideosService;

import java.util.List;
import java.util.Optional;

@Service
public class InsertVideosServiceImpl implements InsertVideosService {

    InsertVideosRepository insertVideosRepository;
    InsertVideosMapper insertVideosMapper;

    @Override
    public List<InsertVideosDto> getAllInsertVideos() {
        List<InsertVideos> insertVideos = insertVideosRepository.findAll();
        return insertVideosMapper.listInsertVideosToListDto(insertVideos);
    }

    @Override
    public void deleteAllInsertVideos() {
        insertVideosRepository.deleteAll();
    }

    @Override
    public InsertVideosDto getInsertVideosById(Long insertVideosId) {
        Optional<InsertVideos> insertVideo = insertVideosRepository.findById(insertVideosId);
        return insertVideo.map(insertVideosMapper::insertVideosToDto).orElse(null);
    }

    @Override
    public InsertVideosDto addInsertVideos(InsertVideosDto insertVideosDto) {
        InsertVideos insertVideo = insertVideosMapper.dtoToInsertVideos(insertVideosDto);
        insertVideo = insertVideosRepository.save(insertVideo);
        return insertVideosMapper.insertVideosToDto(insertVideo);
    }

    @Override
    public void deleteInsertVideosById(Long insertVideosId) {
        insertVideosRepository.deleteById(insertVideosId);
    }

    @Autowired
    public void setInsertVideosRepository(InsertVideosRepository insertVideosRepository) {
        this.insertVideosRepository = insertVideosRepository;
    }

    @Autowired
    public void setInsertVideosMapper(InsertVideosMapper insertVideosMapper) {
        this.insertVideosMapper = insertVideosMapper;
    }
}
