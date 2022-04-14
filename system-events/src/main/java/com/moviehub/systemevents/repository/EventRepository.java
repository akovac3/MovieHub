package com.moviehub.systemevents.repository;

import com.moviehub.systemevents.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, Long> {
}
