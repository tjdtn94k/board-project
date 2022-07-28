package com.example.projectboard.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@ToString
@Table(indexes = {
    @Index(columnList = "conte"),
    @Index(columnList = "createdAt"),
    @Index(columnList = "createdBy")
})

@Entity
public class ArticleComment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter @ManyToOne(optional = false) private Article article; // 게시글 id
  @Setter @Column(nullable = false, length = 2000) private String content; // 내용

  @CreatedDate
  @Column(nullable = false) private LocalDateTime createdAt; // 생성일시
  @CreatedBy
  @Column(nullable = false , length = 100) private String createdBy; // 생성자
  @LastModifiedDate
  @Column(nullable = false) private LocalDateTime modifiedAt; // 수정일시
  @LastModifiedBy
  @Column(nullable = false , length = 100) private String modifiedBy; // 수정자



}
