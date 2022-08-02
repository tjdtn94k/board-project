package com.example.projectboard.config;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // Auditing 기능 활성화
@Configuration // Bean 설정 클래스(필드의 Getter, Setter)
public class JpaConfig {
  @Bean
  public AuditorAware<String> auditorAware(){
    return () -> Optional.of("kss"); // TODO : 스프링 시큐리티로 인증기능을 붙이게 될 때 수정
  }
}
