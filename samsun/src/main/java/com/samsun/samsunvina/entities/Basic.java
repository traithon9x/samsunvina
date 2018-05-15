package com.samsun.samsunvina.entities;

import com.samsun.samsunvina.constants.DatabaseConstants;
import com.samsun.samsunvina.enumerations.Status;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class Basic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = DatabaseConstants.TIMESTAMP_DEFAULT_CURRENT_TIMESTAMP)
    protected Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = DatabaseConstants.TIMESTAMP_DEFAULT_CURRENT_TIMESTAMP_LAST_UPDATED)
    protected Date lastUpdatedDate;

    @Column(nullable = false, columnDefinition = DatabaseConstants.STATUS_ACTIVE)
    @Enumerated(EnumType.STRING)
    protected Status status;

    public Date getCreatedDate() {
        return createdDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
