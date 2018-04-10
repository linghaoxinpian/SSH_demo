package taxservice.info.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import taxservice.info.entity.Info;
import taxservice.info.service.InfoService;
import core.action.BaseAction;
import core.util.QueryHelper;

public class InfoAction extends BaseAction {	
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private InfoService infoService;
	private List<Info> infoList;
	private Info info;
	private String[] privilegeIds;

	//列表页面
	public String listUI(){		
		if(info!=null){
			//搜索条件不为空
			QueryHelper queryHelper=new QueryHelper(Info.class,"i");
			queryHelper.addCondition("i.title like ?", "%"+info.getTitle()+"%");
			queryHelper.addOrderBy("i.createTime",QueryHelper.ORDER_BY_ASC);
			System.out.println(info.getTitle()+"------"+queryHelper.getQueryListHql());
			
			//查询
			infoList= infoService.findObjects(queryHelper);			
		}else{
			ActionContext.getContext().put("infoTypeMap", Info.INFO_TYPE_MAP);
			infoList = infoService.findObjects();
		}
		return "listUI";
	}
	//跳转到新增页面
	public String addUI(){
		ActionContext.getContext().put("infoTypeMap", Info.INFO_TYPE_MAP);
		info=new Info();
		info.setCreateTime(new Timestamp(new Date().getTime()));
		return "addUI";
	}
	//保存新增
	public String add(){
		try {
			if(info != null){				
				infoService.save(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "list";
	}
	//跳转到编辑页面
	public String editUI() {
		ActionContext.getContext().put("infoTypeMap", Info.INFO_TYPE_MAP);
		if (info != null && info.getInfoId() != null) {
			info = infoService.findObjectById(info.getInfoId());
		}
		return "editUI";	
	}
	//保存编辑
	public String edit() throws IOException{
		if(info != null){			
			infoService.update(info);
		}
		return "list";
	}
	//删除
	public String delete(){
		if(info != null && info.getInfoId() != null){
			infoService.delete(info.getInfoId());
		}
		return "list";
	}
	//批量删除
	public String deleteSelected(){
		if(selectedRow != null){
			for(String id: selectedRow){
				infoService.delete(id);
			}
		}
		return "list";
	}
	
	public void publicInfo() throws IOException{
		if(info!=null){
			info=infoService.findObjectById(info.getInfoId());
			info.setState(info.getState());
			infoService.update(info);
			
			//异步返回结果
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html");
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write("更新状态成功".getBytes("utf-8"));
			outputStream.close();			
		}
	}
		
//----------------属性----------------------	
	public List<Info> getInfoList() {
		return infoList;
	}
	public void setInfoList(List<Info> infoList) {
		this.infoList = infoList;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public String[] getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}	
	
}
