/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.controller.exceptions.NonexistentEntityException;
import com.entities.Draw;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.entities.Tournament;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author vinodkumar
 */
public class DrawJpaController implements Serializable {

    public DrawJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Draw draw) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tournament tid = draw.getTid();
            if (tid != null) {
                tid = em.getReference(tid.getClass(), tid.getTid());
                draw.setTid(tid);
            }
            em.persist(draw);
            if (tid != null) {
                tid.getDrawList().add(draw);
                tid = em.merge(tid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Draw draw) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Draw persistentDraw = em.find(Draw.class, draw.getMatchno());
            Tournament tidOld = persistentDraw.getTid();
            Tournament tidNew = draw.getTid();
            if (tidNew != null) {
                tidNew = em.getReference(tidNew.getClass(), tidNew.getTid());
                draw.setTid(tidNew);
            }
            draw = em.merge(draw);
            if (tidOld != null && !tidOld.equals(tidNew)) {
                tidOld.getDrawList().remove(draw);
                tidOld = em.merge(tidOld);
            }
            if (tidNew != null && !tidNew.equals(tidOld)) {
                tidNew.getDrawList().add(draw);
                tidNew = em.merge(tidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = draw.getMatchno();
                if (findDraw(id) == null) {
                    throw new NonexistentEntityException("The draw with id " + id + " no longer exists.");
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
            Draw draw;
            try {
                draw = em.getReference(Draw.class, id);
                draw.getMatchno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The draw with id " + id + " no longer exists.", enfe);
            }
            Tournament tid = draw.getTid();
            if (tid != null) {
                tid.getDrawList().remove(draw);
                tid = em.merge(tid);
            }
            em.remove(draw);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Draw> findDrawEntities() {
        return findDrawEntities(true, -1, -1);
    }

    public List<Draw> findDrawEntities(int maxResults, int firstResult) {
        return findDrawEntities(false, maxResults, firstResult);
    }

    private List<Draw> findDrawEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Draw.class));
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

    public Draw findDraw(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Draw.class, id);
        } finally {
            em.close();
        }
    }

    public int getDrawCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Draw> rt = cq.from(Draw.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
