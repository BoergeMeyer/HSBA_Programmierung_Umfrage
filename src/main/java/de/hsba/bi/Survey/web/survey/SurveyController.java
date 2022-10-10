package de.hsba.bi.Survey.web.survey;

import de.hsba.bi.Survey.result.ResultService;
import de.hsba.bi.Survey.survey.Answer;
import de.hsba.bi.Survey.survey.Question;
import de.hsba.bi.Survey.survey.Survey;
import de.hsba.bi.Survey.survey.SurveyService;
import de.hsba.bi.Survey.user.User;
import de.hsba.bi.Survey.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/surveys/")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService         surveyService;
    private final UserService           userService;
    private final SurveyFormConverter   formConverter;
    private final ResultService         resultService;

    @ModelAttribute("user")
    public String getUsername(){
        return userService.getUserDetails();
    }

    //Umfragenübersicht
    @GetMapping
    public String index(Model model) {
        model.addAttribute("surveyAll",surveyService.findAllSurveyNotLocked());
        model.addAttribute("surveyAllWithoutFromUser",surveyService.findAllSurveyNotFromUser(userService.getUserDetails()));
        model.addAttribute("HasVoted",resultService);
        return "surveys/index";
    }

    // Eigene Umfragen auflisten
    @GetMapping(path = "my")
    public String getMySurvey(@ModelAttribute("question") CreateQuestionForm form, Model model){
        model.addAttribute("getSurveyById", surveyService.findSurveyByUsername(userService.getUserDetails()));
        model.addAttribute("result",resultService);
        return "surveys/my";
    }

    //Umfrage wird erstellt
    @PostMapping("createSurvey")
    public String createSurvey(@ModelAttribute("survey") CreateSurveyForm form){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = principal instanceof UserDetails ? userService.returnUserByName(((UserDetails) principal).getUsername()) : null;
        Survey survey = formConverter.create(new Survey(), form, user);
        surveyService.saveSurvey(survey);
        return "redirect:" + "my";
    }

    //Frage oder Antwort zu einer Umfrage hinzufügen
    @GetMapping("add")
    public String addToSurvey(@RequestParam Long sid, @RequestParam String actioname, @RequestParam String title){
        if(actioname != null){
            switch(actioname){
                case("addQuestion"):
                    Question q = new Question(surveyService.getSurvey(sid),title);
                    surveyService.getSurvey(sid).addQuestion(q);
                    surveyService.saveQuestion(q);
                    if(q.getAnswers() == null){
                        Answer a = new Answer(q,"Weiß ich nicht.");
                        surveyService.getQuestion(q.getId()).addAnswer(a);
                        surveyService.saveAnswer(a);
                    }
                    break;
                case("addAnswer"):
                    Answer a = new Answer(surveyService.getQuestion(sid),title);
                    surveyService.getQuestion(sid).addAnswer(a);
                    surveyService.saveAnswer(a);
                    sid = surveyService.getQuestion(sid).getSurvey().getId();
                    break;
            }
        }
        return (actioname == "survey") ? ":redirect" + "survey" : "redirect:" + "edit?sid=" + Long.toString(sid);
    }

    //Umfrage wird bearbeitet
    @GetMapping("edit")
    public String edit(@RequestParam Long sid, Model model){
        User currentUser = userService.findCurrentUser();
        if(currentUser.getName() != null){
            model.addAttribute("getSurveyByIdAndUser", surveyService.findSurveyByIdAndUsername(currentUser.getName(),sid));
        }else{
            System.out.println("no user");
        }
        return "surveys/edit";
    }

    //Title einer Umfrage, Frage oder Antwort wird geändert
    @GetMapping("change")
    public String edit(@RequestParam Long id,@RequestParam String change ,Model model){
        switch(change){
            case"Survey":
                model.addAttribute("objectId",id);
                model.addAttribute("changeType",change);
                model.addAttribute("object",surveyService.getSurvey(id));
                break;
            case"Question":
                model.addAttribute("objectId",id);
                model.addAttribute("changeType",change);
                model.addAttribute("object",surveyService.getQuestion(id));
                break;
            case"Answer":
                model.addAttribute("objectId",id);
                model.addAttribute("changeType",change);
                model.addAttribute("object",surveyService.getAnswer(id));
                break;
        }
        return "surveys/change";
    }

    //Titel einer Umfrage wird geändert
    @GetMapping("changeSurvey")
    public String changeSurvey(@RequestParam Long id, @RequestParam String objectTitle){
        String title = objectTitle;
        surveyService.getSurvey(id).setTitle(title);
        surveyService.getSurvey(id).setTitle(objectTitle);return "redirect:" + "edit?sid=" + id;
    }

    //Titel einer Frage wird geändert
    @GetMapping("changeQuestion")
    public String changeQuestion(@RequestParam Long id, @RequestParam String objectTitle){
        surveyService.getQuestion(id).setTitle(objectTitle);
        Long sid = surveyService.getQuestion(id).getSurvey().getId();
        return "redirect:" + "edit?sid=" + sid;
    }

    //Titel einer Antwort wird geändert
    @GetMapping("changeAnswer")
    public String changeAnswer(@RequestParam Long id, @RequestParam String objectTitle){
        surveyService.getAnswer(id).setTitle(objectTitle);
        Long sid = surveyService.getAnswer(id).getQuestion().getSurvey().getId();
        return "redirect:" + "edit?sid=" + sid;
    }

    //Umfrage, Frage oder Antwort wird gelöscht
    @GetMapping("delete")
    public String delete(@RequestParam Long sid, @RequestParam Long e, @RequestParam String type){
        if (type != null) {
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
        return (type == "survey") ? ":redirect" + "my" : "redirect:" + "edit?sid=" + sid;
    }

    //Sperrstatus einer Umfrage wird geändert
    @GetMapping("is_locked")
    public String changeIsLocked(@RequestParam Long sid,@RequestParam int status){
        surveyService.getSurvey(sid).setIs_locked(status);
        System.out.println("MSG: Status der Umfrage wurde auf " + surveyService.getSurvey(sid).getIs_locked() + " gesetzt.");
        return "index";
    }

}
