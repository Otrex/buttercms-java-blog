package com.butter.blog.controllers;

import com.butter.blog.services.BlogService;
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
        var blogs = this.blogService.getBlogs("4", "1").getData();
        var first_post = blogs.remove(0);

        model.addAttribute("first_post", first_post);
        model.addAttribute("other_post", blogs);

        System.out.println(blogs);
        System.out.println(first_post);
        return "index";
    }


    @GetMapping("/blogs")
    public String getPosts(Model model, @RequestParam Map<String, String> req) {
        String page = req.get("page") == null ? "1": req.get("page");
        String pageSize = req.get("page_size") == null ? "10": req.get("page_size");

        var blogs = this.blogService.getBlogs(pageSize, page).getData();
        model.addAttribute("posts", blogs);

        return "blogs";
    }

    @GetMapping("/blogs/{slug}")
    public String getPost(Model model, @PathVariable String slug) {
        var blog = this.blogService.getBlog(slug).getData();
        model.addAttribute("post", blog);

        return "blog";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }
}
