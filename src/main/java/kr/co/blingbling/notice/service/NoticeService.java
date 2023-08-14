package kr.co.blingbling.notice.service;

import kr.co.blingbling.notice.domain.Notice;
import kr.co.blingbling.notice.domain.PageData;

public interface NoticeService {
	
	public PageData selectNoticeList(int currentPage);

	public Notice selectOneByNo(int noticeNo);

}
