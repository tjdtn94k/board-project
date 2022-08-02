package com.example.projectboard.repository;

import com.example.projectboard.domain.Article;
import com.example.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource // data.rest에서 지정한(annotated)만 열어줌
public interface ArticleRepository extends
    JpaRepository<Article, Long>,
    QuerydslPredicateExecutor<Article>, // Article 필드에 대한 검색기능
    QuerydslBinderCustomizer<QArticle>
{

  @Override
  default void customize(QuerydslBindings bindings, QArticle root) {
    bindings.excludeUnlistedProperties(true); // 모든 필드 검색 닫기
    bindings.including(root.title,root.hashtag,root.createdAt,root.createdBy); // 특정 필드만 검색 필터 열기
    bindings.bind(root.title).first(StringExpression::containsIgnoreCase); //like '%%'
    bindings.bind(root.content).first(StringExpression::containsIgnoreCase); //like '%%'
    bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase); //like '%%'
    bindings.bind(root.createdAt).first(DateTimeExpression::eq); //like '%%'
    bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); //like '%%'
    //bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // like ''
  }
}