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

    public String returnResult(Long qid, Long aid){

        int sum = resultRepository.findAllResultByQuestionID(qid).size();
        int count = resultRepository.findAllResultByAnswerID(aid).size();
        System.out.println("SID: " + qid + " - AID: " + aid + " - SUM: " + sum + "- COUNT:" + count);

        return count/sum + "%";
    }

    public String calculateResult(Long qid, Long aid){
        int countQID = resultRepository.countQuestionID(qid);
        int countAID = resultRepository.countAnswerID(aid);
        System.out.println(countAID + " " + countQID);
        Double calc = (Double.valueOf(countAID) / Double.valueOf(countQID))*100;
        System.out.println(calc);
        return " - Ergebnis: "+ String.format("%.2f", calc) + "%";
    }

}