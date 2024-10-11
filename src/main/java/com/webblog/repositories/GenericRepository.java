package com.webblog.repositories;


public interface GenericRepository<T,ID> {
	   Boolean save(T entity);
	    Boolean update(T entity);
	    Boolean delete(ID id); 
	    
}
