package de.hsba.bi.Survey.web.survey;

import de.hsba.bi.Survey.survey.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/survey/")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("surveyAll",surveyService.findAllSurvey());
        model.addAttribute("allAnswers", surveyService.findAllAnswers());
        model.addAttribute("allQuestions", surveyService.findAllQuestions());
        return "index";
    }

}

