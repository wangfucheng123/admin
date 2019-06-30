package com.dfans.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dfans.model.MenuTree;

public class TreeUtils {

	
	 public static List<MenuTree> getChild(String id, List<MenuTree> rootMenu) {
		    // 子菜单
		    List<MenuTree> childList = new ArrayList();
		    for (MenuTree menu : rootMenu) {
		    	
		        // 遍历所有节点，将父菜单id与传过来的id比较
		        if (menu.getPid()!=0) {
		        	//System.out.println((""+menu.getPid()).equals(id));
		            if ((""+menu.getPid()).equals(id)) {
		            	System.out.println(menu.getPid()+"+++++++++++"+id);
		                childList.add(menu);
		            }
		        }
		    } 
		    //递归退出条件
		    if (childList.size() == 0) {
		        return null;
		    }
		    return childList;
		}
	 
	 
	 
	public static String getTree(List dataList)
	{
		
		  // 节点列表（散列表，用于临时存储节点对象）  
		  HashMap nodeList = new HashMap();  
		  // 根节点  
		  Node root = null;  
		  // 根据结果集构造节点列表（存入散列表）  
		  for (Iterator it = dataList.iterator(); it.hasNext();) {  
		   Map dataRecord = (Map) it.next();  
		   Node node = new Node();  
		   node.id = (String) dataRecord.get("id");  
		   node.name = (String) dataRecord.get("name");  
		   node.parentId = (String) dataRecord.get("parentId"); 
		   node.price= (float) 0; 
		   node.type=(String) dataRecord.get("type"); 
		   node.code=(String) dataRecord.get("code"); 
		   nodeList.put(node.id, node);  
		  }  
		  // 构造无序的多叉树  
		  Set entrySet = nodeList.entrySet();  
		  for (Iterator it = entrySet.iterator(); it.hasNext();) {  
		   Node node = (Node) ((Map.Entry) it.next()).getValue();  
		   if (node.parentId == null || node.parentId.equals("")) {  
		    root = node;  
		   } else {  
		    ((Node) nodeList.get(node.parentId)).addChild(node);  
		   }  
		  }  
		  // 输出无序的树形菜单的JSON字符串  
		  //System.out.println(root.toString());     
		  // 对多叉树进行横向排序  
		  root.sortChildren();  
		  // 输出有序的树形菜单的JSON字符串  
		  //System.out.println(root.toString());  
		  return root.toString();
	}
	
	
	public static String getCheckTree(List dataList)
	{
		
		  // 节点列表（散列表，用于临时存储节点对象）  
		  HashMap nodeList = new HashMap();  
		  // 根节点  
		  Node root = null;  
		  // 根据结果集构造节点列表（存入散列表）  
		  for (Iterator it = dataList.iterator(); it.hasNext();) {  
		   Map dataRecord = (Map) it.next();  
		   Node node = new Node();  
		   node.id = (String) dataRecord.get("id");  
		   node.name = (String) dataRecord.get("name");  
		   node.code=(String) dataRecord.get("code");
		   node.parentId = (String) dataRecord.get("parentId");  
		   nodeList.put(node.id, node);  
		  }  
		  // 构造无序的多叉树  
		  Set entrySet = nodeList.entrySet();  
		  for (Iterator it = entrySet.iterator(); it.hasNext();) {  
		   Node node = (Node) ((Map.Entry) it.next()).getValue();  
		   if (node.parentId == null || node.parentId.equals("")) {  
		    root = node;  
		   } else {  
		    ((Node) nodeList.get(node.parentId)).addChild(node);  
		   }  
		  }  
		  // 输出无序的树形菜单的JSON字符串     
		  // 对多叉树进行横向排序  
		  root.sortChildren();  
		  // 输出有序的树形菜单的JSON字符串  
		  //System.out.println(root.toString());  
		  return root.toCheckString();
	}
}

class Node {  
	 /** 
	  * 节点编号 
	  */  
	 public String id;  
	 /** 
	  * 节点内容 
	  */  
	 public String name;  
	 /** 
	  * 父节点编号 
	  */  
	 public String parentId;  
	 /** 
	  * 孩子节点列表 
	  */  
	 
	 public Float price;  
	 /** 
	  * 价格 
	  */ 
	 
	 public String type;  
	 /** 
	  * 价格 
	  */ 
	 public String code;
	 public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code == null ? null : code.trim();
	    }
	 private Children children = new Children();  
	   
	 // 先序遍历，拼接JSON字符串  
	 public String toString() {    
	  String result = "{"  
	   + "id : '" + id + "'"  
	   + ", name : '" + name  + "', code : '" + code  + "', price : '" + price + "'"+ ", type : '" + type + "'";  
	    
	  if (children != null && children.getSize() != 0) {  
	   result += ",open:true, children : " + children.toString();  
	  } else {  
	   result += ", isParent : false";  
	  }  
	      
	  return result + "}";  
	 }  
	 
	 
	 public String toCheckString() {    
		  String result = "{"  
		   + "id : '" + id + "'"  
		   + ", name : '" + name + "'"+ ", code : '" + code + "'";  
		    
		  if (children != null && children.getSize() != 0) {  
		   result += ",open:true,nocheck:true, children : " + children.toCheckString();  
		  } else {  
		   result += ", isParent : false";  
		  }  
		      
		  return result + "}";  
		 }  
	   
	 // 兄弟节点横向排序  
	 public void sortChildren() {  
	  if (children != null && children.getSize() != 0) {  
	   children.sortChildren();  
	  }  
	 }  
	   
	 // 添加孩子节点  
	 public void addChild(Node node) {  
	  this.children.addChild(node);  
	 }  
	}  
	  
	/** 
	* 孩子列表类 
	*/  
	class Children {  
	 private List list = new ArrayList();  
	   
	 public int getSize() {  
	  return list.size();  
	 }  
	   
	 public void addChild(Node node) {  
	  list.add(node);  
	 }  
	   
	 // 拼接孩子节点的JSON字符串  
	 public String toString() {  
	  String result = "[";    
	  for (Iterator it = list.iterator(); it.hasNext();) {  
	   result += ((Node) it.next()).toString();  
	   result += ",";  
	  }  
	  result = result.substring(0, result.length() - 1);  
	  result += "]";  
	  return result;  
	 }  
	 
	// 拼接孩子节点的JSON字符串  
		 public String toCheckString() {  
		  String result = "[";    
		  for (Iterator it = list.iterator(); it.hasNext();) {  
		   result += ((Node) it.next()).toCheckString();  
		   result += ",";  
		  }  
		  result = result.substring(0, result.length() - 1);  
		  result += "]";  
		  return result;  
		 }  
	   
	 // 孩子节点排序  
	 public void sortChildren() {  
	  // 对本层节点进行排序  
	  // 可根据不同的排序属性，传入不同的比较器，这里传入ID比较器  
	  Collections.sort(list, new NodeIDComparator());  
	  // 对每个节点的下一层节点进行排序  
	  for (Iterator it = list.iterator(); it.hasNext();) {  
	   ((Node) it.next()).sortChildren();  
	  }  
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	}  
	  
	/** 
	 * 节点比较器 
	 */  
	class NodeIDComparator implements Comparator {  
	 // 按照节点编号比较  
	 public int compare(Object o1, Object o2) {  
	  int j1 = Integer.parseInt(((Node)o1).id);  
	     int j2 = Integer.parseInt(((Node)o2).id);  
	     return (j1 < j2 ? -1 : (j1 == j2 ? 0 : 1));  
	 }   
	} 
