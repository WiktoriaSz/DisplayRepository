package com.exercise.display_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Controller
public class HomeController {
    @Autowired
    UserRepository user;
    @Autowired
    Repository repository;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String showMain(Model model) {
        model.addAttribute("accName", user.getAccountName());
        return "index";
    }

    @GetMapping("/display")
    public String displayRepository(Model model) {
        model.addAttribute("accName", user.getAccountName());
        Repository repo = user.getRecentlyPushed(
                user.getRepositoryListFromRestTemplateGetMethod(user, restTemplate),
                LocalDateTime.now());
        model.addAttribute("repoName", repo.getName());
        model.addAttribute("repoDate", ZonedDateTime.parse(repo.getPushed_at()).toLocalDateTime());
        return "index";
    }

}
