package app.diploma.eventCollector.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller("mainController")
public class MainController {
    @RequestMapping(value = "/collector", method = RequestMethod.GET)
    public String index() {
        return "realTimeEvents";
    }
    @RequestMapping(value = "/collector/savedEvents", method = RequestMethod.GET)
    public String viewSavedEvents() {
        return "savedEvents";
    }

    @RequestMapping("/")
    public RedirectView localRedirect() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("collector/");
        return redirectView;
    }
}
