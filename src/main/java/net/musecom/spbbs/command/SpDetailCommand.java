package net.musecom.spbbs.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import net.musecom.spbbs.dao.SpDao;
import net.musecom.spbbs.dto.SpDto;

public class SpDetailCommand implements SpCommand {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String cNum = request.getParameter("num");
		
		SpDao dao = new SpDao();
		SpDto dto = dao.detail(cNum);
		
		model.addAttribute("detail", dto);
	}

	
}
