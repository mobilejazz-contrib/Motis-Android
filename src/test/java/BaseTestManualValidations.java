import com.mobilejazz.library.MotisMapper;
import junit.framework.TestCase;
import models.TestObjectValidationMethods;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by Jose Luis on 16/10/14.
 */
public class BaseTestManualValidations extends TestCase {

    private static final String KEY_BOOL = "boolean";
    private static final String KEY_INT = "int";
    private static final String KEY_FLOAT = "float";
    private static final String KEY_DOUBLE = "double";
    private static final String KEY_LONG = "long";
    private static final String KEY_STRING = "string";
    private static final String KEY_DATE = "date";
    private static final String KEY_FLOAT_CLASS = "Float";
    private static final String KEY_INTEGER_CLASS = "Integer";
    private static final String KEY_DOUBLE_CLASS = "Double";
    private static final String KEY_LONG_CLASS = "Long";
    private static final String KEY_BOOLEAN_CLASS = "Boolean";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testValidationMethodInt() throws Exception {
        TestObjectValidationMethods testObject = new TestObjectValidationMethods();

        MotisMapper mapper = MotisMapper.getInstance(testObject.getClass());
        mapper.mapObjectValueForFieldName(testObject, KEY_INT, 1);

        assertThat(testObject.getIntField())
                .isNotNull()
                .isEqualTo(12);

    }

    public void testValidationMethodFloat() throws Exception {
        TestObjectValidationMethods testObject = new TestObjectValidationMethods();

        MotisMapper mapper = MotisMapper.getInstance(testObject.getClass());
        mapper.mapObjectValueForFieldName(testObject, KEY_FLOAT, 1.0f);

        assertThat(testObject.getFloatField())
                .isNotNull()
                .isEqualTo(12.0f);

    }
}
