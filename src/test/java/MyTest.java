import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

/**
 * Created by Tropico on 07/09/2014.
 */
public class MyTest {

    @Test
    public void verify_equality_is_case_insensitive() {
        assertThat("Hello", iequal("hello"));
        assertThat("HeLlo", iequal("HELLO"));
        assertThat("HeLlo", not(iequal("H ELLO")));
    }

    @Test
    public void verify_null_is_supported_as_well() {
        assertThat(null, iequal(null));
        assertThat(null, not(iequal("hello")));
    }

    private Matcher<String> iequal(final String expected) {
        return new BaseMatcher<String>() {

            @Override
            public boolean matches(Object o) {
                if (o==null && expected == null)
                    return true;
                if (o==null)
                    return false;
                return ((String)o).toLowerCase().equals(expected.toLowerCase());
            }

            @Override
            public void describeTo(Description description) {
                description.appendValue(expected);
            }

            @Override
            public void describeMismatch(java.lang.Object o, org.hamcrest.Description description) {
               description.appendValue("but was " + o.toString());
            }
        };
    }


}
