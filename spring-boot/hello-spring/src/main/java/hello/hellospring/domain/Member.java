package hello.hellospring.domain;

import javax.persistence.*;

// @Entity : JPA 로 관리하는 Entity 이다.
@Entity
public class Member {

    // @Id : PK
    // @GeneratedValue(strategy = GenerationType.IDENTITY) : 자동 증가 값. GenerationType.IDENTITY = DB 가 알아서 생성해줌
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(name = "name") : 컬럼 정보 @Column(name = "컬럼명")
    // @Column(name = "name")
    private String name;

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
