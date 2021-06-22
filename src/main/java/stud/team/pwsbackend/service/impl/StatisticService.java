package stud.team.pwsbackend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.repository.StreamRepository;
import stud.team.pwsbackend.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class StatisticService {

    private UserRepository userRepository;
    private StreamRepository streamRepository;

    public Map<Long,String> getUsersByRegisterDate(){
        List<Object[]> result = userRepository.findCountUserByRegisterDate();
        Map<Long,String> map = null;
        if(result != null && !result.isEmpty()){
            map = new HashMap<Long,String>();
            for (Object[] object : result) {
                map.put(((Long)object[0]),object[1].toString());
            }
        }
        return map;
    }

    public Map<Long,String> getUsersOnStream(){
        List<Object[]> result = streamRepository.findCountUserOnStream();
        Map<Long,String> map = null;
        if(result != null && !result.isEmpty()){
            map = new HashMap<Long,String>();
            for (Object[] object : result) {
                map.put(((Long)object[0]) - (Long)object[1],object[3].toString() + " unauth");
                map.put((Long)object[1],object[3].toString() + " auth");
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
