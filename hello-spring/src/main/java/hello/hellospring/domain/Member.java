package hello.hellospring.domain;

import javax.persistence.*;

@Entity // jpa가 관리하는 엔티티
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // id 값 자동 증가
    private Long id; // 시스템이 정하는 임의의 id 값

    // @Column(name = "username") // DB 컬럼명이 username인 경우 이와 같이 입력하면 자동으로 맵핑해준다.
    private String name; // 고객이 입력하는 이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
