package com.example.projectboard.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@DisplayName("View 컨트롤러  - 인증")
@WebMvcTest
public class AuthControllerTest {

  private final MockMvc mvc;

  public AuthControllerTest(@Autowired MockMvc mvc) {
    this.mvc = mvc;
  }
  @DisplayName("[view][GET] 로그인 페이지 -  호출")
  @Test
  public void ginvenNoting_whenRequestingLoginPage_thenReturnsLoginPage() throws Exception {
    //given

    // when & then
    mvc.perform(get("/login"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
  }

}
