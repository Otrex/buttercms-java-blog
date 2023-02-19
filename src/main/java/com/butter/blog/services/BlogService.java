package com.butter.blog.services;

import java.util.HashMap;
import java.util.Map;

import com.buttercms.ButterCMSClient;
import com.buttercms.IButterCMSClient;
import com.buttercms.model.PostResponse;
import com.buttercms.model.PostsResponse;
import org.springframework.stereotype.Service;
import io.github.cdimascio.dotenv.Dotenv;

@Service
public class BlogService {
    private String buttterAPIKey;
    private IButterCMSClient butterCMSClient;

    BlogService() {
        this.setupButter();
    }

    private void setupButter() {
        Dotenv dotenv = Dotenv.load();
        this.buttterAPIKey = dotenv.get("BUTTERKEY");
        this.butterCMSClient = new ButterCMSClient(this.buttterAPIKey);
    }


    public PostsResponse getBlogs(String pageSize, String page) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("page", page);
        params.put("page_size", pageSize);
        params.put("exclude_body", "true");

        System.out.println(this.butterCMSClient.getPosts(params));

        return this.butterCMSClient.getPosts(params);
    }

    public PostResponse getBlog(String slug) {
        return this.butterCMSClient.getPost(slug);
    }
}
