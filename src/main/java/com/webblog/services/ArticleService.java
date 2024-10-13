package com.webblog.services;

import java.util.List;

import com.webblog.models.Article;
import com.webblog.repositories.impl.ArticleImpl;

public class ArticleService {

    private ArticleImpl articleImpl = new ArticleImpl();

    public ArticleService() {
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

    public Article findById(Integer id) {
        return articleImpl.findById(id);
    }
    
    public List<Article> getAllArticle(int page, int pageSize) {
    	return articleImpl.getPage(page, pageSize);
    	
    }
    public Integer countArteicle() {
    	return articleImpl.count();
    }
    
    public List<Article> searchArticlesByTitle(String title, int page, int pageSize) {
        return articleImpl.searchByTitle(title, page, pageSize);
    }
}
