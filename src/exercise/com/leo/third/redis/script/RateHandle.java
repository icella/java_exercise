package exercise.com.leo.third.redis.script;


import org.springframework.stereotype.Service;


@Service
public interface RateHandle {

    void insert(String pname, long timestamp, Rate rate, boolean increment) throws Exception;

    boolean isLimit(String pname, long current_time, int number, Rate rate) throws Exception;
    
    boolean acquire(String pname, long timestamp, int limitNumber, Rate rate) throws Exception;
}
