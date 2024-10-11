package com.webblog.repositories;

import java.util.List;

public interface MultiInterface <T>{
	  List<T> getPage(int page, int pageSize);
		Integer count();
	    
}
