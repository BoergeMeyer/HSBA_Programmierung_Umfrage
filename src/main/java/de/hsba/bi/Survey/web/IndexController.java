package de.hsba.bi.Survey.web;

import de.hsba.bi.Survey.result.ResultService;
import de.hsba.bi.Survey.survey.SurveyService;
import de.hsba.bi.Survey.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final ResultService resultService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("countSurvey",surveyService.returnNumberOfSurveys());
        model.addAttribute("countUsers",userService.returnNumberOfUsers());
        //model.addAttribute("countResult",resultService.returnNumberOfResult());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            System.out.println(((UserDetails) principal).getUsername());
        }
        return auth instanceof AnonymousAuthenticationToken ? "login" : "redirect:/";
    }
}
