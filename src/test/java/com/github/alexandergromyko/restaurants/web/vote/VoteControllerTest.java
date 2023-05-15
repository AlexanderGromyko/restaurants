package com.github.alexandergromyko.restaurants.web.vote;

import com.github.alexandergromyko.restaurants.repository.VoteRepository;
import com.github.alexandergromyko.restaurants.to.VoteToTest;
import com.github.alexandergromyko.restaurants.util.JsonUtil;
import com.github.alexandergromyko.restaurants.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.github.alexandergromyko.restaurants.web.restaurant.RestaurantTestData.restaurant1;
import static com.github.alexandergromyko.restaurants.web.user.UserTestData.*;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VoteControllerTest extends AbstractControllerTest {
    @Autowired
    private VoteRepository voteRepository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(VoteController.REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VoteTestData.VOTE_TO_TEST_MATCHER.contentJson(VoteTestData.VOTE_TO_RESTAURANT2));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getNotExisted() throws Exception {
        perform(MockMvcRequestBuilders.get(VoteController.REST_URL))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("1")));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void delete() throws Exception {
        LocalDateTime defaultLocalDateTime = LocalDateTime.now().withHour(9);
        try (MockedStatic<LocalDateTime> mockedLocalDateTime = Mockito.mockStatic(LocalDateTime.class)) {
            mockedLocalDateTime.when(LocalDateTime::now).thenReturn(defaultLocalDateTime);
            perform(MockMvcRequestBuilders.delete(VoteController.REST_URL))
                    .andExpect(status().isNoContent());
            assertFalse(voteRepository.get(USER_ID, LocalDate.now()).isPresent());
        }
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithPlacement() throws Exception {
        VoteToTest newVoteToTest = VoteTestData.getNewToTest();
        ResultActions action = perform(MockMvcRequestBuilders.post(VoteController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVoteToTest)));

        VoteToTest created = VoteTestData.VOTE_TO_TEST_MATCHER.readFromJson(action);
        int newId = created.id();
        newVoteToTest.setId(newId);
        VoteTestData.VOTE_TO_TEST_MATCHER.assertMatch(created, newVoteToTest);
        VoteTestData.VOTE_TO_TEST_MATCHER.assertMatch(VoteTestData.createToTest(voteRepository.getExisted(newId)), newVoteToTest);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateOnWrongTime() throws Exception {
        LocalDateTime defaultLocalDateTime = LocalDateTime.now().withHour(12);
        try (MockedStatic<LocalDateTime> mockedLocalDateTime = Mockito.mockStatic(LocalDateTime.class)) {
            mockedLocalDateTime.when(LocalDateTime::now).thenReturn(defaultLocalDateTime);
            perform(MockMvcRequestBuilders.put(VoteController.REST_URL + "/" + restaurant1.id()))
                    .andExpect(status().isUnprocessableEntity());

        }
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void deleteOnWrongTime() throws Exception {
        LocalDateTime defaultLocalDateTime = LocalDateTime.now().withHour(12);
        try (MockedStatic<LocalDateTime> mockedLocalDateTime = Mockito.mockStatic(LocalDateTime.class)) {
            mockedLocalDateTime.when(LocalDateTime::now).thenReturn(defaultLocalDateTime);
            perform(MockMvcRequestBuilders.delete(VoteController.REST_URL))
                    .andExpect(status().isUnprocessableEntity());
        }
    }
}