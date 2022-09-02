package de.hsba.bi.Survey.web.user;


import de.hsba.bi.Survey.survey.SurveyService;
import de.hsba.bi.Survey.user.User;
import de.hsba.bi.Survey.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//import javax.validation.Valid;

@Controller
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserIndexController {

    private final SurveyService surveyService;
    private final RegisterFormConverter formConverter;
    private final UserService userService;

    @GetMapping("registration")
    public String Form(Model model){
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("registerUser")
    public String createUser(@ModelAttribute("user") RegisterEntryForm form, BindingResult result,Model model){
        System.out.println("register Posting started");
        if(userService.findUserByName(form.getUsername()) > 0){
            model.addAttribute("error","Username is already used.");
            System.out.println("Name is already used.");
            return "user/registration";
        }

        if(form.getPassword().contains(" ")){
            model.addAttribute("error", "Password should not have empty spaces.");
            System.out.println("Password is null.");
            return "user/register";
        }

        System.out.println("User creation initiated");
        User user = formConverter.update(new User(), form);
        userService.save(user);
        userService.findAll().forEach(alluser -> System.out.println(alluser.getName()));
        return "redirect:" + "/";
    }

    @GetMapping("list")
    public String list(Model model){
        model.addAttribute("Alluser", userService.findAll());
        return "user/list";
    }
}
