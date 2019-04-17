package com.exercise.display_repository;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
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
    public Repository getRecentlyPushed(List<Repository> list, LocalDateTime now) {
        if (!list.isEmpty()) {
            LocalDateTime temporary = LocalDateTime.from(now);
            long seconds = temporary.until(ZonedDateTime.parse(list.get(0).getPushed_at()), ChronoUnit.SECONDS);

            Repository rep = list.get(0);
            for (Repository repo : list) {
                if (seconds < temporary.until(ZonedDateTime.parse(repo.getPushed_at()), ChronoUnit.SECONDS)) {
                    rep = repo;
                    seconds = temporary.until(ZonedDateTime.parse(repo.getPushed_at()), ChronoUnit.SECONDS);
                }
            }
            return rep;
        } else {
            return null;
        }
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
