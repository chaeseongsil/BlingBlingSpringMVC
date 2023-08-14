package kr.co.blingbling.qna.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.blingbling.qna.domain.Qna;

public interface QnaStore {
	
	public int insertQna(SqlSession session, Qna qna);
	
	public int updateQna(SqlSession session, Qna qna);
	
	public int deleteQna(SqlSession session, int myQnaNo);
	/**
	 * ��ü Qna �� �� ��ȸ
	 * @param session
	 * @return result count(*)
	 */
	public int selectCountAllQna(SqlSession session);
	/**
	 *  ������ Qna �ۼ��� �� ��ȸ
	 * @param session
	 * @param myQnaWriter
	 * @return
	 */
	public int selectCountMyQna(SqlSession session, String myQnaWriter);
	
	public List<Qna> selectAllQnaList(SqlSession session, int currentPage);

	public List<Qna> selectMyQnaList(SqlSession session, int currentPage, String myQnaWriter);
	
	public Qna selectOneByNo(SqlSession session, int myQnaNo);
	
	public String generateAllPageNavi(SqlSession session, int currentPage);
	
	public String generateMyPageNavi(SqlSession session, int currentPage, String myQnaWriter);

}
