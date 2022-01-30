package hello.core.singleton;

public class SingletonService {

    //static 영역공부 해야 됨
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }


    //private를 이용해 다른 곳에서의 접근을 막아둠
    private SingletonService(){

    }
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
