package travel.myweb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import travel.myweb.component.TravelPost;
import travel.myweb.repository.TravelPostRepository;
import travel.myweb.service.TravelPostService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final TravelPostService travelPostService;
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", travelPostService.getAllPosts());
        return "index";
    }
}
