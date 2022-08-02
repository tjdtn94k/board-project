package com.example.projectboard.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@ToString(callSuper = true) // Java에서 사용하는 ToString 동일 : 객체가 가지고 있는 정보나 값들을 문자열로 만들어 리턴하는 함수( callSuper : 부모 클래스 필드 값들도 동일한지 체크)
@Table(indexes = {
    @Index(columnList = "title"),
    @Index(columnList = "hashtag"),
    @Index(columnList = "createdAt"),
    @Index(columnList = "createdBy")
}) // 맵핑할 테이블 지정(인덱스 추가)
@EntityListeners(AuditingEntityListener.class) // 엔티티를 DB에 적용하기 이전, 이후에 콜백 리스너를 요청할 수 있는 어노테이션
@Entity // DB 테이블과 1:1 매핑할 수 있도록 도와주는 클래스
public class Article extends AuditingFields {

  @Id // 객체의 Primary Key
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에서 자동으로 생성
  private Long id;

  @Setter @ManyToOne(optional = false) @JoinColumn(name = "userId") private UserAccount userAccount; // 유저 정보 (ID) // 단뱡향(N:1) // 외래 키 매핑(userId)

  @Setter @Column(nullable = false) private String title; // 제목 // @Coulum : 객체 필드와 DB 테이블 컬럼을 매핑
  @Setter @Column(nullable = false, length = 10000) private String content; // 본문

  @Setter private String hashtag; // 해시태그

  @ToString.Exclude // 순환참조 닫기
  @OrderBy("createdAt DESC") // 정렬
  @OneToMany(mappedBy = "article", cascade = CascadeType.ALL) // 양뱡향(1:N)
  private final Set<ArticleComment> articleComments = new LinkedHashSet<>();


  protected Article() {}

  private Article(UserAccount userAccount, String title, String content, String hashtag) {
    this.userAccount = userAccount;
    this.title = title;
    this.content = content;
    this.hashtag = hashtag;
  }

  public static Article of(UserAccount userAccount, String title, String content, String hashtag) {
    return new Article(userAccount, title, content, hashtag);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Article that)) return false;
    return id != null && id.equals(that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}