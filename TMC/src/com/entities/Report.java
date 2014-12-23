/*
  * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.entities;

import com.entities.Tournament;
import com.entities.User;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic; 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nitesh
 */
@Entity
@Table(name = "report")
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r"),
    @NamedQuery(name = "Report.findByReportid", query = "SELECT r FROM Report r WHERE r.reportid = :reportid"),
    @NamedQuery(name = "Report.findByAction", query = "SELECT r FROM Report r WHERE r.action = :action"),
    @NamedQuery(name = "Report.findByPerformedName", query = "SELECT r FROM Report r WHERE r.performedName = :performedName"),
    @NamedQuery(name = "Report.findByPerformedDate", query = "SELECT r FROM Report r WHERE r.performedDate = :performedDate")})
public class Report implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "REPORTID")
    private Integer reportid;
    @Basic(optional = false)
    @Column(name = "ACTION")
    private String action;
    @Basic(optional = false)
    @Column(name = "PERFORMED_NAME")
    private String performedName;
    @Basic(optional = false)
    @Column(name = "PERFORMED_DATE")
    @Temporal(TemporalType.DATE)
    private Date performedDate;
    @JoinColumn(name = "TID", referencedColumnName = "TID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tournament tid;
    @JoinColumn(name = "PERFORMED_BY", referencedColumnName = "USERID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User performedBy;

    public Report() {
    }

    public Report(Integer reportid) {
        this.reportid = reportid;
    }

    public Report(Integer reportid, String action, String performedName, Date performedDate) {
        this.reportid = reportid;
        this.action = action;
        this.performedName = performedName;
        this.performedDate = performedDate;
    }

    public Integer getReportid() {
        return reportid;
    }

    public void setReportid(Integer reportid) {
        this.reportid = reportid;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPerformedName() {
        return performedName;
    }

    public void setPerformedName(String performedName) {
        this.performedName = performedName;
    }

    public Date getPerformedDate() {
        return performedDate;
    }

    public void setPerformedDate(Date performedDate) {
        this.performedDate = performedDate;
    }

    public Tournament getTid() {
        return tid;
    }

    public void setTid(Tournament tid) {
        this.tid = tid;
    }

    public User getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(User performedBy) {
        this.performedBy = performedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportid != null ? reportid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.reportid == null && other.reportid != null) || (this.reportid != null && !this.reportid.equals(other.reportid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.controller.Report[ reportid=" + reportid + " ]";
    }
    
}
