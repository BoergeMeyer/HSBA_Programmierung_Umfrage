package de.hsba.bi.Survey.web.result;

import de.hsba.bi.Survey.result.Result;
import de.hsba.bi.Survey.result.ResultService;
import de.hsba.bi.Survey.survey.Survey;
import de.hsba.bi.Survey.survey.SurveyService;
import de.hsba.bi.Survey.user.User;
import de.hsba.bi.Survey.user.UserService;
import de.hsba.bi.Survey.web.survey.SurveyFormConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ResultController {

    private final SurveyService surveyService;
    private final UserService userService;
    private final SurveyFormConverter formConverter;
    private final ResultService resultService;


    @GetMapping(path = "/voting/{sid}")
    public String Startvote(Model model, @PathVariable("sid") Long sid) {
        System.out.println(sid);
        surveyService.findSurveyById(sid).get(0).getQuestions();
        model.addAttribute("getSelectedSurvey", surveyService.findSurveyById(sid));
        model.addAttribute("getQuestions",surveyService.findSurveyById(sid).get(0).getQuestions().get(0));
        System.out.println("Question index: " +
        surveyService.findSurveyById(sid).get(0).getQuestions().indexOf(surveyService.findSurveyById(sid).get(0).getQuestions().get(0))
        );
        return "result/voting";
    }

    @PostMapping("vote")
    public String continueVote(Model model, @RequestParam("answer") Long aid, @RequestParam("index") Long i){
        if(i != 0){
            List<Survey> survey = surveyService.findSurveyByAnswerId(aid);
            Long sid = surveyService.findSurveyByAnswerId(aid).get(0).getId();
            Long qid = surveyService.findQuestionByAnswerId(aid).get(0).getId();

            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = principal instanceof UserDetails ? userService.returnUserByName(((UserDetails) principal).getUsername()) : null;

            Result result = new Result(sid,qid,aid,user);
            resultService.save(result);
            System.out.println("Aktuelle Results:");
            resultService.findAll().forEach(
                    res -> System.out.println(res.getSurveyID() + " " + res.getQuestionID() + " " + res.getSelectedAnswerID() + " " + res.getUser().getName())
            );
            int numberOfQuestions = survey.get(0).getQuestions().size() - 1;
            int currentIndex =  survey.get(0).getQuestions().indexOf(
                                    surveyService.findQuestionByAnswerId(aid).get(0)
                                );
            currentIndex++;
            model.addAttribute("getSelectedSurvey",survey.get(0));
            if(currentIndex <= numberOfQuestions){
                model.addAttribute("getQuestions",survey.get(0).getQuestions().get(currentIndex));
            }
            return (currentIndex <= numberOfQuestions) ? "result/voting" : "result/return";
        }
        System.out.println("Index");
        return "index";
    }

    @GetMapping(path = "/result/{sid}")
    public String getResults(Model model, @PathVariable("sid") Long sid){
        model.addAttribute("getSurveyResult", surveyService.findSurveyById(sid));
        model.addAttribute("result",resultService);
        return "result/result";
    }

}

