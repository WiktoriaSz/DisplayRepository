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
public class UserWiktoria implements UserRepository {
    private URI urlApiRepos;

    {
        try {
            urlApiRepos = new URI("https://api.github.com/user/47928697/repos");
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
        return "Wiktoria";
    }

    @Override
    public List<Repository> getRepositoryListFromRestTemplateGetMethod(
            UserRepository userWiktoria, RestTemplate restTemplate) {
        ResponseEntity<List<RepositoryMyOwn>> response = restTemplate.exchange(
                userWiktoria.getUrlApiRepos(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RepositoryMyOwn>>() {
                });
        return new ArrayList<>(response.getBody());
    }

}
