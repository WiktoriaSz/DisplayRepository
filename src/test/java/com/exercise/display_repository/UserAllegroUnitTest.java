package com.exercise.display_repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserAllegroUnitTest {

    private List<Repository> repositoryList = new ArrayList<>();
    private UserRepository userRepository = new UserAllegro();
    private RestTemplate restTemplate = new RestTemplate();
    private Repository r3;

    @Before
    public void setUp() {
        Repository r1 = new RepositoryAllegro("r1", "2017-03-22T08:10:35Z");
        Repository r2 = new RepositoryAllegro("r2", "2018-02-07T16:09:20Z");
        r3 = new RepositoryAllegro("r3", "2019-04-03T09:46:04Z");
        repositoryList.add(r1);
        repositoryList.add(r2);
        repositoryList.add(r3);
    }

    @Test
    public void checkIfReturnsRecentlyPublished(){
        Assert.assertEquals(userRepository.getRecentlyPushed(repositoryList, LocalDateTime.now()), r3);
    }

    @Test
    public void checkIfReturnsNullIfTheListIsEmpty(){
        List<Repository> some = new ArrayList<>();
        Assert.assertNull(userRepository.getRecentlyPushed(some, LocalDateTime.now()));
    }

    @Test
    public void restTemplateGetJsonIntoRepositoryListTest(){
        Assert.assertEquals(
                "akubra",
                userRepository.getRepositoryListFromRestTemplateGetMethod(userRepository, restTemplate).get(0).getName());
    }
}
