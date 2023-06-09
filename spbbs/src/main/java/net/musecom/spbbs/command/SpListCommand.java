package net.musecom.spbbs.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import net.musecom.spbbs.dao.SpDao;
import net.musecom.spbbs.dto.SpDto;

public class SpListCommand implements SpCommand {

	@Override
	public void execute(Model model) {
		
		SpDao dao = new SpDao();
		ArrayList<SpDto> dtos = dao.list();

		model.addAttribute("list", dtos);
		
	}

}
