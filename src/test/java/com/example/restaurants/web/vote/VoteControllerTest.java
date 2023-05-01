package com.example.restaurants.web.vote;

import com.example.restaurants.model.Vote;
import com.example.restaurants.repository.VoteRepository;
import com.example.restaurants.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.restaurants.service.VoteService.itIsGoodTimeToMakeVote;
import static com.example.restaurants.web.restaurant.RestaurantTestData.*;
import static com.example.restaurants.web.user.UserTestData.USER_ID;
import static com.example.restaurants.web.user.UserTestData.USER_MAIL;
import static com.example.restaurants.web.vote.VoteTestData.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = REST_URL_USER + '/';

    @Autowired
    private VoteRepository voteRepository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + RESTAURANT2_ID + "/votes"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(VOTE_OF_USER));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + RESTAURANT2_ID + "/votes"))
                .andExpect(status().isNoContent());
        assertFalse(voteRepository.get(USER_ID, RESTAURANT2_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createWithPlacement() throws Exception {
        if(itIsGoodTimeToMakeVote()) {
            Vote newVote = VoteTestData.getNew();
            ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL_SLASH + RESTAURANT3_ID + "/votes")
                    .contentType(MediaType.APPLICATION_JSON));

            Vote created = VOTE_MATCHER.readFromJson(action);
            int newId = created.id();
            newVote.setId(newId);
            VOTE_MATCHER.assertMatch(created, newVote);
            VOTE_MATCHER.assertMatch(voteRepository.getExisted(newId), newVote);
        }
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createOnWrongTime() throws Exception {
        if(!itIsGoodTimeToMakeVote()) {
            ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL_SLASH + RESTAURANT3_ID + "/votes")
                    .contentType(MediaType.APPLICATION_JSON));
            action.andExpect(status().isUnprocessableEntity());
        }
    }
}