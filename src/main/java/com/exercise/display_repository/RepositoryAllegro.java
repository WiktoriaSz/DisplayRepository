package com.exercise.display_repository;
import org.springframework.stereotype.Component;

@Component
public class RepositoryAllegro implements Repository {
    private String name;
    private String pushed_at;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPushed_at() {
        return pushed_at;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "name='" + name + '\'' +
                ", pushed_at=" + pushed_at +
                '}';
    }

    public RepositoryAllegro(String name, String pushed_at) {
        this.name = name;
        this.pushed_at = pushed_at;
    }

    public RepositoryAllegro() {
    }
}
