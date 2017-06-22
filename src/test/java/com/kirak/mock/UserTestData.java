package com.kirak.mock;

import com.kirak.matchers.ModelMatcher;
import com.kirak.model.Booking;
import com.kirak.model.Hotel;
import com.kirak.model.User;
import com.kirak.model.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;
import java.util.*;

import static com.kirak.mock.BookingTestData.*;
import static com.kirak.mock.CityTestData.ST_PETERSBURG;
import static com.kirak.mock.CountryTestData.RUSSIA;
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

    public static final Set<Booking> USER1_BOOKINGS = new HashSet<Booking>(Arrays.asList(new Booking[] {BOOKING1, BOOKING4}));
    public static final Set<Booking> USER2_BOOKINGS = new HashSet<Booking>(Arrays.asList(new Booking[] {BOOKING2}));
    public static final Set<Booking> USER3_BOOKINGS = new HashSet<Booking>(Arrays.asList(new Booking[] {BOOKING3}));
    public static final Set<Booking> ADMIN_BOOKINGS = new HashSet<Booking>(Arrays.asList(new Booking[] {BOOKING5}));

    public static final User USER1 = new User(USER1_ID, "User1", "user1@yandex.ru", "password1", USER1_BOOKINGS, UserRole.ROLE_USER);
    public static final User USER2 = new User(USER2_ID, "User2", "user2@yandex.ru", "password2", USER2_BOOKINGS, UserRole.ROLE_USER);
    public static final User USER3 = new User(USER3_ID, "User3", "user3@yandex.ru", "password3", USER3_BOOKINGS, UserRole.ROLE_USER);
    public static final User MANAGER = new User(MANAGER_ID, "Manager", "manager@gmail.com", "manager",
            Collections.emptySet(), UserRole.ROLE_HOTEL_MANAGER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin",
            ADMIN_BOOKINGS, UserRole.ROLE_SYSTEM_ADMIN, UserRole.ROLE_USER);


    public static final List<User> ALL_USERS = Arrays.asList(USER1, USER2, USER3, MANAGER, ADMIN);


    public static final ModelMatcher<User> USER_MATCHER = ModelMatcher.of(User.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getPassword(), actual.getPassword())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getName(), actual.getName())
                            && Objects.equals(expected.getEmail(), actual.getEmail())
                            && Objects.equals(expected.getBookings(), actual.getBookings())
                            && Objects.equals(expected.getVotes(), actual.getVotes())
                            && Objects.equals(expected.getRoles(), actual.getRoles())
                    )
    );


    public static User getCreatedUser() {
        return new User(null, "User NEW", "user1@yandex.ru", "password1", USER1_BOOKINGS, UserRole.ROLE_USER);
    }

    public static User getUpdatedUser() {
        return new User(USER1_ID, "User UPDATED", "user1@yandex.ru", "password1", USER1_BOOKINGS, UserRole.ROLE_USER);
    }

}
