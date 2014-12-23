/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vinodkumar
 */
@Entity
@Table(name = "tournament")
@NamedQueries({
    @NamedQuery(name = "Tournament.findAll", query = "SELECT t FROM Tournament t"),
    @NamedQuery(name = "Tournament.findByTid", query = "SELECT t FROM Tournament t WHERE t.tid = :tid"),
    @NamedQuery(name = "Tournament.findByTname", query = "SELECT t FROM Tournament t WHERE t.tname = :tname"),
    @NamedQuery(name = "Tournament.findByType", query = "SELECT t FROM Tournament t WHERE t.type = :type"),
    @NamedQuery(name = "Tournament.findByStartdate", query = "SELECT t FROM Tournament t WHERE t.startdate = :startdate"),
    @NamedQuery(name = "Tournament.findByEnddate", query = "SELECT t FROM Tournament t WHERE t.enddate = :enddate"),
    @NamedQuery(name = "Tournament.findByStatus", query = "SELECT t FROM Tournament t WHERE t.status = :status")})
public class Tournament implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tid", fetch = FetchType.LAZY)
    private List<Report> reportList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TID")
    private Integer tid;
    @Basic(optional = false)
    @Column(name = "TNAME")
    private String tname;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private String type;
    @Basic(optional = false)
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Basic(optional = false)
    @Column(name = "ENDDATE")
    @Temporal(TemporalType.DATE)
    private Date enddate;
    @Column(name = "STATUS")
    private String status;
    @OneToMany(mappedBy = "tid", fetch = FetchType.LAZY)
    private List<Staff> staffList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tid", fetch = FetchType.LAZY)
    private List<Draw> drawList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tid", fetch = FetchType.LAZY)
    private List<Team> teamList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tid", fetch = FetchType.LAZY)
    private List<Salary> salaryList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tid", fetch = FetchType.LAZY)
    private List<User> userList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tid", fetch = FetchType.LAZY)
    private List<Transaction> transactionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tid", fetch = FetchType.LAZY)
    private List<Player> playerList;

    public Tournament() {
    }

    public Tournament(Integer tid) {
        this.tid = tid;
    }

    public Tournament(Integer tid, String tname, String type, Date startdate, Date enddate) {
        this.tid = tid;
        this.tname = tname;
        this.type = type;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public List<Draw> getDrawList() {
        return drawList;
    }

    public void setDrawList(List<Draw> drawList) {
        this.drawList = drawList;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public List<Salary> getSalaryList() {
        return salaryList;
    }

    public void setSalaryList(List<Salary> salaryList) {
        this.salaryList = salaryList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tid != null ? tid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tournament)) {
            return false;
        }
        Tournament other = (Tournament) object;
        if ((this.tid == null && other.tid != null) || (this.tid != null && !this.tid.equals(other.tid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Tournament[ tid=" + tid + " ]";
    }

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }
    
}
