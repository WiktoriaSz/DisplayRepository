package com.exercise.display_repository;

import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class UserAllegro implements UserRepository {
    private URI urlApiRepos;

    {
        try {
            urlApiRepos = new URI("https://api.github.com/user/562236/repos");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public URI getUrlApiRepos() {
        return urlApiRepos;
    }

    @Override
    public String getAccountName() {
        return "Allegro";
    }

    @Override
    public List<Repository> getRepositoryListFromRestTemplateGetMethod(
            UserRepository allegroUser, RestTemplate restTemplate) {
        ResponseEntity<List<RepositoryAllegro>> response = restTemplate.exchange(
                allegroUser.getUrlApiRepos(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RepositoryAllegro>>() {
                });
        return new ArrayList<>(response.getBody());
    }
}
