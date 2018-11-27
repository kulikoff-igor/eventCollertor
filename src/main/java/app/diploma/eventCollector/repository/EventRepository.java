package app.diploma.eventCollector.repository;

import app.diploma.eventCollector.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("eventRepository")
public interface EventRepository extends JpaRepository<Event, Integer> {
    Boolean existsByFingerPrint(String fingerPrint);

}
