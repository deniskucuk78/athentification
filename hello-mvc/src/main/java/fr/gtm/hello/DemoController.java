package fr.gtm.hello;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemoController {
	@Autowired ClientDao dao;
	
	@GetMapping("/")
	public String hello(@RequestParam (name="name", defaultValue = "John", required = false) String name, Model model) {
		String message = "Bon anniversaire " + name;
		model.addAttribute("message", message);
		return "home";
	}
	
	
	@GetMapping("/signin")
	public String signin(Model model) {
		User user = new User();
		model.addAttribute("user", user);

		return "signin";
	}
	
	@PostMapping("/connexion")
	public String connexion(User user, Model model) {
		
		List<User> users = dao.findAll();
		
		for(User u : users) {
			if(u.getNom().equals(user.getNom()) && u.getPassword().equals(user.getPassword()))
			{
				model.addAttribute("user", user);
				return "ok";
			}	
		}
	
		return "signin";
	}
	
	
	//Avec Optional
	
	@PostMapping("/connexion2")
	public String connexion2(User user, Model model) {
		
		Optional<User> opt = dao.findByNomAndPassword(user.getNom(), user.getPassword());
		
		if(opt.isPresent())
		{
			model.addAttribute("user", opt.get());
			return "ok";
		}
	
		model.addAttribute("user", user);
		return "signin";
	}
	

	
}
		


	
