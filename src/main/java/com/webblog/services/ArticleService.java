package com.webblog.services;

import com.webblog.models.Article;
import com.webblog.repositories.impl.ArticleImpl;

public class ArticleService {
    
    private ArticleImpl articleImpl = new ArticleImpl();
    
    public ArticleService() {
        // Constructor
    }
    

    public Boolean save(Article entity) {
        return articleImpl.save(entity);
    }
    
    public Boolean update(Article entity) {

        return articleImpl.update(entity); 
    }
    
 
    public Boolean delete(Integer id) {
        return articleImpl.delete(id); 
    }
}
