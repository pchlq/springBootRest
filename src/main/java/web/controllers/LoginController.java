package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Pavel Peskov
 */
@Controller
public class LoginController {

    @GetMapping({"/login"})
    public String getLoginPage() {
        return "login";
    }

}
