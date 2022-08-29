package de.hsba.bi.Survey.web.survey;

import de.hsba.bi.Survey.survey.SurveyService;
import de.hsba.bi.Survey.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/surveys/")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;
    private final UserService userService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("surveyAll",surveyService.findAllSurvey());
        return "surveys/index";
    }

    @GetMapping(path = "mysurvey/{id}")
    public String getSurveyToShow(@PathVariable Long id, Model model){
        model.addAttribute("getSurveyById", surveyService.findSurveyByUserId(id));
        return "surveys/listofmysurveys";
    }

    @GetMapping("edit/{id}")
    public String getSurveyToEdit(@PathVariable Long id,Model model){
        model.addAttribute("getSurveyById", surveyService.findSurveyByUserId(id));
        return "surveys/editsurvey";
    }



}
