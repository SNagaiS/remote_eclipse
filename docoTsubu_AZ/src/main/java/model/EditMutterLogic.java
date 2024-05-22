package model;

import java.util.List;

import dao.MutterDAO;

public class EditMutterLogic {
	public void execute(int no,Mutter mutter, List<Mutter> mutterList,int getno) {
		mutter.setNo(getno); 
		mutterList.set(no,mutter);
	}
	
	
	MutterDAO dao = new MutterDAO();
	
	public boolean editCheck(Mutter mutter) {
		return dao.editCheck(mutter);
	}
	
	public void upDate(Mutter mutter) {
		dao.upDate(mutter);
	}
	
	public Mutter postMutter(int no) {
		Mutter mutter = dao.postMutter(no);
		return mutter;
	}
}
