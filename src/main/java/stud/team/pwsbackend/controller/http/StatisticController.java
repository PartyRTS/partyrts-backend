package stud.team.pwsbackend.controller.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stud.team.pwsbackend.service.impl.StatisticService;

import java.math.BigInteger;
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
    public Map<String,Integer> getUsersByRegisterDate(){
        return statisticService.getUsersByRegisterDate();
    }

    @GetMapping("/usersOnStream")
    public Map<String,Integer> getUsersOnStream(){
        return statisticService.getUsersOnStream();
    }

    @GetMapping("/countStreams")
    public Map<String,Integer> getCountStreams(){
        return statisticService.getCountStreams();
    }

    @GetMapping("/votesOnStream")
    public Map<String,Double> getVotesOnStream(){
        return statisticService.getVotesOnStream();
    }
}
