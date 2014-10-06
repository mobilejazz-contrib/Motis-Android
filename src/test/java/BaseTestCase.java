import com.mobilejazz.library.MotisMapper;
import models.User;
import junit.framework.TestCase;
import org.json.JSONObject;

/**
 * Created by Jose Luis on 06/10/14.
 */
public class BaseTestCase extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testMotisUser () throws Exception {

        System.out.println("MOTIS TEST");

        String rawJson = "{\"server_id\":42,\"user_name\":\"John Doe\",\"creation_date\":1405428749, \"best_friend\":{\"server_id\":24,\"user_name\":\"Lara Croft\",\"creation_date\":1405228739}, \"worse_enemies\":[{\"server_id\":2,\"user_name\":\"Batman The Dark\",\"creation_date\":1305228739},{\"server_id\":6,\"user_name\":\"Javier Chavarri\",\"creation_date\":1405156839},{\"server_id\":9,\"user_name\":\"Austin Peligro Powers\",\"creation_date\":925299900}]}";

        try {
            JSONObject jsonObject = new JSONObject(rawJson);

            User user = new User();
            System.out.println("User 1: " + user.toString());

            MotisMapper motisMapper = new MotisMapper(user.getClass());

            motisMapper.map(user, jsonObject);

            System.out.println("User 2: " + user.toString());
        } catch (Exception e) {
            System.out.println("Catch exception: " + e.toString());
        }
    }
}
