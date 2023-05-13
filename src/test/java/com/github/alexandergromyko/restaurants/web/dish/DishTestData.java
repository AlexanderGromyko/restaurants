package com.github.alexandergromyko.restaurants.web.dish;

import com.github.alexandergromyko.restaurants.model.Dish;
import com.github.alexandergromyko.restaurants.to.DishTo;
import com.github.alexandergromyko.restaurants.web.MatcherFactory;
import com.github.alexandergromyko.restaurants.web.restaurant.AdminRestaurantController;
import com.github.alexandergromyko.restaurants.web.restaurant.RestaurantController;

import java.time.LocalDate;
import java.util.List;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");
    public static MatcherFactory.Matcher<DishTo> DISH_TO_MATCHER = MatcherFactory.usingEqualsComparator(DishTo.class);

    public static final String REST_URL_USER = RestaurantController.REST_URL + "/2/dishes";
    public static final String REST_URL_ADMIN = AdminRestaurantController.REST_URL + "/2/dishes";

    public static final int DISH1_ID = 1;
    public static final int DISH5_ID = 5;

    public static final Dish dish1 = new Dish(DISH1_ID, "Fried eggs", LocalDate.now(), "3 fried eggs", 4000);
    public static final Dish dish2 = new Dish(DISH1_ID + 1, "Bread", LocalDate.now(), "Plate of bread", 1000);
    public static final Dish dish3 = new Dish(DISH1_ID + 2, "Fried potato", LocalDate.now(), "Just fries", 1500);
    public static final Dish dish4 = new Dish(DISH1_ID + 3, "Green tea", LocalDate.now(), "Cup of green tea with mint", 2200);
    public static final Dish dish5 = new Dish(DISH1_ID + 4, "Americano", LocalDate.now(), "Big cup of americano", 1400);
    public static final Dish dish6 = new Dish(DISH1_ID + 5, "Latte", LocalDate.now(), "Medium cup of latte", 2000);
    public static final Dish dish7 = new Dish(DISH1_ID + 6, "Espresso", LocalDate.now(), "Small cup of espresso", 900);
    public static final Dish dish8 = new Dish(DISH1_ID + 7, "Cappuccino", LocalDate.now(), "Cappuccino with oat milk", 2200);
    public static final Dish dish9 = new Dish(DISH1_ID + 8, "Fish and chips", LocalDate.now(), "Fish and chips from Great Britain", 6500);
    public static final Dish dish10 = new Dish(DISH1_ID + 9, "Burger", LocalDate.now(), "Big Tasty from McDonalds", 2200);
    public static final Dish dish11 = new Dish(DISH1_ID + 10, "Beer 0.33", LocalDate.now(), "Non-alcoholic beer", 3500);
    public static final Dish dish12 = new Dish(DISH1_ID + 15, "Americano", LocalDate.now().minusDays(1), "Big cup of americano", 2400);
    public static final Dish dish13 = new Dish(DISH1_ID + 16, "Latte", LocalDate.now().minusDays(1), "Medium cup of latte", 3000);
    public static final Dish dish14 = new Dish(DISH1_ID + 17, "Espresso", LocalDate.now().minusDays(1), "Small cup of espresso", 1900);
    public static final Dish dish15 = new Dish(DISH1_ID + 18, "Cappuccino", LocalDate.now().minusDays(1), "Cappuccino with oat milk", 3200);
    public static final List<Dish> dishesOf2Restaurant = List.of(dish5, dish8, dish7, dish6);
    public static final List<Dish> dishesOnYesterday = List.of(dish12, dish15, dish14, dish13);

    public static Dish getNew() {
        return new Dish(null, "new name", LocalDate.now(), "new description", 777);
    }

    public static Dish getUpdated() {
        return new Dish(DISH5_ID, "updated name",  LocalDate.now().plusDays(1), "updated description", 123);
    }
}