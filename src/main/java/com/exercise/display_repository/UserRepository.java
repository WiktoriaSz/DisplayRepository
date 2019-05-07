package com.exercise.display_repository;

import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface UserRepository {
    URI getUrlApiRepos();
    String getAccountName();
    default Repository getRecentlyPushed(List<Repository> list, LocalDateTime now){
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
    };
    List<Repository> getRepositoryListFromRestTemplateGetMethod(UserRepository userRepository, RestTemplate restTemplate);

}
