import com.mobilejazz.library.MotisMapper;
import models.TestObject;
import models.User;
import junit.framework.TestCase;
import org.json.JSONObject;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by Jose Luis on 06/10/14.
 */
public class BaseTestCase extends TestCase {

    private static final String KEY_BOOL = "boolean";
    private static final String KEY_INT = "int";
    private static final String KEY_FLOAT = "float";
    private static final String KEY_DOUBLE = "double";
    private static final String KEY_STRING = "string";
    private static final String KEY_DATE = "date";
    private static final String KEY_FLOAT_CLASS = "Float";
    private static final String KEY_INTEGER_CLASS = "Integer";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     *
     * Test with boolean attributes
     *
     */

    public void testMotis() {
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

    public void testTrueBoolToBool () throws Exception{
        TestObject motisObject = new TestObject();
        motisObject.setBooleanField(false);

        MotisMapper motisMapper = new MotisMapper(motisObject.getClass());
        motisMapper.mapObjectForKey(motisObject, KEY_BOOL, true);

        assertThat(motisObject.isBooleanField()).isTrue();

    }

    public void testFalseBoolToBool () throws Exception {
        TestObject motisObject = new TestObject();
        motisObject.setBooleanField(true);

        MotisMapper motisMapper = new MotisMapper(motisObject.getClass());
        motisMapper.mapObjectForKey(motisObject, KEY_BOOL, false);

        assertThat(motisObject.isBooleanField()).isFalse();
    }

    public void testIntToInt () throws Exception {
        TestObject motisObject = new TestObject();
        motisObject.setIntField(1);

        MotisMapper motisMapper = new MotisMapper(motisObject.getClass());
        motisMapper.mapObjectForKey(motisObject, KEY_INT, 0);

        assertThat(motisObject.getIntField())
                .isGreaterThanOrEqualTo(0)
                .isNotNull();

        motisMapper.mapObjectForKey(motisObject, KEY_INT, -1);

        assertThat(motisObject.getIntField())
                .isLessThanOrEqualTo(-1)
                .isNotEqualTo(0)
                .isNegative()
                .isNotNull();
    }

    public void testStringToInt () throws Exception {
        TestObject motisObject = new TestObject();

        MotisMapper motisMapper = new MotisMapper(motisObject.getClass());
        motisMapper.mapObjectForKey(motisObject, KEY_INT, "1 ");

        assertThat(motisObject.getIntField())
                .isNotNull()
                .isEqualTo(1);
    }

    public void testStringToFloat () throws Exception {
        TestObject motisObject = new TestObject();

        MotisMapper motisMapper = new MotisMapper(motisObject.getClass());

        // Check with primitive type
        motisMapper.mapObjectForKey(motisObject, KEY_FLOAT, "1.0f ");

        assertThat(motisObject.getFloatField())
                .isNotNull()
                .isEqualTo(1.0f);

        // Check with Float class
        motisMapper.mapObjectForKey(motisObject, KEY_FLOAT_CLASS, "1.0f ");

        assertThat(motisObject.getFloatClassField())
                .isNotNull()
                .isEqualTo(1.0f);


    }

   public void testFloatToFloat() throws Exception {
        TestObject motisObject = new TestObject();
        motisObject.setFloatField(0.0f);

        MotisMapper motisMapper = new MotisMapper(motisObject.getClass());
        motisMapper.mapObjectForKey(motisObject, KEY_FLOAT, 1.0f);

        assertThat(motisObject.getFloatField())
                .isNotNull()
                .isEqualTo(1.0f);

    }

    public void testNumberToString() throws Exception {
//        TestObject motisObject = new TestObject();
//        motisObject.setStringField(1);

    }
}
