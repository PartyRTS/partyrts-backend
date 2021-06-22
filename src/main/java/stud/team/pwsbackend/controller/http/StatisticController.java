package stud.team.pwsbackend.controller.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stud.team.pwsbackend.service.impl.StatisticService;

import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/statistic",
        produces = "application/json")
@Slf4j
public class StatisticController {

    private StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/registerUser")
    public Map<Long,String> getUsersByRegisterDate(){
        return statisticService.getUsersByRegisterDate();
    }

    @GetMapping("/usersOnStream")
    public Map<Long,String> getUsersOnStream(){
        return statisticService.getUsersOnStream();
    }
}
