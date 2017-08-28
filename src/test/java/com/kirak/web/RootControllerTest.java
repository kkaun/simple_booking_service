package com.kirak.web;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.kirak.Profile.DATAJPA;
import static com.kirak.TestUtil.userAuth;
import static com.kirak.mock.UserTestData.ADMIN;
import static com.kirak.mock.UserTestData.MANAGER;
import static com.kirak.mock.UserTestData.USER1;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Kir on 26.08.2017.
 */
@ActiveProfiles(DATAJPA)
public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void getAdminPanel() throws Exception {
        mockMvc.perform(get("/administrate")
                .with(userAuth(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
                .andExpect(forwardedUrl("/WEB-INF/view/admin.jsp"));
    }

    @Test
    public void getManagerObjects() throws Exception {
        mockMvc.perform(get("/manage")
                .with(userAuth(MANAGER)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("managerObjects"))
                .andExpect(forwardedUrl("/WEB-INF/view/managerObjects.jsp"));
    }

    @Test
    public void getUserOwnActivity() throws Exception {
        mockMvc.perform(get("/user_activity")
                .with(userAuth(USER1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user"))
                .andExpect(forwardedUrl("/WEB-INF/view/user.jsp"));
    }

    @Test
    public void handleUnauthorizedRequest() throws Exception {
        mockMvc.perform(get("/administrate"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

}