package com.webblog.services;

import java.util.List;
import com.webblog.models.Auteur;
import com.webblog.repositories.impl.AuteurImpl;

public class AuteurService {

    private AuteurImpl auteurImpl = new AuteurImpl();

    public AuteurService() {
    }

    public Boolean save(Auteur entity) {
        return auteurImpl.save(entity);
    }

    public Boolean update(Auteur entity) {
        return auteurImpl.update(entity);
    }

    public Boolean delete(Integer id) {
        return auteurImpl.delete(id);
    }

    public Auteur findById(Integer id) {
        return auteurImpl.findById(id);
    }

    public List<Auteur> getAllAuteurs(int page, int pageSize) {
        return auteurImpl.getPage(page, pageSize);
    }

    public Integer countAuteurs() {
        return auteurImpl.count();
    }
}
