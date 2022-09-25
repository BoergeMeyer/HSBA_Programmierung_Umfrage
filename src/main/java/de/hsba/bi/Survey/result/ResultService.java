package de.hsba.bi.Survey.result;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;

    public Result save(Result result){
        return resultRepository.save(result);
    }

    public List<Result> findAll(){
        return resultRepository.findAll();
    }

}