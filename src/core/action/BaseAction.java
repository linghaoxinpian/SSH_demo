package core.action;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	protected String[] selectedRow;
	
	public String[] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
}
