package com.cnpm.paging;

import com.cnpm.sort.Sorter;

public interface Pageble {
	Integer getPage();
	Integer getOffset();
	Integer getLimit();
	Sorter getSorter();
	void setLimit(Integer limit);
}
