package taxservice.info.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import com.opensymphony.xwork2.ActionContext;
import taxservice.info.entity.Info;
import taxservice.info.service.InfoService;
import core.action.BaseAction;

public class InfoAction extends BaseAction {	
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private InfoService infoService;
	private List<Info> infoList;
	private Info info;
	private String[] privilegeIds;

	//列表页面
	public String listUI(){
		ActionContext.getContext().put("infoTypeMap", Info.INFO_TYPE_MAP);
		infoList = infoService.findObjects();
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
