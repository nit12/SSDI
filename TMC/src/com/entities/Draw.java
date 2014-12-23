/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author vinodkumar
 */
@Entity
@Table(name = "draw")
@NamedQueries({
    @NamedQuery(name = "Draw.findAll", query = "SELECT d FROM Draw d"),
    @NamedQuery(name = "Draw.findByMatchno", query = "SELECT d FROM Draw d WHERE d.matchno = :matchno"),
    @NamedQuery(name = "Draw.findByTeam1Id", query = "SELECT d FROM Draw d WHERE d.team1Id = :team1Id"),
    @NamedQuery(name = "Draw.findByTeam2Id", query = "SELECT d FROM Draw d WHERE d.team2Id = :team2Id"),
    @NamedQuery(name = "Draw.findByVenue", query = "SELECT d FROM Draw d WHERE d.venue = :venue"),
    @NamedQuery(name = "Draw.findByMatchDate", query = "SELECT d FROM Draw d WHERE d.matchDate = :matchDate"),
    @NamedQuery(name = "Draw.findByResult", query = "SELECT d FROM Draw d WHERE d.result = :result"),
    @NamedQuery(name = "Draw.findByRoundNo", query = "SELECT d FROM Draw d WHERE d.roundNo = :roundNo"),
    @NamedQuery(name = "Draw.findByComments", query = "SELECT d FROM Draw d WHERE d.comments = :comments")})
public class Draw implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MATCHNO")
    private Integer matchno;
    @Basic(optional = false)
    @Column(name = "TEAM1_ID")
    private String team1Id;
    @Basic(optional = false)
    @Column(name = "TEAM2_ID")
    private String team2Id;
    @Column(name = "VENUE")
    private String venue;
    @Column(name = "MATCH_DATE")
    @Temporal(TemporalType.DATE)
    private Date matchDate;
    @Column(name = "RESULT")
    private Character result;
    @Basic(optional = false)
    @Column(name = "ROUND_NO")
    private int roundNo;
    @Column(name = "COMMENTS")
    private String comments;
    @JoinColumn(name = "TID", referencedColumnName = "TID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tournament tid;

    public Draw() {
    }

    public Draw(Integer matchno) {
        this.matchno = matchno;
    }

    public Draw(Integer matchno, String team1Id, String team2Id, int roundNo) {
        this.matchno = matchno;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.roundNo = roundNo;
    }

    public Integer getMatchno() {
        return matchno;
    }

    public void setMatchno(Integer matchno) {
        this.matchno = matchno;
    }

    public String getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(String team1Id) {
        this.team1Id = team1Id;
    }

    public String getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(String team2Id) {
        this.team2Id = team2Id;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public Character getResult() {
        return result;
    }

    public void setResult(Character result) {
        this.result = result;
    }

    public int getRoundNo() {
        return roundNo;
    }

    public void setRoundNo(int roundNo) {
        this.roundNo = roundNo;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Tournament getTid() {
        return tid;
    }

    public void setTid(Tournament tid) {
        this.tid = tid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matchno != null ? matchno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Draw)) {
            return false;
        }
        Draw other = (Draw) object;
        if ((this.matchno == null && other.matchno != null) || (this.matchno != null && !this.matchno.equals(other.matchno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Draw[ matchno=" + matchno + " ]";
    }
    
}
