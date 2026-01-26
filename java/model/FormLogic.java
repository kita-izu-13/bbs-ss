package model;

import dao.FormDAO;

public class FormLogic {
	public boolean execute(Form form) {
		FormDAO dao = new FormDAO();
		Form form2 = dao.insert(form);
		return form2 != null;
	}
}
