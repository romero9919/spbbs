package net.musecom.spbbs.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import net.musecom.spbbs.dao.SpDao;

public class SpWriteCommand implements SpCommand {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request"); 
        
        String writer = request.getParameter("writer");
        String userid = request.getParameter("userid");
        String userpass = request.getParameter("userpass");
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        
        SpDao dao = new SpDao();
        dao.write(writer, userid, userpass, title, contents);
        
	}

}
