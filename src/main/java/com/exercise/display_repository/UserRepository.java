package com.exercise.display_repository;

import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository {
    URI getUrlApiRepos();
    String getAccountName();
    Repository getRecentlyPushed(List<Repository> list, LocalDateTime now);
    List<Repository> getRepositoryListFromRestTemplateGetMethod(UserRepository userRepository, RestTemplate restTemplate);

}
