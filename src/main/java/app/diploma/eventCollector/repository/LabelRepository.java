package app.diploma.eventCollector.repository;

import app.diploma.eventCollector.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("labelRepository")
public interface LabelRepository extends JpaRepository<Label, Integer> {
}
