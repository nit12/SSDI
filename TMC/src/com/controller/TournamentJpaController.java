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
import com.entities.Staff;
import java.util.ArrayList;
import java.util.List;
import com.entities.Draw;
import com.entities.Team;
import com.entities.Salary;
import com.entities.User;
import com.entities.Transaction;
import com.entities.Player;
import com.entities.Tournament;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author vinodkumar
 */
public class TournamentJpaController implements Serializable {

    public TournamentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tournament tournament) {
        if (tournament.getStaffList() == null) {
            tournament.setStaffList(new ArrayList<Staff>());
        }
        if (tournament.getDrawList() == null) {
            tournament.setDrawList(new ArrayList<Draw>());
        }
        if (tournament.getTeamList() == null) {
            tournament.setTeamList(new ArrayList<Team>());
        }
        if (tournament.getSalaryList() == null) {
            tournament.setSalaryList(new ArrayList<Salary>());
        }
        if (tournament.getUserList() == null) {
            tournament.setUserList(new ArrayList<User>());
        }
        if (tournament.getTransactionList() == null) {
            tournament.setTransactionList(new ArrayList<Transaction>());
        }
        if (tournament.getPlayerList() == null) {
            tournament.setPlayerList(new ArrayList<Player>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Staff> attachedStaffList = new ArrayList<Staff>();
            for (Staff staffListStaffToAttach : tournament.getStaffList()) {
                staffListStaffToAttach = em.getReference(staffListStaffToAttach.getClass(), staffListStaffToAttach.getStaffId());
                attachedStaffList.add(staffListStaffToAttach);
            }
            tournament.setStaffList(attachedStaffList);
            List<Draw> attachedDrawList = new ArrayList<Draw>();
            for (Draw drawListDrawToAttach : tournament.getDrawList()) {
                drawListDrawToAttach = em.getReference(drawListDrawToAttach.getClass(), drawListDrawToAttach.getMatchno());
                attachedDrawList.add(drawListDrawToAttach);
            }
            tournament.setDrawList(attachedDrawList);
            List<Team> attachedTeamList = new ArrayList<Team>();
            for (Team teamListTeamToAttach : tournament.getTeamList()) {
                teamListTeamToAttach = em.getReference(teamListTeamToAttach.getClass(), teamListTeamToAttach.getTeamid());
                attachedTeamList.add(teamListTeamToAttach);
            }
            tournament.setTeamList(attachedTeamList);
            List<Salary> attachedSalaryList = new ArrayList<Salary>();
            for (Salary salaryListSalaryToAttach : tournament.getSalaryList()) {
                salaryListSalaryToAttach = em.getReference(salaryListSalaryToAttach.getClass(), salaryListSalaryToAttach.getRoleId());
                attachedSalaryList.add(salaryListSalaryToAttach);
            }
            tournament.setSalaryList(attachedSalaryList);
            List<User> attachedUserList = new ArrayList<User>();
            for (User userListUserToAttach : tournament.getUserList()) {
                userListUserToAttach = em.getReference(userListUserToAttach.getClass(), userListUserToAttach.getUserid());
                attachedUserList.add(userListUserToAttach);
            }
            tournament.setUserList(attachedUserList);
            List<Transaction> attachedTransactionList = new ArrayList<Transaction>();
            for (Transaction transactionListTransactionToAttach : tournament.getTransactionList()) {
                transactionListTransactionToAttach = em.getReference(transactionListTransactionToAttach.getClass(), transactionListTransactionToAttach.getTransId());
                attachedTransactionList.add(transactionListTransactionToAttach);
            }
            tournament.setTransactionList(attachedTransactionList);
            List<Player> attachedPlayerList = new ArrayList<Player>();
            for (Player playerListPlayerToAttach : tournament.getPlayerList()) {
                playerListPlayerToAttach = em.getReference(playerListPlayerToAttach.getClass(), playerListPlayerToAttach.getPlayerId());
                attachedPlayerList.add(playerListPlayerToAttach);
            }
            tournament.setPlayerList(attachedPlayerList);
            em.persist(tournament);
            for (Staff staffListStaff : tournament.getStaffList()) {
                Tournament oldTidOfStaffListStaff = staffListStaff.getTid();
                staffListStaff.setTid(tournament);
                staffListStaff = em.merge(staffListStaff);
                if (oldTidOfStaffListStaff != null) {
                    oldTidOfStaffListStaff.getStaffList().remove(staffListStaff);
                    oldTidOfStaffListStaff = em.merge(oldTidOfStaffListStaff);
                }
            }
            for (Draw drawListDraw : tournament.getDrawList()) {
                Tournament oldTidOfDrawListDraw = drawListDraw.getTid();
                drawListDraw.setTid(tournament);
                drawListDraw = em.merge(drawListDraw);
                if (oldTidOfDrawListDraw != null) {
                    oldTidOfDrawListDraw.getDrawList().remove(drawListDraw);
                    oldTidOfDrawListDraw = em.merge(oldTidOfDrawListDraw);
                }
            }
            for (Team teamListTeam : tournament.getTeamList()) {
                Tournament oldTidOfTeamListTeam = teamListTeam.getTid();
                teamListTeam.setTid(tournament);
                teamListTeam = em.merge(teamListTeam);
                if (oldTidOfTeamListTeam != null) {
                    oldTidOfTeamListTeam.getTeamList().remove(teamListTeam);
                    oldTidOfTeamListTeam = em.merge(oldTidOfTeamListTeam);
                }
            }
            for (Salary salaryListSalary : tournament.getSalaryList()) {
                Tournament oldTidOfSalaryListSalary = salaryListSalary.getTid();
                salaryListSalary.setTid(tournament);
                salaryListSalary = em.merge(salaryListSalary);
                if (oldTidOfSalaryListSalary != null) {
                    oldTidOfSalaryListSalary.getSalaryList().remove(salaryListSalary);
                    oldTidOfSalaryListSalary = em.merge(oldTidOfSalaryListSalary);
                }
            }
            for (User userListUser : tournament.getUserList()) {
                Tournament oldTidOfUserListUser = userListUser.getTid();
                userListUser.setTid(tournament);
                userListUser = em.merge(userListUser);
                if (oldTidOfUserListUser != null) {
                    oldTidOfUserListUser.getUserList().remove(userListUser);
                    oldTidOfUserListUser = em.merge(oldTidOfUserListUser);
                }
            }
            for (Transaction transactionListTransaction : tournament.getTransactionList()) {
                Tournament oldTidOfTransactionListTransaction = transactionListTransaction.getTid();
                transactionListTransaction.setTid(tournament);
                transactionListTransaction = em.merge(transactionListTransaction);
                if (oldTidOfTransactionListTransaction != null) {
                    oldTidOfTransactionListTransaction.getTransactionList().remove(transactionListTransaction);
                    oldTidOfTransactionListTransaction = em.merge(oldTidOfTransactionListTransaction);
                }
            }
            for (Player playerListPlayer : tournament.getPlayerList()) {
                Tournament oldTidOfPlayerListPlayer = playerListPlayer.getTid();
                playerListPlayer.setTid(tournament);
                playerListPlayer = em.merge(playerListPlayer);
                if (oldTidOfPlayerListPlayer != null) {
                    oldTidOfPlayerListPlayer.getPlayerList().remove(playerListPlayer);
                    oldTidOfPlayerListPlayer = em.merge(oldTidOfPlayerListPlayer);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tournament tournament) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tournament persistentTournament = em.find(Tournament.class, tournament.getTid());
            List<Staff> staffListOld = persistentTournament.getStaffList();
            List<Staff> staffListNew = tournament.getStaffList();
            List<Draw> drawListOld = persistentTournament.getDrawList();
            List<Draw> drawListNew = tournament.getDrawList();
            List<Team> teamListOld = persistentTournament.getTeamList();
            List<Team> teamListNew = tournament.getTeamList();
            List<Salary> salaryListOld = persistentTournament.getSalaryList();
            List<Salary> salaryListNew = tournament.getSalaryList();
            List<User> userListOld = persistentTournament.getUserList();
            List<User> userListNew = tournament.getUserList();
            List<Transaction> transactionListOld = persistentTournament.getTransactionList();
            List<Transaction> transactionListNew = tournament.getTransactionList();
            List<Player> playerListOld = persistentTournament.getPlayerList();
            List<Player> playerListNew = tournament.getPlayerList();
            List<String> illegalOrphanMessages = null;
            for (Draw drawListOldDraw : drawListOld) {
                if (!drawListNew.contains(drawListOldDraw)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Draw " + drawListOldDraw + " since its tid field is not nullable.");
                }
            }
            for (Team teamListOldTeam : teamListOld) {
                if (!teamListNew.contains(teamListOldTeam)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Team " + teamListOldTeam + " since its tid field is not nullable.");
                }
            }
            for (Salary salaryListOldSalary : salaryListOld) {
                if (!salaryListNew.contains(salaryListOldSalary)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Salary " + salaryListOldSalary + " since its tid field is not nullable.");
                }
            }
            for (User userListOldUser : userListOld) {
                if (!userListNew.contains(userListOldUser)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain User " + userListOldUser + " since its tid field is not nullable.");
                }
            }
            for (Transaction transactionListOldTransaction : transactionListOld) {
                if (!transactionListNew.contains(transactionListOldTransaction)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Transaction " + transactionListOldTransaction + " since its tid field is not nullable.");
                }
            }
            for (Player playerListOldPlayer : playerListOld) {
                if (!playerListNew.contains(playerListOldPlayer)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Player " + playerListOldPlayer + " since its tid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Staff> attachedStaffListNew = new ArrayList<Staff>();
            for (Staff staffListNewStaffToAttach : staffListNew) {
                staffListNewStaffToAttach = em.getReference(staffListNewStaffToAttach.getClass(), staffListNewStaffToAttach.getStaffId());
                attachedStaffListNew.add(staffListNewStaffToAttach);
            }
            staffListNew = attachedStaffListNew;
            tournament.setStaffList(staffListNew);
            List<Draw> attachedDrawListNew = new ArrayList<Draw>();
            for (Draw drawListNewDrawToAttach : drawListNew) {
                drawListNewDrawToAttach = em.getReference(drawListNewDrawToAttach.getClass(), drawListNewDrawToAttach.getMatchno());
                attachedDrawListNew.add(drawListNewDrawToAttach);
            }
            drawListNew = attachedDrawListNew;
            tournament.setDrawList(drawListNew);
            List<Team> attachedTeamListNew = new ArrayList<Team>();
            for (Team teamListNewTeamToAttach : teamListNew) {
                teamListNewTeamToAttach = em.getReference(teamListNewTeamToAttach.getClass(), teamListNewTeamToAttach.getTeamid());
                attachedTeamListNew.add(teamListNewTeamToAttach);
            }
            teamListNew = attachedTeamListNew;
            tournament.setTeamList(teamListNew);
            List<Salary> attachedSalaryListNew = new ArrayList<Salary>();
            for (Salary salaryListNewSalaryToAttach : salaryListNew) {
                salaryListNewSalaryToAttach = em.getReference(salaryListNewSalaryToAttach.getClass(), salaryListNewSalaryToAttach.getRoleId());
                attachedSalaryListNew.add(salaryListNewSalaryToAttach);
            }
            salaryListNew = attachedSalaryListNew;
            tournament.setSalaryList(salaryListNew);
            List<User> attachedUserListNew = new ArrayList<User>();
            for (User userListNewUserToAttach : userListNew) {
                userListNewUserToAttach = em.getReference(userListNewUserToAttach.getClass(), userListNewUserToAttach.getUserid());
                attachedUserListNew.add(userListNewUserToAttach);
            }
            userListNew = attachedUserListNew;
            tournament.setUserList(userListNew);
            List<Transaction> attachedTransactionListNew = new ArrayList<Transaction>();
            for (Transaction transactionListNewTransactionToAttach : transactionListNew) {
                transactionListNewTransactionToAttach = em.getReference(transactionListNewTransactionToAttach.getClass(), transactionListNewTransactionToAttach.getTransId());
                attachedTransactionListNew.add(transactionListNewTransactionToAttach);
            }
            transactionListNew = attachedTransactionListNew;
            tournament.setTransactionList(transactionListNew);
            List<Player> attachedPlayerListNew = new ArrayList<Player>();
            for (Player playerListNewPlayerToAttach : playerListNew) {
                playerListNewPlayerToAttach = em.getReference(playerListNewPlayerToAttach.getClass(), playerListNewPlayerToAttach.getPlayerId());
                attachedPlayerListNew.add(playerListNewPlayerToAttach);
            }
            playerListNew = attachedPlayerListNew;
            tournament.setPlayerList(playerListNew);
            tournament = em.merge(tournament);
            for (Staff staffListOldStaff : staffListOld) {
                if (!staffListNew.contains(staffListOldStaff)) {
                    staffListOldStaff.setTid(null);
                    staffListOldStaff = em.merge(staffListOldStaff);
                }
            }
            for (Staff staffListNewStaff : staffListNew) {
                if (!staffListOld.contains(staffListNewStaff)) {
                    Tournament oldTidOfStaffListNewStaff = staffListNewStaff.getTid();
                    staffListNewStaff.setTid(tournament);
                    staffListNewStaff = em.merge(staffListNewStaff);
                    if (oldTidOfStaffListNewStaff != null && !oldTidOfStaffListNewStaff.equals(tournament)) {
                        oldTidOfStaffListNewStaff.getStaffList().remove(staffListNewStaff);
                        oldTidOfStaffListNewStaff = em.merge(oldTidOfStaffListNewStaff);
                    }
                }
            }
            for (Draw drawListNewDraw : drawListNew) {
                if (!drawListOld.contains(drawListNewDraw)) {
                    Tournament oldTidOfDrawListNewDraw = drawListNewDraw.getTid();
                    drawListNewDraw.setTid(tournament);
                    drawListNewDraw = em.merge(drawListNewDraw);
                    if (oldTidOfDrawListNewDraw != null && !oldTidOfDrawListNewDraw.equals(tournament)) {
                        oldTidOfDrawListNewDraw.getDrawList().remove(drawListNewDraw);
                        oldTidOfDrawListNewDraw = em.merge(oldTidOfDrawListNewDraw);
                    }
                }
            }
            for (Team teamListNewTeam : teamListNew) {
                if (!teamListOld.contains(teamListNewTeam)) {
                    Tournament oldTidOfTeamListNewTeam = teamListNewTeam.getTid();
                    teamListNewTeam.setTid(tournament);
                    teamListNewTeam = em.merge(teamListNewTeam);
                    if (oldTidOfTeamListNewTeam != null && !oldTidOfTeamListNewTeam.equals(tournament)) {
                        oldTidOfTeamListNewTeam.getTeamList().remove(teamListNewTeam);
                        oldTidOfTeamListNewTeam = em.merge(oldTidOfTeamListNewTeam);
                    }
                }
            }
            for (Salary salaryListNewSalary : salaryListNew) {
                if (!salaryListOld.contains(salaryListNewSalary)) {
                    Tournament oldTidOfSalaryListNewSalary = salaryListNewSalary.getTid();
                    salaryListNewSalary.setTid(tournament);
                    salaryListNewSalary = em.merge(salaryListNewSalary);
                    if (oldTidOfSalaryListNewSalary != null && !oldTidOfSalaryListNewSalary.equals(tournament)) {
                        oldTidOfSalaryListNewSalary.getSalaryList().remove(salaryListNewSalary);
                        oldTidOfSalaryListNewSalary = em.merge(oldTidOfSalaryListNewSalary);
                    }
                }
            }
            for (User userListNewUser : userListNew) {
                if (!userListOld.contains(userListNewUser)) {
                    Tournament oldTidOfUserListNewUser = userListNewUser.getTid();
                    userListNewUser.setTid(tournament);
                    userListNewUser = em.merge(userListNewUser);
                    if (oldTidOfUserListNewUser != null && !oldTidOfUserListNewUser.equals(tournament)) {
                        oldTidOfUserListNewUser.getUserList().remove(userListNewUser);
                        oldTidOfUserListNewUser = em.merge(oldTidOfUserListNewUser);
                    }
                }
            }
            for (Transaction transactionListNewTransaction : transactionListNew) {
                if (!transactionListOld.contains(transactionListNewTransaction)) {
                    Tournament oldTidOfTransactionListNewTransaction = transactionListNewTransaction.getTid();
                    transactionListNewTransaction.setTid(tournament);
                    transactionListNewTransaction = em.merge(transactionListNewTransaction);
                    if (oldTidOfTransactionListNewTransaction != null && !oldTidOfTransactionListNewTransaction.equals(tournament)) {
                        oldTidOfTransactionListNewTransaction.getTransactionList().remove(transactionListNewTransaction);
                        oldTidOfTransactionListNewTransaction = em.merge(oldTidOfTransactionListNewTransaction);
                    }
                }
            }
            for (Player playerListNewPlayer : playerListNew) {
                if (!playerListOld.contains(playerListNewPlayer)) {
                    Tournament oldTidOfPlayerListNewPlayer = playerListNewPlayer.getTid();
                    playerListNewPlayer.setTid(tournament);
                    playerListNewPlayer = em.merge(playerListNewPlayer);
                    if (oldTidOfPlayerListNewPlayer != null && !oldTidOfPlayerListNewPlayer.equals(tournament)) {
                        oldTidOfPlayerListNewPlayer.getPlayerList().remove(playerListNewPlayer);
                        oldTidOfPlayerListNewPlayer = em.merge(oldTidOfPlayerListNewPlayer);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tournament.getTid();
                if (findTournament(id) == null) {
                    throw new NonexistentEntityException("The tournament with id " + id + " no longer exists.");
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
            Tournament tournament;
            try {
                tournament = em.getReference(Tournament.class, id);
                tournament.getTid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tournament with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Draw> drawListOrphanCheck = tournament.getDrawList();
            for (Draw drawListOrphanCheckDraw : drawListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tournament (" + tournament + ") cannot be destroyed since the Draw " + drawListOrphanCheckDraw + " in its drawList field has a non-nullable tid field.");
            }
            List<Team> teamListOrphanCheck = tournament.getTeamList();
            for (Team teamListOrphanCheckTeam : teamListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tournament (" + tournament + ") cannot be destroyed since the Team " + teamListOrphanCheckTeam + " in its teamList field has a non-nullable tid field.");
            }
            List<Salary> salaryListOrphanCheck = tournament.getSalaryList();
            for (Salary salaryListOrphanCheckSalary : salaryListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tournament (" + tournament + ") cannot be destroyed since the Salary " + salaryListOrphanCheckSalary + " in its salaryList field has a non-nullable tid field.");
            }
            List<User> userListOrphanCheck = tournament.getUserList();
            for (User userListOrphanCheckUser : userListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tournament (" + tournament + ") cannot be destroyed since the User " + userListOrphanCheckUser + " in its userList field has a non-nullable tid field.");
            }
            List<Transaction> transactionListOrphanCheck = tournament.getTransactionList();
            for (Transaction transactionListOrphanCheckTransaction : transactionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tournament (" + tournament + ") cannot be destroyed since the Transaction " + transactionListOrphanCheckTransaction + " in its transactionList field has a non-nullable tid field.");
            }
            List<Player> playerListOrphanCheck = tournament.getPlayerList();
            for (Player playerListOrphanCheckPlayer : playerListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tournament (" + tournament + ") cannot be destroyed since the Player " + playerListOrphanCheckPlayer + " in its playerList field has a non-nullable tid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Staff> staffList = tournament.getStaffList();
            for (Staff staffListStaff : staffList) {
                staffListStaff.setTid(null);
                staffListStaff = em.merge(staffListStaff);
            }
            em.remove(tournament);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tournament> findTournamentEntities() {
        return findTournamentEntities(true, -1, -1);
    }

    public List<Tournament> findTournamentEntities(int maxResults, int firstResult) {
        return findTournamentEntities(false, maxResults, firstResult);
    }

    private List<Tournament> findTournamentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tournament.class));
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

    public Tournament findTournament(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tournament.class, id);
        } finally {
            em.close();
        }
    }

    public int getTournamentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tournament> rt = cq.from(Tournament.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
