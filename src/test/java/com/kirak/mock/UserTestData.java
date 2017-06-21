package com.kirak.mock;

import com.kirak.model.Booking;
import com.kirak.model.User;
import com.kirak.model.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.kirak.mock.BookingTestData.*;
import static com.kirak.model.abstraction.BaseIntEntity.START_SEQ;

/**
 * Created by Kir on 20.06.2017.
 */
public class UserTestData {

    private static final Logger LOG = LoggerFactory.getLogger(UserTestData.class);

    public static final int USER1_ID = START_SEQ;
    public static final int USER2_ID = START_SEQ + 1;
    public static final int USER3_ID = START_SEQ + 2;
    public static final int MANAGER_ID = START_SEQ + 3;
    public static final int ADMIN_ID = START_SEQ + 4;

    public static final Set<Booking> USER1_BOOKINGS = new HashSet<Booking>(Arrays.asList(new Booking[] {BOOKING1, BOOKING4}));
    public static final Set<Booking> USER2_BOOKINGS = new HashSet<Booking>(Arrays.asList(new Booking[] {BOOKING2}));
    public static final Set<Booking> USER3_BOOKINGS = new HashSet<Booking>(Arrays.asList(new Booking[] {BOOKING3}));
    public static final Set<Booking> ADMIN_BOOKINGS = new HashSet<Booking>(Arrays.asList(new Booking[] {BOOKING5}));

    public static final User USER1 = new User(USER1_ID, "User", "user@yandex.ru", "password", USER1_BOOKINGS, UserRole.ROLE_USER);
    public static final User USER2 = new User(USER2_ID, "User", "user@yandex.ru", "password", USER2_BOOKINGS, UserRole.ROLE_USER);
    public static final User USER3 = new User(USER3_ID, "User", "user@yandex.ru", "password", USER3_BOOKINGS, UserRole.ROLE_USER);
    public static final User MANAGER = new User(MANAGER_ID, "Manager", "manager@gmail.com", "password",
            Collections.emptySet(), UserRole.ROLE_HOTEL_MANAGER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin",
            ADMIN_BOOKINGS, UserRole.ROLE_SYSTEM_ADMIN, UserRole.ROLE_USER);


//Integer id, String name, String email, String password, (Date registered), Set<Booking> bookings, UserRole role, UserRole... roles



//    public static final ModelMatcher<User> MATCHER = ModelMatcher.of(User.class,
//            (expected, actual) -> expected == actual ||
//                    (comparePassword(expected.getPassword(), actual.getPassword())
//                            && Objects.equals(expected.getId(), actual.getId())
//                            && Objects.equals(expected.getName(), actual.getName())
//                            && Objects.equals(expected.getEmail(), actual.getEmail())
//                            && Objects.equals(expected.getCaloriesPerDay(), actual.getCaloriesPerDay())
//                            && Objects.equals(expected.isEnabled(), actual.isEnabled())
//                            && Objects.equals(expected.getRoles(), actual.getRoles())
//                    )
//    );


}
