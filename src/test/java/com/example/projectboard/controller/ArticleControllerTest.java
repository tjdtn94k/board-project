package com.example.projectboard.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.projectboard.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@DisplayName("View Controller - 게시글")
@Import(SecurityConfig.class)
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

  private final MockMvc mvc;

  public ArticleControllerTest(@Autowired MockMvc mvc) {
    this.mvc = mvc;
  }
  @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 호출")
  @Test
  public void ginvenNoting_whenRequestingArticlesView_thenReturnsArticlesView() throws Exception {
    //given

    // when & then
    mvc.perform(get("/articles"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
        .andExpect((MockMvcResultMatchers.view().name("articles/index"))) // 호출한 view 이름
        .andExpect(MockMvcResultMatchers.model().attributeExists("articles")); // model에 articles key 값이 있는 지 확인
  }
  @DisplayName("[view][GET] 게시글 상세 페이지 - 호출")
  @Test
  public void ginvenNoting_whenRequestingArticleView_thenReturnsArticleView() throws Exception {
    //given

    // when & then
    mvc.perform(get("/articles/1"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
        .andExpect((MockMvcResultMatchers.view().name("articles/detail"))) 
        .andExpect(MockMvcResultMatchers.model().attributeExists("article")) // model에 article key 값이 있는 지 확인
        .andExpect(MockMvcResultMatchers.model().attributeExists("articleComments")); // model에 articleComments key 값이 있는 지 확인
  }
  @DisplayName("[view][GET] 게시글 검색 전용 페이지 - 호출")
  @Test
  public void ginvenNoting_whenRequestingArticleSearchView_thenReturnsArticleSearchView() throws Exception {
    //given

    // when & then
    mvc.perform(get("/articles/search"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
        .andExpect(MockMvcResultMatchers.model().attributeExists("articles/search")); // model에 articles/search key 값이 있는 지 확인
  }
  @DisplayName("[view][GET] 게시글 해시태그 검색 페이지 - 호출")
  @Test
  public void ginvenNoting_whenRequestingArticleHashtagSearchView_thenReturnsArticleHashtagSearchView() throws Exception {
    //given

    // when & then
    mvc.perform(get("/articles/search-hashtag"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
        .andExpect(MockMvcResultMatchers.model().attributeExists("article/search-hashtag")); // model에 article/search-hashtag key 값이 있는 지 확인
  }
}