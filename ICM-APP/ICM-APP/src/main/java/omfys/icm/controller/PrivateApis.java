package omfys.icm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/priapi")
public class PrivateApis {

	@RequestMapping("/pritest")
	public String privateTest(HttpServletRequest request,Model model,HttpServletResponse response) {
		
		return "private class privateTest method api";
	}
	
	
}
