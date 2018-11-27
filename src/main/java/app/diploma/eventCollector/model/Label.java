package app.diploma.eventCollector.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Label")
public class Label {
    private Integer idLabel;
    private Event event;
    private String labelName;
    private String labelData;
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLabel", nullable = false)
    public Integer getIdLabel() {
        return idLabel;
    }

    public void setIdLabel(Integer idLabel) {
        this.idLabel = idLabel;
    }

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "idEvent", nullable = false)
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Basic
    @Column(name = "labelName", nullable = true, length = 150)
    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    @Basic
    @Column(name = "labelData", nullable = true, length = 150)
    public String getLabelData() {
        return labelData;
    }

    public void setLabelData(String labelData) {
        this.labelData = labelData;
    }
}
