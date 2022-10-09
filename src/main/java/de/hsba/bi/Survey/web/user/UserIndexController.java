package de.hsba.bi.Survey.web.user;


import de.hsba.bi.Survey.user.User;
import de.hsba.bi.Survey.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserIndexController {

    private final RegisterFormConverter formConverter;
    private final UserService userService;

    @GetMapping("registration")
    public String Form(Model model){
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("registerUser")
    public String createUser(@ModelAttribute("user") RegisterEntryForm form, Model model, @RequestParam("password") String password, @RequestParam("passwordrepeat") String passwordRep){
        System.out.println("register Posting started " + password + " " + passwordRep);
        if(userService.findUserByName(form.getUsername()) > 0){
            model.addAttribute("error","Username is already used.");
            return "user/registration";
        }
        if(form.getPassword().contains(" ")){
            model.addAttribute("error", "Password should not have empty spaces.");
            return "user/register";
        }

        if(password.equals(passwordRep)){
            System.out.println("user creation initated");
            User user = formConverter.update(new User(), form);
            userService.save(user);
            userService.findAll().forEach(alluser -> System.out.println(alluser.getName()));
            return "redirect:" + "/";
        }else{
            return "error/404";
        }
    }

    @GetMapping("list")
    public String list(Model model){
        model.addAttribute("Alluser", userService.findAll());
        return "user/list";
    }
}
