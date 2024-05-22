package model;

import dao.MutterDAO;

public class DeleteMutterLogic {
	MutterDAO dao = new MutterDAO();
	
	public void execute(int no) {
		dao.deleteMutter(no);
	}
	
	public void allDelete() {
		dao.allDeleteMutter();
	}
}
