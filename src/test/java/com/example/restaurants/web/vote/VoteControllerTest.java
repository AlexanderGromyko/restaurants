package com.example.restaurants.web.vote;

import com.example.restaurants.repository.VoteRepository;
import com.example.restaurants.to.VoteToTest;
import com.example.restaurants.util.JsonUtil;
import com.example.restaurants.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static com.example.restaurants.service.VoteService.itIsGoodTimeToMakeVote;
import static com.example.restaurants.web.user.UserTestData.*;
import static com.example.restaurants.web.vote.VoteTestData.VOTE_TO_RESTAURANT2;
import static com.example.restaurants.web.vote.VoteTestData.VOTE_TO_TEST_MATCHER;
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
                .andExpect(VOTE_TO_TEST_MATCHER.contentJson(VOTE_TO_RESTAURANT2));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void delete() throws Exception {
        if(itIsGoodTimeToMakeVote()) {
            perform(MockMvcRequestBuilders.delete(VoteController.REST_URL))
                    .andExpect(status().isNoContent());
            assertFalse(voteRepository.get(USER_ID, LocalDate.now()).isPresent());
        }
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithPlacement() throws Exception {
        if(itIsGoodTimeToMakeVote()) {
            VoteToTest newVoteToTest = VoteTestData.getNewToTest();
            ResultActions action = perform(MockMvcRequestBuilders.post(VoteController.REST_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.writeValue(newVoteToTest)));

            VoteToTest created = VOTE_TO_TEST_MATCHER.readFromJson(action);
            int newId = created.id();
            newVoteToTest.setId(newId);
            VOTE_TO_TEST_MATCHER.assertMatch(created, newVoteToTest);
            VOTE_TO_TEST_MATCHER.assertMatch(VoteTestData.createToTest(voteRepository.getExisted(newId)), newVoteToTest);
        }
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createOnWrongTime() throws Exception {
        if(!itIsGoodTimeToMakeVote()) {
            ResultActions action = perform(MockMvcRequestBuilders.post(VoteController.REST_URL)
                    .contentType(MediaType.APPLICATION_JSON));
            action.andExpect(status().isUnprocessableEntity());
        }
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void deleteOnWrongTime() throws Exception {
        if(!itIsGoodTimeToMakeVote()) {
            perform(MockMvcRequestBuilders.delete(VoteController.REST_URL))
                    .andExpect(status().isUnprocessableEntity());
        }
    }
}