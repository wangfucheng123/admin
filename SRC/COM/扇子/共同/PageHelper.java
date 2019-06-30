package com.dfans.common;
        
import java.util.ArrayList;
import java.util.List;
         
/*
 * 集合内存分页
 */
public class PageHelper {
	public List<Object> fenye(List targetList,int pageSize) {
		List<Object> newlist = new ArrayList<>();
		int totalCount = targetList.size();
		int pageCount = 0;
		
		//总页码计算
		int pageNum = totalCount % pageSize;
		if(pageNum > 0) {
			pageCount = totalCount / pageSize + 1;
		}else {
			pageCount = totalCount / pageSize;
		}          
		
		for (int i = 1; i <= pageCount; i++) {
			//如果总记录数刚好能整除总页码数，就截取(i-1)*页码数，到设置页码数
			if(pageNum == 0) {
				List subList = targetList.subList((i-1)*pageSize, pageSize);
				newlist.add(subList);
			}else {
				if(i == pageCount) {
					List subList = targetList.subList((i-1)*pageSize, pageSize*(i));
					newlist.add(subList);
				}  
			}           
		}   
		return newlist;
	} 
	//method fenye end
	
	      
	/*
	 * 对目标list分页
	 */
	public List listPaging(List targetList,int currPage,int pageSize) {
		if(currPage <= 0 || "".equals(currPage+"") ) {
			currPage = 1;
		}
		if(pageSize <= 0 || "".equals(pageSize+"") ) {
			pageSize = 10;
		}
		List<Object> rlstList = new ArrayList<>();
		if(null != targetList && targetList.size() > 0 ) {
			
			int totalCount = targetList.size();
			int pageCount = 0;
			      
			//总页码计算
			int pageNum = totalCount % pageSize;
			if(pageNum > 0) {
				pageCount = totalCount / pageSize + 1;
			}else {
				pageCount = totalCount / pageSize;
			}
			   
			rlstList = targetList.subList((currPage-1)*pageSize, (currPage * pageSize));
			        
			/*
			 * Collections.sort(rlstList, new Comparator<T>() {
			 * 
			 * @Override public int compare(T o1, T o2) { return 0; }
			 * 
			 * });
			 */
			 
		} 
		return rlstList;
		
		 
		
	}
	
}
