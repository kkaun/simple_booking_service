package com.kirak.mock;

import com.kirak.matchers.ModelMatcher;
import com.kirak.model.*;
import com.kirak.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import static com.kirak.model.abstraction.BaseIntEntity.START_SEQ;

/**
 * Created by Kir on 20.06.2017.
 */
public class UserTestData {



    private static final Logger LOG = LoggerFactory.getLogger(UserTestData.class);

    public static final Integer USER1_ID = START_SEQ;
    public static final Integer USER2_ID = START_SEQ + 1;
    public static final Integer USER3_ID = START_SEQ + 2;
    public static final Integer MANAGER_ID = START_SEQ + 3;
    public static final Integer ADMIN_ID = START_SEQ + 4;


    public static final Set<SuperBooking> USER1_SUPER_BOOKINGS = new HashSet<SuperBooking>(Arrays.asList(new SuperBooking[] {}));
    public static final Set<SuperBooking> USER2_SUPER_BOOKINGS = new HashSet<SuperBooking>(Arrays.asList(new SuperBooking[] {}));
    public static final Set<SuperBooking> USER3_SUPER_BOOKINGS = new HashSet<SuperBooking>(Arrays.asList(new SuperBooking[] {}));
    public static final Set<SuperBooking> ADMIN_SUPER_BOOKINGS = new HashSet<SuperBooking>(Arrays.asList(new SuperBooking[] {}));

    public static final User USER1 = new User(USER1_ID, "User1", "user1@yandex.ru", "483748273423",
            "password1", UserRole.ROLE_USER);
    public static final User USER2 = new User(USER2_ID, "User2", "user2@yandex.ru", "483711111123",
            "password2", UserRole.ROLE_USER);
    public static final User USER3 = new User(USER3_ID, "User3", "user3@yandex.ru", "483333373423",
            "password3", UserRole.ROLE_USER);
    public static final User MANAGER = new User(MANAGER_ID, "Manager", "manager@gmail.com", "432523522352",
            "manager", UserRole.ROLE_MANAGER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin",
            UserRole.ROLE_USER, UserRole.ROLE_ADMIN);


    public static final List<User> ALL_USERS = Arrays.asList(ADMIN, MANAGER, USER1, USER2, USER3);


    public static final ModelMatcher<User> USER_MATCHER = ModelMatcher.of(User.class,
            (expected, actual) -> expected == actual ||
                    (comparePassword(expected.getPassword(), actual.getPassword())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.getVotes(), actual.getVotes())
                            && Objects.equals(expected.getRoles(), actual.getRoles())
                    )
    );


    private static boolean comparePassword(String rawOrEncodedPassword, String password) {
        if (PasswordUtil.isEncoded(rawOrEncodedPassword)) {
            return rawOrEncodedPassword.equals(password);
        } else if (!PasswordUtil.isMatch(rawOrEncodedPassword, password)) {
            LOG.error("Password " + password + " doesn't match encoded " + password);
            return false;
        }
        return true;
    }


    public static User getCreatedUser() {
        return new User(null, "User NEW", "user644@yandex.ru", "4324232343", "password1", UserRole.ROLE_USER);
    }

    public static User getUpdatedUser() {
        return new User(USER1_ID, "User UPDATED", "user657@yandex.ru", "3847242323", "password1", UserRole.ROLE_USER);
    }


}
