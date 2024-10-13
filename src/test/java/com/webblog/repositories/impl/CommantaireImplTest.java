package com.webblog.repositories.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.mockito.Mockito;

import com.webblog.models.Commontaire;
import com.webblog.enums.StatusC;

public class CommantaireImplTest {

	private CommantaireImpl commantaireImpl;
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;

	@Before
	public void setUp() {

		entityManagerFactory = Mockito.mock(EntityManagerFactory.class);
		entityManager = Mockito.mock(EntityManager.class);
		transaction = Mockito.mock(EntityTransaction.class);

		when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
		when(entityManager.getTransaction()).thenReturn(transaction);

		commantaireImpl = new CommantaireImpl();

		commantaireImpl.setEntityManagerFactory(entityManagerFactory);
	}

	@Test
	public void testSaveCommentaire() {
		Commontaire commentaire = new Commontaire();
		commentaire.setId(1);
		commentaire.setContenu("This is a comment");
		commentaire.setDateCreation(LocalDate.now());
		commentaire.setStatus(StatusC.Approuvé);

		boolean result = commantaireImpl.save(commentaire);

		verify(entityManager).persist(commentaire);
		verify(transaction).commit();
		assertTrue(result);
	}

	@Test
	public void testSaveCommentaire_Exception() {
		Commontaire commentaire = new Commontaire();

		doThrow(new RuntimeException("Database error")).when(entityManager).persist(any());

		boolean result = commantaireImpl.save(commentaire);

		assertFalse(result);
	}

	@Test
	public void testUpdateCommentaire_Success() {
		Commontaire commentaire = new Commontaire();
		commentaire.setId(1);
		commentaire.setContenu("Updated comment");
		commentaire.setDateCreation(LocalDate.now());
		commentaire.setStatus(StatusC.Approuvé);

		when(entityManager.find(Commontaire.class, commentaire.getId())).thenReturn(commentaire);

		boolean result = commantaireImpl.update(commentaire);

		verify(entityManager).merge(commentaire);
		verify(transaction).commit();
		assertTrue(result);
	}

	@Test
	public void testUpdateCommentaire_NotFound() {
		Commontaire commentaire = new Commontaire();
		commentaire.setId(1);

		
		when(entityManager.find(Commontaire.class, commentaire.getId())).thenReturn(null);

		boolean result = commantaireImpl.update(commentaire);

		
		verify(entityManager, never()).merge(any());
		verify(transaction, never()).commit();
		assertFalse(result);
	}

	@Test
	public void testDeleteCommentaire_Success() {
		Integer id = 1;
		Commontaire commentaire = new Commontaire();
		commentaire.setId(id);

		when(entityManager.find(Commontaire.class, id)).thenReturn(commentaire);

		boolean result = commantaireImpl.delete(id);

		verify(entityManager).remove(commentaire);
		verify(transaction).commit();
		assertTrue(result);
	}

	@Test
	public void testDeleteCommentaire_NotFound() {
		Integer id = 1;

		when(entityManager.find(Commontaire.class, id)).thenReturn(null);

		boolean result = commantaireImpl.delete(id);

		verify(entityManager, never()).remove(any());
		verify(transaction, never()).commit();
		assertFalse(result);
	}
}
