package app.diploma.eventCollector.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "AnnotationsRepository")
public class Annotations {
    private Integer idAnnotations;
    private String description;
    private String summary;
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAnnotations", nullable = false)
    public Integer getIdAnnotations() {
        return idAnnotations;
    }

    public void setIdAnnotations(Integer idAnnotations) {
        this.idAnnotations = idAnnotations;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 250)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "summary", nullable = false, length = 250)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
