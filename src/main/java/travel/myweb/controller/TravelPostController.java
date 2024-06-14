package travel.myweb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import travel.myweb.component.TravelPost;
import travel.myweb.service.TravelPostService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TravelPostController {
    private final TravelPostService travelPostService;
    @GetMapping("/travelPosts/new")
    public String newPostForm(Model model) {
        model.addAttribute("travelPost", new TravelPost());
        return "travelPosts/new";
    }

    @PostMapping("/travelPosts/new")
    public String createPost(TravelPost travelPost, @RequestParam("imageFile") MultipartFile imageFile) {
        if (!imageFile.isEmpty()) {
            try {
                String imagePath = "images/" + imageFile.getOriginalFilename();
                File dest = new File(imagePath);
                imageFile.transferTo(dest);
                travelPost.setImagePath(imagePath);
            } catch (IOException e) {
                e.printStackTrace();
                // 오류 처리
            }
        }
        travelPostService.createPost(travelPost);
        return "redirect:/";
    }

    @GetMapping("/travelPosts/hidden")
    public String hiddenPosts(Model model) {
        List<TravelPost> hiddenPosts = travelPostService.getAllHiddenPosts();
        model.addAttribute("posts", hiddenPosts);
        return "posts/hidden";
    }

    @GetMapping("/travelPosts/view")
    public String viewPost(@RequestParam(name = "id") Long id, Model model) {
        TravelPost post = travelPostService.findPostById(id);
        model.addAttribute("post", post);
        return "travelPosts/view";
    }
}
