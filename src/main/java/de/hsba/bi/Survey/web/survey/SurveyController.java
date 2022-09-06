package de.hsba.bi.Survey.web.survey;

import de.hsba.bi.Survey.survey.Survey;
import de.hsba.bi.Survey.survey.SurveyService;
import de.hsba.bi.Survey.user.User;
import de.hsba.bi.Survey.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/surveys/")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;
    private final UserService userService;
    private final SurveyFormConverter formConverter;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("surveyAll",surveyService.findAllSurvey());
        return "surveys/index";
    }

    @GetMapping(path = "mysurvey")
    public String getSurveyToShow(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : null;
        model.addAttribute("getSurveyById", surveyService.findSurveyByUsername(name));
        return "surveys/listofmysurveys";
    }

    @PostMapping("createSurvey")
    public String createSurvey(@ModelAttribute("survey") CreateSurveyForm form, BindingResult result, Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = principal instanceof UserDetails ? userService.returnUserByName(((UserDetails) principal).getUsername()) : null;
        Survey survey = formConverter.create(new Survey(), form, user);
        surveyService.saveSurvey(survey);
        surveyService.findAllSurvey().forEach(surveys -> System.out.println(surveys.getTitle()));
        return "redirect:" + "survey";
        //return "redirect:" + "mysurvey";
    }

    /* REWORK */

    @GetMapping("edit")
    public String edit(@RequestParam Long sid, Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : null;
        model.addAttribute("getSurveyByIdAndUser", surveyService.findSurveyByIdAndUsername(name,sid));
        return "surveys/edit";
    }

    @GetMapping("delete")
    public String delete(@RequestParam Long sid, @RequestParam Long e, @RequestParam String type){
        if(type != null){
            switch (type) {
                case("survey"):
                    surveyService.deleteSurvey(e);
                    break;
                case("question"):
                    surveyService.deleteQuestion(e);
                    break;
                case("answer"):
                    surveyService.deleteAnswer(e);
                    break;
            }
        }

        return (type == "survey") ? ":redirect" + "survey" : "redirect:" + "edit?sid=" + Long.toString(sid);
    }


    /* FURTHER DEVELOPMENT */

    @GetMapping(path = "survey")
    public String getMySurvey(@ModelAttribute("question") CreateQuestionForm form, Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : null;
        model.addAttribute("getSurveyById", surveyService.findSurveyByUsername(name));
        return "surveys/mysurvey";
    }

}
