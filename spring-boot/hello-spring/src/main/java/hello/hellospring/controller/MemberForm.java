package hello.hellospring.controller;

public class MemberForm {

    // createMemberForm.html 에 있는 input 값과 일치 시켜줌
    // setName 을 통해 name 값이 들어감
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
