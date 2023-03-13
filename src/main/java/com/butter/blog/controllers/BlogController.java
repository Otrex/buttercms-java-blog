package com.butter.blog.controllers;

import com.butter.blog.services.BlogService;
import com.buttercms.model.PostsResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class BlogController {
    private BlogService blogService;
    BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/")
    public String index(Model model) {
        var posts = this.blogService.getBlogs("2", "1");
        model.addAttribute("posts", posts.getData());
        return "index";
    }

    @GetMapping("/search")
    ResponseEntity<PostsResponse> search(@RequestParam("q") String query) {
        var results = this.blogService.search(query);
        return ResponseEntity.ok(results);
    }


    @GetMapping("/posts")
    public String getPosts(Model model, @RequestParam Map<String, String> req) {
        try {
            String page = req.get("page") == null ? "1": req.get("page");
            String pageSize = req.get("page_size") == null ? "10": req.get("page_size");

            var posts = this.blogService.getBlogs(pageSize, page).getData();
            model.addAttribute("posts", posts);

            return "posts";
        } catch (Exception e) {
            model.addAttribute("error", e);
            return "error";
        }
    }

    @GetMapping("/posts/{slug}")
    public String getPost(Model model, @PathVariable String slug) {
        try {
            var blog = this.blogService.getBlog(slug).getData();
            model.addAttribute("post", blog);

            return "blog";
        } catch (Exception e) {
            model.addAttribute("error", e);
            return "error";
        }
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }
}
