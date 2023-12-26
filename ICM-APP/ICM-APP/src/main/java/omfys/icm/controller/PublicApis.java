package omfys.icm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/pubapi")
public class PublicApis {
	
	@RequestMapping("/pubtest")
	public String pubTest() {
		
		return "public class pubTest method api";
	}

}
