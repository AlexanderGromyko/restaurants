package com.github.alexandergromyko.restaurants.web.vote;

import com.github.alexandergromyko.restaurants.service.VoteService;
import com.github.alexandergromyko.restaurants.to.VoteTo;
import com.github.alexandergromyko.restaurants.web.AuthUser;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VoteController {
    public static final String REST_URL = "/api/today-votes";
    public static final String GOOD_TIME_TO_VOTE_STRING = "11:00:00";
    private final VoteService service;

    @GetMapping
    public ResponseEntity<VoteTo> get(@AuthenticationPrincipal AuthUser authUser) {
        log.info("get vote for userId {}", authUser.id());
        return service.get(authUser.id(), LocalDate.now());
    }

    @Operation(summary = "note: only owner can delete his vote only before " + GOOD_TIME_TO_VOTE_STRING)
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser authUser) {
        log.info("delete vote of userId {}", authUser.id());
        service.delete(authUser.id());
    }

    @Operation(summary = "note: user can change his vote only before " + GOOD_TIME_TO_VOTE_STRING)
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody VoteTo voteTo) {
        log.info("update vote for userId {} and restaurantId {}", authUser.getUser(), voteTo.getRestaurantId());
        service.update(authUser.getUser(), voteTo.getRestaurantId());
    }

    @Operation(summary = "note: user can vote only for one restaurant")
    @PostMapping
    public VoteTo createWithPlacement(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody VoteTo voteTo) {
        log.info("create vote for userId {} and restaurantId {}", authUser.getUser(), voteTo.getRestaurantId());
        return service.save(authUser.getUser(), voteTo.getRestaurantId());
    }
}
