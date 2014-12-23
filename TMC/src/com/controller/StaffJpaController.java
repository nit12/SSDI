/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.entities.Salary;
import com.entities.Staff;
import com.entities.Team;
import com.entities.Tournament;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author vinodkumar
 */
public class StaffJpaController implements Serializable {

    public StaffJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Staff staff) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Salary roleId = staff.getRoleId();
            if (roleId != null) {
                roleId = em.getReference(roleId.getClass(), roleId.getRoleId());
                staff.setRoleId(roleId);
            }
            Team teamId = staff.getTeamId();
            if (teamId != null) {
                teamId = em.getReference(teamId.getClass(), teamId.getTeamid());
                staff.setTeamId(teamId);
            }
            Tournament tid = staff.getTid();
            if (tid != null) {
                tid = em.getReference(tid.getClass(), tid.getTid());
                staff.setTid(tid);
            }
            em.persist(staff);
            if (roleId != null) {
                roleId.getStaffList().add(staff);
                roleId = em.merge(roleId);
            }
            if (teamId != null) {
                teamId.getStaffList().add(staff);
                teamId = em.merge(teamId);
            }
            if (tid != null) {
                tid.getStaffList().add(staff);
                tid = em.merge(tid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Staff staff) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Staff persistentStaff = em.find(Staff.class, staff.getStaffId());
            Salary roleIdOld = persistentStaff.getRoleId();
            Salary roleIdNew = staff.getRoleId();
            Team teamIdOld = persistentStaff.getTeamId();
            Team teamIdNew = staff.getTeamId();
            Tournament tidOld = persistentStaff.getTid();
            Tournament tidNew = staff.getTid();
            if (roleIdNew != null) {
                roleIdNew = em.getReference(roleIdNew.getClass(), roleIdNew.getRoleId());
                staff.setRoleId(roleIdNew);
            }
            if (teamIdNew != null) {
                teamIdNew = em.getReference(teamIdNew.getClass(), teamIdNew.getTeamid());
                staff.setTeamId(teamIdNew);
            }
            if (tidNew != null) {
                tidNew = em.getReference(tidNew.getClass(), tidNew.getTid());
                staff.setTid(tidNew);
            }
            staff = em.merge(staff);
            if (roleIdOld != null && !roleIdOld.equals(roleIdNew)) {
                roleIdOld.getStaffList().remove(staff);
                roleIdOld = em.merge(roleIdOld);
            }
            if (roleIdNew != null && !roleIdNew.equals(roleIdOld)) {
                roleIdNew.getStaffList().add(staff);
                roleIdNew = em.merge(roleIdNew);
            }
            if (teamIdOld != null && !teamIdOld.equals(teamIdNew)) {
                teamIdOld.getStaffList().remove(staff);
                teamIdOld = em.merge(teamIdOld);
            }
            if (teamIdNew != null && !teamIdNew.equals(teamIdOld)) {
                teamIdNew.getStaffList().add(staff);
                teamIdNew = em.merge(teamIdNew);
            }
            if (tidOld != null && !tidOld.equals(tidNew)) {
                tidOld.getStaffList().remove(staff);
                tidOld = em.merge(tidOld);
            }
            if (tidNew != null && !tidNew.equals(tidOld)) {
                tidNew.getStaffList().add(staff);
                tidNew = em.merge(tidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = staff.getStaffId();
                if (findStaff(id) == null) {
                    throw new NonexistentEntityException("The staff with id " + id + " no longer exists.");
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
            Staff staff;
            try {
                staff = em.getReference(Staff.class, id);
                staff.getStaffId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The staff with id " + id + " no longer exists.", enfe);
            }
            Salary roleId = staff.getRoleId();
            if (roleId != null) {
                roleId.getStaffList().remove(staff);
                roleId = em.merge(roleId);
            }
            Team teamId = staff.getTeamId();
            if (teamId != null) {
                teamId.getStaffList().remove(staff);
                teamId = em.merge(teamId);
            }
            Tournament tid = staff.getTid();
            if (tid != null) {
                tid.getStaffList().remove(staff);
                tid = em.merge(tid);
            }
            em.remove(staff);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Staff> findStaffEntities() {
        return findStaffEntities(true, -1, -1);
    }

    public List<Staff> findStaffEntities(int maxResults, int firstResult) {
        return findStaffEntities(false, maxResults, firstResult);
    }

    private List<Staff> findStaffEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Staff.class));
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

    public Staff findStaff(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Staff.class, id);
        } finally {
            em.close();
        }
    }

    public int getStaffCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Staff> rt = cq.from(Staff.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
