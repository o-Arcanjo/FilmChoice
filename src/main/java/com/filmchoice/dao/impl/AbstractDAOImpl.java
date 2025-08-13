package com.filmchoice.dao.impl;
import java.util.List;

import com.filmchoice.dao.DAO;
import com.filmchoice.dao.PersistenciaDawException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

public abstract class AbstractDAOImpl<E, T> implements DAO<E, T> {

	private EntityManagerFactory emf;
	private Class<E> entityClass;

	public AbstractDAOImpl(Class<E> entityClass, EntityManagerFactory emf) {
		this.entityClass = entityClass;
		this.emf = emf;
	}

	protected EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	@Override
	public void save(E obj) throws PersistenciaDawException {
		try(EntityManager em = getEntityManager()) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			try {
				em.persist(obj);
				transaction.commit();
			} catch (PersistenceException e) {
				e.printStackTrace();
				if (transaction.isActive()) {
					transaction.rollback();
				}
				throw new PersistenciaDawException("Ocorreu algum erro ao tentar salvar a entidade.", e);
			}
		}
	}

	@Override
	public E update(E obj) throws PersistenciaDawException {
		try(EntityManager em = getEntityManager()) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			try {
				E resultado = em.merge(obj);
				transaction.commit();
				return resultado;
			} catch (PersistenceException e) {
				e.printStackTrace();
				if (transaction.isActive()) {
					transaction.rollback();
				}
				throw new PersistenciaDawException("Ocorreu algum erro ao tentar atualizar a entidade.", e);
			}
		}
	}

	@Override
	public void delete(T primaryKey) throws PersistenciaDawException {
		try(EntityManager em = getEntityManager()) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			try {
				E obj = em.getReference(this.entityClass, primaryKey);
				em.remove(obj);
				transaction.commit();
			} catch (PersistenceException e) {
				e.printStackTrace();
				if (transaction.isActive()) {
					transaction.rollback();
				}
				throw new PersistenciaDawException("Ocorreu algum erro ao tentar remover a entidade.", e);
			}
		}
	}

	@Override
	public E getByID(T primaryKey) throws PersistenciaDawException {
		try(EntityManager em = getEntityManager()) {
			try {
				return em.find(this.entityClass, primaryKey);
			} catch (PersistenceException e) {
				e.printStackTrace();
				throw new PersistenciaDawException("Ocorreu algum erro ao tentar recuperar a entidade com base no ID.", e);
			}
		}
	}

	@Override
	public List<E> getAll() throws PersistenciaDawException {
		try(EntityManager em = getEntityManager()) {
			try {
				TypedQuery<E> query = em.createQuery(String.format("SELECT obj FROM %s obj", this.entityClass.getSimpleName()), this.entityClass);
				return query.getResultList();
			} catch (PersistenceException e) {
				e.printStackTrace();
				throw new PersistenciaDawException("Ocorreu algum erro ao tentar recuperar todas as instâncias da entidade.", e);
			}
		}
	}

}