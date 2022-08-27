package de.hsba.bi.Survey.web;

import de.hsba.bi.Survey.survey.SurveyService;
import de.hsba.bi.Survey.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

    private final SurveyService surveyService;
    private final UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("countSurvey",surveyService.returnNumberOfSurveys());
        model.addAttribute("countUsers",userService.returnNumberOfUsers());
        return "index";
    }
}
