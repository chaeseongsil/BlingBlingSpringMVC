package kr.co.blingbling.qna.service;

import kr.co.blingbling.qna.domain.PageData;
import kr.co.blingbling.qna.domain.Qna;

public interface QnaService {
	public int insertQna(Qna qna);

	public int updateQna(Qna qna);
	
	public int deleteQna(int myQnaNo);
	
	public PageData selectAllQnaList(int currentPage);

	public PageData selectMyQnaList(int currentPage, String myQnaWriter);
	
	public Qna selectOneByNo(int myQnaNo);
}
