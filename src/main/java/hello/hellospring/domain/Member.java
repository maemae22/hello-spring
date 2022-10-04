package hello.hellospring.domain;

import javax.persistence.*;

@Entity // JPA가 관리하는 엔티티가 됨
public class Member {

    @Id // PK 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 자동으로 시퀀스 올라가는거
    private Long id; // 데이터를 구분하기 위해서 시스템이 정하는 id

    // @Column(name = "username") : DB의 컬럼명이 username일 경우 이렇게 작성. 우리는 똑같이 name이니까 작성할 필요 없음
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
