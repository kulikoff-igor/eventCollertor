package app.diploma.eventCollector.service;

import app.diploma.eventCollector.model.Label;
import app.diploma.eventCollector.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("labelService")
public class LabelService {
    @Autowired
    private LabelRepository labelRepository;

    public void addLabel(Label label) {
        labelRepository.saveAndFlush(label);
    }
}
