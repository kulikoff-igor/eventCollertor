package app.diploma.eventCollector.repository;

import app.diploma.eventCollector.model.Annotations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("annotationsRepository")
public interface AnnotationsRepository extends JpaRepository<Annotations, Integer> {
}
