/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.controller.exceptions.IllegalOrphanException;
import com.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.entities.Tournament;
import com.entities.Staff;
import java.util.ArrayList;
import java.util.List;
import com.entities.Player;
import com.entities.Team;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author vinodkumar
 */
public class TeamJpaController implements Serializable {

    public TeamJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Team team) {
        if (team.getStaffList() == null) {
            team.setStaffList(new ArrayList<Staff>());
        }
        if (team.getPlayerList() == null) {
            team.setPlayerList(new ArrayList<Player>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tournament tid = team.getTid();
            if (tid != null) {
                tid = em.getReference(tid.getClass(), tid.getTid());
                team.setTid(tid);
            }
            List<Staff> attachedStaffList = new ArrayList<Staff>();
            for (Staff staffListStaffToAttach : team.getStaffList()) {
                staffListStaffToAttach = em.getReference(staffListStaffToAttach.getClass(), staffListStaffToAttach.getStaffId());
                attachedStaffList.add(staffListStaffToAttach);
            }
            team.setStaffList(attachedStaffList);
            List<Player> attachedPlayerList = new ArrayList<Player>();
            for (Player playerListPlayerToAttach : team.getPlayerList()) {
                playerListPlayerToAttach = em.getReference(playerListPlayerToAttach.getClass(), playerListPlayerToAttach.getPlayerId());
                attachedPlayerList.add(playerListPlayerToAttach);
            }
            team.setPlayerList(attachedPlayerList);
            em.persist(team);
            if (tid != null) {
                tid.getTeamList().add(team);
                tid = em.merge(tid);
            }
            for (Staff staffListStaff : team.getStaffList()) {
                Team oldTeamIdOfStaffListStaff = staffListStaff.getTeamId();
                staffListStaff.setTeamId(team);
                staffListStaff = em.merge(staffListStaff);
                if (oldTeamIdOfStaffListStaff != null) {
                    oldTeamIdOfStaffListStaff.getStaffList().remove(staffListStaff);
                    oldTeamIdOfStaffListStaff = em.merge(oldTeamIdOfStaffListStaff);
                }
            }
            for (Player playerListPlayer : team.getPlayerList()) {
                Team oldTeamIdOfPlayerListPlayer = playerListPlayer.getTeamId();
                playerListPlayer.setTeamId(team);
                playerListPlayer = em.merge(playerListPlayer);
                if (oldTeamIdOfPlayerListPlayer != null) {
                    oldTeamIdOfPlayerListPlayer.getPlayerList().remove(playerListPlayer);
                    oldTeamIdOfPlayerListPlayer = em.merge(oldTeamIdOfPlayerListPlayer);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Team team) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Team persistentTeam = em.find(Team.class, team.getTeamid());
            Tournament tidOld = persistentTeam.getTid();
            Tournament tidNew = team.getTid();
            List<Staff> staffListOld = persistentTeam.getStaffList();
            List<Staff> staffListNew = team.getStaffList();
            List<Player> playerListOld = persistentTeam.getPlayerList();
            List<Player> playerListNew = team.getPlayerList();
            List<String> illegalOrphanMessages = null;
            for (Player playerListOldPlayer : playerListOld) {
                if (!playerListNew.contains(playerListOldPlayer)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Player " + playerListOldPlayer + " since its teamId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tidNew != null) {
                tidNew = em.getReference(tidNew.getClass(), tidNew.getTid());
                team.setTid(tidNew);
            }
            List<Staff> attachedStaffListNew = new ArrayList<Staff>();
            for (Staff staffListNewStaffToAttach : staffListNew) {
                staffListNewStaffToAttach = em.getReference(staffListNewStaffToAttach.getClass(), staffListNewStaffToAttach.getStaffId());
                attachedStaffListNew.add(staffListNewStaffToAttach);
            }
            staffListNew = attachedStaffListNew;
            team.setStaffList(staffListNew);
            List<Player> attachedPlayerListNew = new ArrayList<Player>();
            for (Player playerListNewPlayerToAttach : playerListNew) {
                playerListNewPlayerToAttach = em.getReference(playerListNewPlayerToAttach.getClass(), playerListNewPlayerToAttach.getPlayerId());
                attachedPlayerListNew.add(playerListNewPlayerToAttach);
            }
            playerListNew = attachedPlayerListNew;
            team.setPlayerList(playerListNew);
            team = em.merge(team);
            if (tidOld != null && !tidOld.equals(tidNew)) {
                tidOld.getTeamList().remove(team);
                tidOld = em.merge(tidOld);
            }
            if (tidNew != null && !tidNew.equals(tidOld)) {
                tidNew.getTeamList().add(team);
                tidNew = em.merge(tidNew);
            }
            for (Staff staffListOldStaff : staffListOld) {
                if (!staffListNew.contains(staffListOldStaff)) {
                    staffListOldStaff.setTeamId(null);
                    staffListOldStaff = em.merge(staffListOldStaff);
                }
            }
            for (Staff staffListNewStaff : staffListNew) {
                if (!staffListOld.contains(staffListNewStaff)) {
                    Team oldTeamIdOfStaffListNewStaff = staffListNewStaff.getTeamId();
                    staffListNewStaff.setTeamId(team);
                    staffListNewStaff = em.merge(staffListNewStaff);
                    if (oldTeamIdOfStaffListNewStaff != null && !oldTeamIdOfStaffListNewStaff.equals(team)) {
                        oldTeamIdOfStaffListNewStaff.getStaffList().remove(staffListNewStaff);
                        oldTeamIdOfStaffListNewStaff = em.merge(oldTeamIdOfStaffListNewStaff);
                    }
                }
            }
            for (Player playerListNewPlayer : playerListNew) {
                if (!playerListOld.contains(playerListNewPlayer)) {
                    Team oldTeamIdOfPlayerListNewPlayer = playerListNewPlayer.getTeamId();
                    playerListNewPlayer.setTeamId(team);
                    playerListNewPlayer = em.merge(playerListNewPlayer);
                    if (oldTeamIdOfPlayerListNewPlayer != null && !oldTeamIdOfPlayerListNewPlayer.equals(team)) {
                        oldTeamIdOfPlayerListNewPlayer.getPlayerList().remove(playerListNewPlayer);
                        oldTeamIdOfPlayerListNewPlayer = em.merge(oldTeamIdOfPlayerListNewPlayer);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = team.getTeamid();
                if (findTeam(id) == null) {
                    throw new NonexistentEntityException("The team with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Team team;
            try {
                team = em.getReference(Team.class, id);
                team.getTeamid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The team with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Player> playerListOrphanCheck = team.getPlayerList();
            for (Player playerListOrphanCheckPlayer : playerListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Team (" + team + ") cannot be destroyed since the Player " + playerListOrphanCheckPlayer + " in its playerList field has a non-nullable teamId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tournament tid = team.getTid();
            if (tid != null) {
                tid.getTeamList().remove(team);
                tid = em.merge(tid);
            }
            List<Staff> staffList = team.getStaffList();
            for (Staff staffListStaff : staffList) {
                staffListStaff.setTeamId(null);
                staffListStaff = em.merge(staffListStaff);
            }
            em.remove(team);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Team> findTeamEntities() {
        return findTeamEntities(true, -1, -1);
    }

    public List<Team> findTeamEntities(int maxResults, int firstResult) {
        return findTeamEntities(false, maxResults, firstResult);
    }

    private List<Team> findTeamEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Team.class));
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

    public Team findTeam(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Team.class, id);
        } finally {
            em.close();
        }
    }

    public int getTeamCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Team> rt = cq.from(Team.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
