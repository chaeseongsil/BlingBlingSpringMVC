package kr.co.blingbling.notice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.blingbling.notice.domain.Notice;
import kr.co.blingbling.notice.domain.PageData;
import kr.co.blingbling.notice.service.NoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
	@RequestMapping(value="/board/noticeList.do", method=RequestMethod.GET)
	public String showNoticeList(
			HttpServletRequest request
			, Model model) {
		String page = request.getParameter("currentPage") != null ? request.getParameter("currentPage") : "1";
		int currentPage = Integer.parseInt(page);
		PageData pd = service.selectNoticeList(currentPage);
		List<Notice> nList = pd.getnList();
		model.addAttribute("nList", nList);
		model.addAttribute("pageNavi", pd.getPageNavi());
		return "notice/notice";
	}
	
	@RequestMapping(value="/board/noticeDetail.do", method=RequestMethod.GET)
	public String showDetailNotice(
			@RequestParam("noticeNo") int noticeNo
			, Model model
			) {
		Notice notice = service.selectOneByNo(noticeNo);
		model.addAttribute("notice", notice);
		return "notice/noticeView";
	}
}
