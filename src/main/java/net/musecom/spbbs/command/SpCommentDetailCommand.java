package net.musecom.spbbs.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import net.musecom.spbbs.dao.SpCommentDao;
import net.musecom.spbbs.dto.SpCommentDto;

public class SpCommentDetailCommand implements SpCommentCommand {
	@Override
	public void comExecute(Model model) {
		
        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");
		SpCommentDao dao = new SpCommentDao();
		
		ArrayList<SpCommentDto> dtos = dao.list();
		model.addAttribute("list", dtos);
		
	}
}
