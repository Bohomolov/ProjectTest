import com.fasterxml.jackson.core.JsonProcessingException;

public class Client {
    public static void main(String[] args) throws JsonProcessingException {
        AppController controller = new AppController();
        controller.run();
    }
}