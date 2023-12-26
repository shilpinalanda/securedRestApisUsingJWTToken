package omfys.icm.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import omfys.icm.model.JwtRequest;
import omfys.icm.model.JwtResponse;
import omfys.icm.service.UserService;
import omfys.icm.utility.JWTUtility;


@RestController
public class HomeController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/jwt", method = RequestMethod.GET)
    public String home() {
    	
    	System.out.println("Welcome to Daily Code Buffer!! GET Method _______________");
        return "Welcome to Daily Code Buffer!!";
    }
    
    @RequestMapping(value = "/jwt_post", method = RequestMethod.POST)
    public String home_post(HttpServletRequest request,Model model,HttpServletResponse response) {
    	System.out.println("Welcome to Daily Code Buffer!! POST Method _______________");
        return "Welcome to Daily Code Buffer Post Method _________________!!";
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
    //public JwtResponse authenticate(@RequestParam(value="username") String username,@RequestParam(value="password") String password) throws Exception{

       
    	System.out.println("jwtRequest"+jwtRequest.toString());
    	try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                    		//username,password
                    		 jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
        		//= userService.loadUserByUsername(username);
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);
	

        return  new JwtResponse(token);
        
    }
    
    @PostMapping("/authenticate_token")
    public void authenticate_token(@RequestBody JwtRequest jwtRequest,HttpServletResponse response) throws Exception{
    //public JwtResponse authenticate(@RequestParam(value="username") String username,@RequestParam(value="password") String password) throws Exception{

       String json="";
    	System.out.println("authenticate_token jwtRequest"+jwtRequest.toString());
    	try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                    		//username,password
                    		 jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
        		//= userService.loadUserByUsername(username);
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);

       // return  new JwtResponse(token); 
        json = new Gson().toJson(token);
       
		response.setContentType("application/json");
		response.getWriter().write(json);
    }
}