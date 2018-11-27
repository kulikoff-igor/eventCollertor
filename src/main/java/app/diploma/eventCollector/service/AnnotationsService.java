package app.diploma.eventCollector.service;

import app.diploma.eventCollector.model.Annotations;
import app.diploma.eventCollector.repository.AnnotationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("annotationsService")
public class AnnotationsService {
    @Autowired
    private AnnotationsRepository annotationsRepository;

    public void addAnnotations(Annotations annotations) {
        annotationsRepository.saveAndFlush(annotations);
    }
}
