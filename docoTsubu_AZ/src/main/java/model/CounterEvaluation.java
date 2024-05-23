package model;

import dao.MutterDAO;

public class CounterEvaluation {
	MutterDAO dao = new MutterDAO();
	public void goodCountUp(int no) {
		dao.goodCountUp(no);
	}
	
	public void badCountUp(int no) {
		dao.badCountUp(no);
	}
}
