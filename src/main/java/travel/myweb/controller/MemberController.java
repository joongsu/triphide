package travel.myweb.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import travel.myweb.component.Member;
import travel.myweb.repository.MemberRepository;
import travel.myweb.service.MemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("joinForm", new RegisterForm());
        return "members/register";
    }

    @PostMapping("/new")
    public String createForm(@Valid RegisterForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "members/register";
        }

        Member member = new Member();
        member.setName(form.getName());
        member.setUsername(form.getUsername());
        member.setPassword(form.getPassword());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "members/login";
    }
    @PostMapping("/members/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        // 로그인 로직
        Member member = memberService.login(username, password);
        if (member != null) {
            model.addAttribute("member", member);
            return "/";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "members/login";
        }
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("member", new Member());
        return "members/register";
    }

    @PostMapping("/register")
    public String register(Member member, Model model) {
        memberRepository.save(member);
        return "redirect:/members/login";
    }


}
