package com.github.alexandergromyko.restaurants.web.dish;

import com.github.alexandergromyko.restaurants.model.Dish;
import com.github.alexandergromyko.restaurants.repository.DishRepository;
import com.github.alexandergromyko.restaurants.util.JsonUtil;
import com.github.alexandergromyko.restaurants.web.AbstractControllerTest;
import com.github.alexandergromyko.restaurants.web.restaurant.RestaurantTestData;
import com.github.alexandergromyko.restaurants.util.DishesUtil;
import com.github.alexandergromyko.restaurants.web.vote.VoteTestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.alexandergromyko.restaurants.web.user.UserTestData.ADMIN_MAIL;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminDishControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = DishTestData.REST_URL_ADMIN + '/';

    @Autowired
    private DishRepository dishRepository;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + DishTestData.DISH5_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DishTestData.DISH_MATCHER.contentJson(DishTestData.dish5));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(DishTestData.REST_URL_ADMIN))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DishTestData.DISH_TO_MATCHER.contentJson(DishesUtil.getTos(DishTestData.dishesOf2Restaurant)));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAllOnYesterday() throws Exception {
        perform(MockMvcRequestBuilders.get(DishTestData.REST_URL_ADMIN + VoteTestData.YESTERDAY_STRING_PARAMETER))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DishTestData.DISH_TO_MATCHER.contentJson(DishesUtil.getTos(DishTestData.dishesOnYesterday)));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + DishTestData.DISH1_ID))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getWithoutAuthorisation() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + DishTestData.DISH5_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + DishTestData.DISH5_ID))
                .andExpect(status().isNoContent());
        assertFalse(dishRepository.get(RestaurantTestData.RESTAURANT2_ID, DishTestData.DISH5_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        Dish updated = DishTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + DishTestData.DISH5_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        DishTestData.DISH_MATCHER.assertMatch(dishRepository.getExisted(DishTestData.DISH5_ID), updated);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithPlacement() throws Exception {
        Dish newDish = DishTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(DishTestData.REST_URL_ADMIN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)));

        Dish created = DishTestData.DISH_MATCHER.readFromJson(action);
        int newId = created.id();
        newDish.setId(newId);
        DishTestData.DISH_MATCHER.assertMatch(created, newDish);
        DishTestData.DISH_MATCHER.assertMatch(dishRepository.getExisted(newId), newDish);
    }
}