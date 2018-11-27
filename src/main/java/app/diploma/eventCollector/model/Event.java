package app.diploma.eventCollector.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Event")
public class Event {
    private Integer idEvent;
    private List<Label> labelList;
    private Annotations annotations;
    private String startsAt;
    private String endsAt;
    private String generatorURL;
    private String state;
    private String fingerPrint;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEvent", nullable = false)
    public Integer getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Integer idEvent) {
        this.idEvent = idEvent;
    }
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Label> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<Label> labelList) {
        this.labelList = labelList;
    }

    @OneToOne()
    @JoinColumn(name = "idAnnotations", nullable = false)
    public Annotations getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Annotations annotations) {
        this.annotations = annotations;
    }
    @Basic
    @Column(name = "startsAt", nullable = false, length = 150)
    public String getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(String startsAt) {
        this.startsAt = startsAt;
    }
    @Basic
    @Column(name = "endsAt", nullable = false, length = 150)
    public String getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(String endsAt) {
        this.endsAt = endsAt;
    }
    @Basic
    @Column(name = "generatorURL", nullable = true, length = 254)
    public String getGeneratorURL() {
        return generatorURL;
    }

    public void setGeneratorURL(String generatorURL) {
        this.generatorURL = generatorURL;
    }
    @Basic
    @Column(name = "state", nullable = true, length = 150)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "fingerPrint", nullable = false, length = 150)
    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }
}
