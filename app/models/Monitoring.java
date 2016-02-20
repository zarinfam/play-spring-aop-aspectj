package models;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by saeed on 12/22/15.
 */

@Entity
public class Monitoring {

    @Id
    @GeneratedValue
    private Long id;
    private Long requestId;
    private String rawData;

    public Monitoring() {
    }

    public Monitoring(String rawData) {
        this.rawData = rawData;
    }

    public Monitoring(Long requestId, String rawData) {
        this.requestId = requestId;
        this.rawData = rawData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }
}
