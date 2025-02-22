package ecc.sogonsogon.BE.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public Map<String, String> home() {
        return Map.of(
                "message", "Welcome, sogonsogon",
                "postsList", "/api/posts",
                "createPost", "POST /api/posts (body: {title, ...})",
                "apiList", "https://www.notion.so/API-17c62e4cb2ae81e2ad34c2a592c1602c?pvs=4"
        );
    }
}