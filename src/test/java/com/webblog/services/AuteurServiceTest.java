package com.webblog.services;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.webblog.enums.Role;
import com.webblog.models.Auteur;
import com.webblog.repositories.impl.AuteurImpl;

public class AuteurServiceTest {

    @InjectMocks
    private AuteurService auteurService;

    @Mock
    private AuteurImpl auteurImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        Auteur auteur = new Auteur();
        auteur.setNom("Dupont");
        auteur.setPrenom("Jean");
        auteur.setEmail("jean.dupont@example.com");
        auteur.setDateNaissance(LocalDate.of(1985, 5, 12));
        auteur.setRole(Role.Contributeur);

        when(auteurImpl.save(auteur)).thenReturn(true);

        Boolean result = auteurService.save(auteur);

        assertTrue(result);
        verify(auteurImpl, times(1)).save(auteur);
    }

    @Test
    public void testUpdate() {
        Auteur auteur = new Auteur();
        auteur.setId(1);
        auteur.setNom("Dupont");
        auteur.setPrenom("Jean Updated");
        auteur.setEmail("jean.updated@example.com");
        auteur.setDateNaissance(LocalDate.of(1985, 5, 12));
        auteur.setRole(Role.Editeur);

        when(auteurImpl.update(auteur)).thenReturn(true);

        Boolean result = auteurService.update(auteur);

        assertTrue(result);
        verify(auteurImpl, times(1)).update(auteur);
    }

    @Test
    public void testDelete() {
        int auteurId = 1;

        when(auteurImpl.delete(auteurId)).thenReturn(true);

        Boolean result = auteurService.delete(auteurId);

        assertTrue(result);
        verify(auteurImpl, times(1)).delete(auteurId);
    }

    @Test
    public void testFindById() {
        int auteurId = 1;
        Auteur auteur = new Auteur();
        auteur.setId(auteurId);
        auteur.setNom("Dupont");
        auteur.setPrenom("Jean");

        when(auteurImpl.findById(auteurId)).thenReturn(auteur);

        Auteur result = auteurService.findById(auteurId);

        assertNotNull(result);
        assertEquals(auteurId, result.getId().intValue()); 
        verify(auteurImpl, times(1)).findById(auteurId);
    }

    @Test
    public void testGetAllAuteurs() {
        Auteur auteur1 = new Auteur();
        auteur1.setId(1);
        auteur1.setNom("Dupont");
        auteur1.setPrenom("Jean");

        Auteur auteur2 = new Auteur();
        auteur2.setId(2);
        auteur2.setNom("Martin");
        auteur2.setPrenom("Claire");

        List<Auteur> auteurs = Arrays.asList(auteur1, auteur2);

        when(auteurImpl.getPage(1, 10)).thenReturn(auteurs);

        List<Auteur> result = auteurService.getAllAuteurs(1, 10);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(auteur1.getNom(), result.get(0).getNom());
        assertEquals(auteur2.getNom(), result.get(1).getNom());

        verify(auteurImpl, times(1)).getPage(1, 10);
    }
}
