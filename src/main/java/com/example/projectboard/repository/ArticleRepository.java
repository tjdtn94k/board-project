package com.example.projectboard.repository;

import com.example.projectboard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource // data.rest에서 지정한(annotated)만 열어줌
public interface ArticleRepository extends JpaRepository<Article, Long> {

}