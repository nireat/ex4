package com.choa.ex4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.choa.freeboard.FreeboardServiceImpl;

@Controller
@RequestMapping(value="/freeboard/**")
public class FreeboardController {
	
	@Autowired
	private FreeboardServiceImpl freeboardServiceImpl;
	
	@RequestMapping(value="freeboardList", method=RequestMethod.GET)
	public String boardList(@RequestParam(defaultValue="1") Integer curPage, Model model) throws Exception{
		model.addAttribute("board", "freeboard");
		model.addAttribute("list", freeboardServiceImpl.boardList(curPage));
		return "board/boardList";
		
	}
	
	
	
}
