package com.github.alexandergromyko.restaurants.web.restaurant;

import com.github.alexandergromyko.restaurants.util.RestaurantsUtil;
import com.github.alexandergromyko.restaurants.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.alexandergromyko.restaurants.web.user.UserTestData.USER_MAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RestaurantControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = RestaurantTestData.REST_URL_USER + '/';

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + RestaurantTestData.RESTAURANT2_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RestaurantTestData.RESTAURANT_TO_MATCHER.contentJson(RestaurantTestData.restaurantTo2));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(RestaurantTestData.REST_URL_USER))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RestaurantTestData.RESTAURANT_TO_MATCHER.contentJson(RestaurantsUtil.getTos(RestaurantTestData.enabledRestaurants)));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + RestaurantTestData.RESTAURANT999_ID))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + RestaurantTestData.RESTAURANT2_ID))
                .andExpect(status().isUnauthorized());
    }
}
