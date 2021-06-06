package stud.team.pwsbackend.controller.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import stud.team.pwsbackend.dto.InsertVideosDto;
import stud.team.pwsbackend.service.InsertVideosService;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/insertVideos",
        produces = "application/json")
@Slf4j
public class InsertVideosController {

    private final InsertVideosService insertVideosService;

    public InsertVideosController(InsertVideosService insertVideosService) {
        this.insertVideosService = insertVideosService;
    }

    @GetMapping
    public List<InsertVideosDto> getAllInsertVideos() {
        return insertVideosService.getAllInsertVideos();
    }

    @DeleteMapping
    public void deleteAllInsertVideos() {
        insertVideosService.deleteAllInsertVideos();
    }

    @GetMapping("/{insertVideosId}")
    public InsertVideosDto getInsertVideosById(@PathVariable Long insertVideosId) {
        return insertVideosService.getInsertVideosById(insertVideosId);
    }

    @PostMapping
    public InsertVideosDto addInsertVideos(@RequestBody InsertVideosDto insertVideosDto) {
        return insertVideosService.addInsertVideos(insertVideosDto);
    }

    @DeleteMapping("/{insertVideosId}")
    public void deleteInsertVideosById(@PathVariable Long insertVideosId) {
        insertVideosService.deleteInsertVideosById(insertVideosId);
    }
}
