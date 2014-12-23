/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.controller.exceptions.NonexistentEntityException;
import com.entities.Salary;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.entities.Tournament;
import com.entities.Staff;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author vinodkumar
 */
public class SalaryJpaController implements Serializable {

    public SalaryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Salary salary) {
        if (salary.getStaffList() == null) {
            salary.setStaffList(new ArrayList<Staff>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tournament tid = salary.getTid();
            if (tid != null) {
                tid = em.getReference(tid.getClass(), tid.getTid());
                salary.setTid(tid);
            }
            List<Staff> attachedStaffList = new ArrayList<Staff>();
            for (Staff staffListStaffToAttach : salary.getStaffList()) {
                staffListStaffToAttach = em.getReference(staffListStaffToAttach.getClass(), staffListStaffToAttach.getStaffId());
                attachedStaffList.add(staffListStaffToAttach);
            }
            salary.setStaffList(attachedStaffList);
            em.persist(salary);
            if (tid != null) {
                tid.getSalaryList().add(salary);
                tid = em.merge(tid);
            }
            for (Staff staffListStaff : salary.getStaffList()) {
                Salary oldRoleIdOfStaffListStaff = staffListStaff.getRoleId();
                staffListStaff.setRoleId(salary);
                staffListStaff = em.merge(staffListStaff);
                if (oldRoleIdOfStaffListStaff != null) {
                    oldRoleIdOfStaffListStaff.getStaffList().remove(staffListStaff);
                    oldRoleIdOfStaffListStaff = em.merge(oldRoleIdOfStaffListStaff);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Salary salary) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Salary persistentSalary = em.find(Salary.class, salary.getRoleId());
            Tournament tidOld = persistentSalary.getTid();
            Tournament tidNew = salary.getTid();
            List<Staff> staffListOld = persistentSalary.getStaffList();
            List<Staff> staffListNew = salary.getStaffList();
            if (tidNew != null) {
                tidNew = em.getReference(tidNew.getClass(), tidNew.getTid());
                salary.setTid(tidNew);
            }
            List<Staff> attachedStaffListNew = new ArrayList<Staff>();
            for (Staff staffListNewStaffToAttach : staffListNew) {
                staffListNewStaffToAttach = em.getReference(staffListNewStaffToAttach.getClass(), staffListNewStaffToAttach.getStaffId());
                attachedStaffListNew.add(staffListNewStaffToAttach);
            }
            staffListNew = attachedStaffListNew;
            salary.setStaffList(staffListNew);
            salary = em.merge(salary);
            if (tidOld != null && !tidOld.equals(tidNew)) {
                tidOld.getSalaryList().remove(salary);
                tidOld = em.merge(tidOld);
            }
            if (tidNew != null && !tidNew.equals(tidOld)) {
                tidNew.getSalaryList().add(salary);
                tidNew = em.merge(tidNew);
            }
            for (Staff staffListOldStaff : staffListOld) {
                if (!staffListNew.contains(staffListOldStaff)) {
                    staffListOldStaff.setRoleId(null);
                    staffListOldStaff = em.merge(staffListOldStaff);
                }
            }
            for (Staff staffListNewStaff : staffListNew) {
                if (!staffListOld.contains(staffListNewStaff)) {
                    Salary oldRoleIdOfStaffListNewStaff = staffListNewStaff.getRoleId();
                    staffListNewStaff.setRoleId(salary);
                    staffListNewStaff = em.merge(staffListNewStaff);
                    if (oldRoleIdOfStaffListNewStaff != null && !oldRoleIdOfStaffListNewStaff.equals(salary)) {
                        oldRoleIdOfStaffListNewStaff.getStaffList().remove(staffListNewStaff);
                        oldRoleIdOfStaffListNewStaff = em.merge(oldRoleIdOfStaffListNewStaff);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = salary.getRoleId();
                if (findSalary(id) == null) {
                    throw new NonexistentEntityException("The salary with id " + id + " no longer exists.");
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
            Salary salary;
            try {
                salary = em.getReference(Salary.class, id);
                salary.getRoleId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The salary with id " + id + " no longer exists.", enfe);
            }
            Tournament tid = salary.getTid();
            if (tid != null) {
                tid.getSalaryList().remove(salary);
                tid = em.merge(tid);
            }
            List<Staff> staffList = salary.getStaffList();
            for (Staff staffListStaff : staffList) {
                staffListStaff.setRoleId(null);
                staffListStaff = em.merge(staffListStaff);
            }
            em.remove(salary);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Salary> findSalaryEntities() {
        return findSalaryEntities(true, -1, -1);
    }

    public List<Salary> findSalaryEntities(int maxResults, int firstResult) {
        return findSalaryEntities(false, maxResults, firstResult);
    }

    private List<Salary> findSalaryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Salary.class));
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

    public Salary findSalary(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Salary.class, id);
        } finally {
            em.close();
        }
    }

    public int getSalaryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Salary> rt = cq.from(Salary.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
