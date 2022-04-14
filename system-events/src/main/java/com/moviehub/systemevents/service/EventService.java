package com.moviehub.systemevents.service;


import com.moviehub.systemevents.event.EventRequest;
import com.moviehub.systemevents.event.EventResponse;
import com.moviehub.systemevents.event.EventServiceGrpc;
import com.moviehub.systemevents.model.Event;
import com.moviehub.systemevents.model.enums.ActionType;
import com.moviehub.systemevents.repository.EventRepository;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class EventService extends EventServiceGrpc.EventServiceImplBase {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event add(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void log(EventRequest request, StreamObserver<EventResponse> responseObserver) {
        Event event = eventRepository.save(new Event(
                request.getMicroservice(),
                request.getUser(),
                ActionType.valueOf(request.getAction().name()),
                request.getResource(),
                Integer.parseInt(request.getStatus())
        ));

        EventResponse response = EventResponse.newBuilder()
                .setMessage(event.toString())
                .setStatus("1")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
