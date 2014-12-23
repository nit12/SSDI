package com.controller;

import com.controller.exceptions.NonexistentEntityException;
import com.controller.exceptions.PreexistingEntityException;
import com.entities.Report;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.entities.Tournament;
import com.entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nitesh
 */
public class ReportJpaController implements Serializable {

    public ReportJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Report report) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tournament tid = report.getTid();
            if (tid != null) {
                tid = em.getReference(tid.getClass(), tid.getTid());
                report.setTid(tid);
            }
            User performedBy = report.getPerformedBy();
            if (performedBy != null) {
                performedBy = em.getReference(performedBy.getClass(), performedBy.getUserid());
                report.setPerformedBy(performedBy);
            }
            em.persist(report);
            if (tid != null) {
                tid.getReportList().add(report);
                tid = em.merge(tid);
            }
            if (performedBy != null) {
                performedBy.getReportList().add(report);
                performedBy = em.merge(performedBy);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReport(report.getReportid()) != null) {
                throw new PreexistingEntityException("Report " + report + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Report report) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Report persistentReport = em.find(Report.class, report.getReportid());
            Tournament tidOld = persistentReport.getTid();
            Tournament tidNew = report.getTid();
            User performedByOld = persistentReport.getPerformedBy();
            User performedByNew = report.getPerformedBy();
            if (tidNew != null) {
                tidNew = em.getReference(tidNew.getClass(), tidNew.getTid());
                report.setTid(tidNew);
            }
            if (performedByNew != null) {
                performedByNew = em.getReference(performedByNew.getClass(), performedByNew.getUserid());
                report.setPerformedBy(performedByNew);
            }
            report = em.merge(report);
            if (tidOld != null && !tidOld.equals(tidNew)) {
                tidOld.getReportList().remove(report);
                tidOld = em.merge(tidOld);
            }
            if (tidNew != null && !tidNew.equals(tidOld)) {
                tidNew.getReportList().add(report);
                tidNew = em.merge(tidNew);
            }
            if (performedByOld != null && !performedByOld.equals(performedByNew)) {
                performedByOld.getReportList().remove(report);
                performedByOld = em.merge(performedByOld);
            }
            if (performedByNew != null && !performedByNew.equals(performedByOld)) {
                performedByNew.getReportList().add(report);
                performedByNew = em.merge(performedByNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = report.getReportid();
                if (findReport(id) == null) {
                    throw new NonexistentEntityException("The report with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Report report;
            try {
                report = em.getReference(Report.class, id);
                report.getReportid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The report with id " + id + " no longer exists.", enfe);
            }
            Tournament tid = report.getTid();
            if (tid != null) {
                tid.getReportList().remove(report);
                tid = em.merge(tid);
            }
            User performedBy = report.getPerformedBy();
            if (performedBy != null) {
                performedBy.getReportList().remove(report);
                performedBy = em.merge(performedBy);
            }
            em.remove(report);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Report> findReportEntities() {
        return findReportEntities(true, -1, -1);
    }

    public List<Report> findReportEntities(int maxResults, int firstResult) {
        return findReportEntities(false, maxResults, firstResult);
    }

    private List<Report> findReportEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Report.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Report findReport(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Report.class, id);
        } finally {
            em.close();
        }
    }

    public int getReportCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Report> rt = cq.from(Report.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}