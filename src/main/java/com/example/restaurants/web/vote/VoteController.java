package com.example.restaurants.web.vote;

import com.example.restaurants.model.Vote;
import com.example.restaurants.repository.VoteRepository;
import com.example.restaurants.service.VoteService;
import com.example.restaurants.web.AuthUser;
import com.example.restaurants.web.restaurant.RestaurantController;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VoteController {
    public static final String REST_URL = RestaurantController.REST_URL + "/{restaurantId}/votes";

    @Autowired
    private final VoteRepository repository;

    private final VoteService service;

    @GetMapping
    public ResponseEntity<Vote> get(@AuthenticationPrincipal AuthUser authUser, @PathVariable int restaurantId) {
        log.info("get vote for userId {} and restaurantId {}", authUser.id(), restaurantId);
        return ResponseEntity.of(repository.get(authUser.id(), restaurantId, LocalDate.now()));
    }

    @Operation(summary = "note: only owner can delete his own vote and only before 11:00:00")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser authUser, @PathVariable int restaurantId) {
        log.info("delete vote of userId {} and restaurantId {}", authUser.id(), restaurantId);
        service.delete(authUser.id(), restaurantId, LocalDate.now());
    }

    @Operation(summary = "note: owner can vote only for himself and only before 11:00:00")
    @PostMapping
    public ResponseEntity<Vote> createWithPlacement(@AuthenticationPrincipal AuthUser authUser, @PathVariable int restaurantId) {
        log.info("create vote for userId {} and restaurantId {}", authUser.getUser(), restaurantId);
        Vote created = service.save(authUser.getUser(), restaurantId, LocalDate.now());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
