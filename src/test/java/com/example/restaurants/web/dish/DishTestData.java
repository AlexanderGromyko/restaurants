package com.example.restaurants.web.dish;

import com.example.restaurants.web.MatcherFactory;
import com.example.restaurants.model.Dish;
import com.example.restaurants.to.DishTo;

import java.time.Month;
import java.util.List;

import static java.time.LocalDateTime.of;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");
    public static MatcherFactory.Matcher<DishTo> DISH_TO_MATCHER = MatcherFactory.usingEqualsComparator(DishTo.class);

    public static final int DISH1_ID = 1;
    public static final int ADMIN_MEAL_ID = 8;

    public static final Dish dish1 = new Dish(DISH1_ID, "Fried eggs", of(2023, Month.APRIL, 30, 10, 0), "3 fried eggs", 4000);
    public static final Dish dish2 = new Dish(DISH1_ID + 1, "Bread", of(2023, Month.APRIL, 30, 10, 0), "Plate of bread", 1000);
    public static final Dish dish3 = new Dish(DISH1_ID + 2, "Fried potato", of(2023, Month.APRIL, 30, 10, 0), "Just fries", 1500);
    public static final Dish dish4 = new Dish(DISH1_ID + 3, "Green tea", of(2023, Month.APRIL, 30, 10, 0), "Cup of green tea with mint", 2200);
    public static final Dish dish5 = new Dish(DISH1_ID + 4, "Americano", of(2023, Month.APRIL, 30, 10, 0), "Big cup of americano", 1400);
    public static final Dish dish6 = new Dish(DISH1_ID + 5, "Latte", of(2023, Month.APRIL, 30, 10, 0), "Medium cup of latte", 2000);
    public static final Dish dish7 = new Dish(DISH1_ID + 6, "Espresso", of(2023, Month.APRIL, 30, 10, 0), "Small cup of espresso", 900);
    public static final Dish dish8 = new Dish(DISH1_ID + 7, "Cappuccino", of(2023, Month.APRIL, 30, 10, 0), "Cappuccino with oat milk", 2200);
    public static final Dish dish9 = new Dish(DISH1_ID + 8, "Fish and chips", of(2023, Month.APRIL, 30, 10, 0), "Fish and chips from Great Britain", 6500);
    public static final Dish dish10 = new Dish(DISH1_ID + 9, "Burger", of(2023, Month.APRIL, 30, 10, 0), "Big Tasty from McDonalds", 2200);
    public static final Dish dish11 = new Dish(DISH1_ID + 10, "Beer 0.33", of(2023, Month.APRIL, 30, 10, 0), "Non-alcoholic beer", 3500);

    //    public static final Dish adminMeal1 = new Dish(ADMIN_MEAL_ID, of(2020, Month.JANUARY, 31, 14, 0), "Админ ланч", 510);
//    public static final Dish adminMeal2 = new Dish(ADMIN_MEAL_ID + 1, of(2020, Month.JANUARY, 31, 21, 0), "Админ ужин", 1500);

    //public static final List<Dish> dishes = List.of(dish7, dish6, dish5, dish4, dish3, dish2, dish1);

    public static final List<Dish> dishesOf2Restaurant = List.of(dish5, dish8, dish7, dish6);

    public static Dish getNew() {
        return new Dish(null, "Donut", of(2020, Month.FEBRUARY, 1, 18, 0), "Donut with chocolate", 300);
    }

//    public static Dish getUpdated() {
//        return new Dish(MEAL1_ID, dish1.getDateTime().plus(2, ChronoUnit.MINUTES), "Обновленный завтрак", 200);
//    }
}