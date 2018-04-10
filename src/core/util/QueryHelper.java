package core.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成hql语句
 * @author linghaoxinpian
 *
 */
public class QueryHelper {
	
	/**
	 * 降序
	 */
	public static String ORDER_BY_DESC="DESC";
	
	/**
	 * 升序
	 */
	public static String ORDER_BY_ASC="ASC";
	
	private String whereClause="";
	private String orderByClause="";
	private String fromClause="";
	private List<Object> parameters;
	
	/**
	 * 构造方法
	 * @param clazz 表名对应的实体类
	 * @param alias 别名
	 */
	public QueryHelper(Class clazz,String alias){
		fromClause=" FROM "+clazz.getSimpleName()+" "+alias;
	}
	
	/**
	 * 添加 where 条件
	 * @param condition 条件,如 entity.title like 
	 * @param params	条件参数  %...%
	 */
	public void addCondition(String condition,Object...params){
		if(whereClause.length()>1){
			//非第一个条件
			whereClause+=" AND "+condition;
		}else{
			//第一个条件
			whereClause=" WHERE "+condition;
		}
		
		if(params!=null){
			parameters=new ArrayList<Object>();
			for(Object obj :params){
				parameters.add(obj);
			}
		}
		
	}

	public void addOrderBy(String property,String order){
		if(orderByClause.length()>1){
			//非第一个条件
			orderByClause+=" , "+property+" "+order;
		}else{
			//第一个条件
			orderByClause=" ORDER BY "+property+ " "+ order;
		}
	}
	
	public String getQueryListHql(){
		return fromClause+whereClause+orderByClause;
	}

	
//------------------------属性-----------------------	
	public List<Object> getParameters() {
		return parameters;
	}

	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}
	
	
}






















