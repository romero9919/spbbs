package net.musecom.spbbs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.musecom.spbbs.command.SpCommand;
import net.musecom.spbbs.command.SpDeleteCommand;
import net.musecom.spbbs.command.SpDetailCommand;
import net.musecom.spbbs.command.SpListCommand;
import net.musecom.spbbs.command.SpUpdateCommand;
import net.musecom.spbbs.command.SpUpdateokCommand;
import net.musecom.spbbs.command.SpWriteCommand;
import net.musecom.spbbs.dto.PageDto;
import net.musecom.spbbs.util.Pagination;
import net.musecom.spbbs.util.Static;

@Controller
public class SpController {
	
	SpCommand command;
	
	//jdbc Spring template 
	public JdbcTemplate template;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Static.template = this.template;
	}
  
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		System.out.println("list()");
		model.addAttribute("request", request);
		command = new SpListCommand();
		command.execute(model);
		PageDto pdto = new PageDto();
        pdto.setPage(pdto.getPage());
		Pagination pages = new Pagination();
		pages.setDisplayPageNum(10);
		pages.setTotalCount();
		pages.setPdto(pdto);
		pages.makeQuery(1);
		model.addAttribute("pages", pages);
	    return "list";	
	}
	
	@RequestMapping("/detail")
	public String detail(HttpServletRequest request, Model model) {
		System.out.println("detail()");
		
		model.addAttribute("request", request);
		command = new SpDetailCommand();
		command.execute(model);
		
		return "detail";
	}
	
	@RequestMapping("/write")
	public String write(Model model) {
		System.out.println("write()");
		return "write";
	}
	
	@RequestMapping(value="/writeok", method=RequestMethod.POST)
	public String writeok(HttpServletRequest request, Model model) {
		System.out.println("writeok");
		
		model.addAttribute("request", request);
		command = new SpWriteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
//	@RequestMapping("/reply")
//	public String reply(HttpServletRequest request, Model model) {
//		System.out.println("reply()");
//		
//		model.addAttribute("request", request);
//		command = new SpReplyCommand();
//		command.execute(model);
//		
//		return "reply";
//	}
//	
//	@RequestMapping(value="/replyok", method=RequestMethod.POST)
//	public String replyok(HttpServletRequest request, Model model) {
//		System.out.println("replyok()");
//		
//		model.addAttribute("request", request);
//		command = new SpReplyokCommand();
//		command.execute(model);
//		
//		return "redirect:list";
//	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request, Model model) {
		System.out.println("update()");
		
		model.addAttribute("request", request);
		command = new SpUpdateCommand();
		command.execute(model);
		
		return "update";
	}
	
	@RequestMapping(value="/updateok", method=RequestMethod.POST)
	public String updateok(HttpServletRequest request, Model model) {
		System.out.println("updateok()");
		
		model.addAttribute("request", request);
		command = new SpUpdateokCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete()");
		
		model.addAttribute("request", request);
		command = new SpDeleteCommand();
		command.execute(model);
	 
		return "redirect:list";
	}
	
}
