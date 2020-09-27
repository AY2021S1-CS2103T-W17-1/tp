package seedu.description.model.bug;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.description.testutil.Assert;

public class LocationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Location(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Location(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        Assert.assertThrows(NullPointerException.class, () -> Location.isValidPhone(null));

        // invalid phone numbers
        assertFalse(Location.isValidPhone("")); // empty string
        assertFalse(Location.isValidPhone(" ")); // spaces only
        assertFalse(Location.isValidPhone("91")); // less than 3 numbers
        assertFalse(Location.isValidPhone("phone")); // non-numeric
        assertFalse(Location.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Location.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(Location.isValidPhone("911")); // exactly 3 numbers
        assertTrue(Location.isValidPhone("93121534"));
        assertTrue(Location.isValidPhone("124293842033123")); // long phone numbers
    }
}
