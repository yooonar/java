package hello.core.singletone;

public class SingletoneService {

    // 내부에 private static 형식으로 자기 자신 선언 <= 이 경우 클래스 레벨에 올라가기 때문에 딱 하나만 존재하게 됨
    // instance 의 참조를 꺼낼 수 있는 방법은 getInstance 밖에 없다.
    private static final SingletoneService instance = new SingletoneService();

    public static SingletoneService getInstance() {
        // JVM 이 뜰 때 SingletoneService 의 static 영역에 있는 SingletoneService 객체를 내부적으로 실행해 인스턴스 참조로 넣어둠

        return instance;
    }

    // 다른 파일에서 임의로 SingletoneService 를 추가로 생성하는 경우가 있어 private 생성자로 생성을 막아버린다.
    private SingletoneService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
