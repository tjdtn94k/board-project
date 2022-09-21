package com.example.projectboard.controller;

import com.example.projectboard.domain.type.SearchType;
import com.example.projectboard.dto.response.ArticleCommentResponse;
import com.example.projectboard.dto.response.ArticleResponse;
import com.example.projectboard.dto.response.ArticleWithCommentsResponse;
import com.example.projectboard.service.ArticleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleController {

  private final ArticleService articleService;

  // 게시판 리스트 조회
  @GetMapping
  public String articles(
      @RequestParam(required = false) SearchType searchType,
      @RequestParam(required = false) String searchValue,
      @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
      ModelMap map) {
    map.addAttribute("articles", articleService.searchArticles(searchType,searchValue,pageable).map(ArticleResponse::from));
    return "articles/index";
  }

  // 게시글 상세조회
  @GetMapping("/{articleId}")
  public String article(@PathVariable Long articleId, ModelMap map) {
    ArticleWithCommentsResponse article = ArticleWithCommentsResponse.from(articleService.getArticleWithComments(articleId));

    map.addAttribute("article", article);
    map.addAttribute("articleComments", article.articleCommentsResponse());
    //map.addAttribute("totalCount", articleService.getArticleCount());

    return "articles/detail";
  }
}
