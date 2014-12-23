/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.controller.exceptions.NonexistentEntityException;
import com.entities.Player;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.entities.Team;
import com.entities.Tournament;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author vinodkumar
 */
public class PlayerJpaController implements Serializable {

	public PlayerJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}

	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Player player) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Team teamId = player.getTeamId();
			if (teamId != null) {
				teamId = em.getReference(teamId.getClass(), teamId.getTeamid());
				player.setTeamId(teamId);
			}
			Tournament tid = player.getTid();
			if (tid != null) {
				tid = em.getReference(tid.getClass(), tid.getTid());
				player.setTid(tid);
			}
			em.persist(player);
			if (teamId != null) {
				teamId.getPlayerList().add(player);
				teamId = em.merge(teamId);
			}
			if (tid != null) {
				tid.getPlayerList().add(player);
				tid = em.merge(tid);
			}
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Player player) throws NonexistentEntityException,
			Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Player persistentPlayer = em.find(Player.class,
					player.getPlayerId());
			Team teamIdOld = persistentPlayer.getTeamId();
			Team teamIdNew = player.getTeamId();
			Tournament tidOld = persistentPlayer.getTid();
			Tournament tidNew = player.getTid();
			if (teamIdNew != null) {
				teamIdNew = em.getReference(teamIdNew.getClass(),
						teamIdNew.getTeamid());
				player.setTeamId(teamIdNew);
			}
			if (tidNew != null) {
				tidNew = em.getReference(tidNew.getClass(), tidNew.getTid());
				player.setTid(tidNew);
			}
			player = em.merge(player);
			if (teamIdOld != null && !teamIdOld.equals(teamIdNew)) {
				teamIdOld.getPlayerList().remove(player);
				teamIdOld = em.merge(teamIdOld);
			}
			if (teamIdNew != null && !teamIdNew.equals(teamIdOld)) {
				teamIdNew.getPlayerList().add(player);
				teamIdNew = em.merge(teamIdNew);
			}
			if (tidOld != null && !tidOld.equals(tidNew)) {
				tidOld.getPlayerList().remove(player);
				tidOld = em.merge(tidOld);
			}
			if (tidNew != null && !tidNew.equals(tidOld)) {
				tidNew.getPlayerList().add(player);
				tidNew = em.merge(tidNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = player.getPlayerId();
				if (findPlayer(id) == null) {
					throw new NonexistentEntityException("The player with id "
							+ id + " no longer exists.");
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
			Player player;
			try {
				player = em.getReference(Player.class, id);
				player.getPlayerId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The player with id " + id
						+ " no longer exists.", enfe);
			}
			Team teamId = player.getTeamId();
			if (teamId != null) {
				teamId.getPlayerList().remove(player);
				teamId = em.merge(teamId);
			}
			Tournament tid = player.getTid();
			if (tid != null) {
				tid.getPlayerList().remove(player);
				tid = em.merge(tid);
			}
			em.remove(player);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Player> findPlayerEntities() {
		return findPlayerEntities(true, -1, -1);
	}

	public List<Player> findPlayerEntities(int maxResults, int firstResult) {
		return findPlayerEntities(false, maxResults, firstResult);
	}

	private List<Player> findPlayerEntities(boolean all, int maxResults,
			int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Player.class));
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

	public Player findPlayer(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Player.class, id);
		} finally {
			em.close();
		}
	}

	public int getPlayerCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Player> rt = cq.from(Player.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}

}
