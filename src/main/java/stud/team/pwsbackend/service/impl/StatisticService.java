package stud.team.pwsbackend.service.impl;

import com.amazonaws.services.dynamodbv2.xspec.S;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.repository.StreamRepository;
import stud.team.pwsbackend.repository.UserRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class StatisticService {

    private UserRepository userRepository;
    private StreamRepository streamRepository;

    public Map<String,Integer> getUsersByRegisterDate(){
        List<Object[]> result = userRepository.findCountUserByRegisterDate();
        Map<String,Integer> map = null;
        if(result != null && !result.isEmpty()){
            map = new HashMap<String,Integer>();
            for (Object[] object : result) {
                String date = "";
                if(object[1] != null){
                    date = object[1].toString();
                }
                map.put(date,((Long)object[0]).intValue());
            }
        }
        return map;
    }

    public Map<String,Integer> getUsersOnStream(){
        List<Object[]> result = streamRepository.findCountUserOnStream();
        Map<String,Integer> map = null;
        if(result != null && !result.isEmpty()){
            map = new HashMap<String,Integer>();
            for (Object[] object : result) {
                Integer fullCount = 1;
                if(object[0] != null){
                    fullCount = Integer.parseInt(object[0].toString());
                }
                Integer authCount = 1;
                if(object[1] != null){
                    authCount =Integer.parseInt(object[1].toString());
                }
                String date = "";
                if(object[2] != null){
                    date = object[2].toString();
                }
                map.put(date + " unauth",fullCount - authCount);
                map.put(date + " auth",authCount);
            }
        }
        return map;
    }

    public Map<String,Integer> getCountStreams(){
        List<Object[]> result = streamRepository.findCountStreams();
        Map<String,Integer> map = null;
        if(result != null && !result.isEmpty()){
            map = new HashMap<String,Integer>();
            for (Object[] object : result) {
                String date = "";
                if(object[1] != null){
                    date = object[1].toString();
                }
                map.put(date,((Long)object[0]).intValue());
            }
        }
        return map;
    }

    public Map<String,Double> getVotesOnStream(){
        List<Object[]> result = streamRepository.findCountVoteOnStream();
        Map<String,Double> map = null;
        if(result != null && !result.isEmpty()){
            map = new HashMap<String,Double>();
            for (Object[] object : result) {
                Integer voteCount = 1;
                if(object[0] != null){
                    voteCount = Integer.parseInt(object[0].toString());
                }
                Integer streamCount = 1;
                if(object[1] != null){
                    streamCount = Integer.parseInt(object[1].toString());
                }
                String date = "";
                if(object[2] != null){
                    date = object[2].toString();
                }
                map.put(date, (double) (voteCount/streamCount));
            }
        }
        return map;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setStreamRepository(StreamRepository streamRepository) {
        this.streamRepository = streamRepository;
    }
}
