package br.com.belval.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.belval.crud.model.Cliente;
import br.com.belval.crud.repository.ClienteRepository;

@Controller
public class ClienteController {
	@Autowired
	private ClienteRepository repository;
	
	@GetMapping("/especiarias/cadastro")
	public ModelAndView novo() {
	ModelAndView modelAndView = new ModelAndView("newcadastro");
	modelAndView.addObject("cliente", new Cliente());
	return modelAndView;
	}
	

	@PostMapping("/especiarias/cadastro")
	public ModelAndView novo(Cliente cliente, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:/especiarias/inicio");
		String msg = "";
		if (cliente.getId() == 0) {
			msg = "Novo produto criado!";
		} else {
			msg = "Produto atualizado!";
		}
		redirectAttributes.addFlashAttribute("msg", msg);
		repository.save(cliente);
		return modelAndView;
	}
	
	@GetMapping("/especiarias/cadastro/admin")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("listaCadastros");
		modelAndView.addObject("cliente", repository.findAll());
		return modelAndView;
	}
	
	@GetMapping("/cliente/{id}")
	public String detalhe(@PathVariable("id") int id, Model model) {
		Cliente c = repository.findById(id);
		if (c == null) {
			return "clientenaoencontrado";
		}
		model.addAttribute("cliente", c);
		return "detalheCliente";
	}
	@GetMapping("/cliente/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		Cliente c = repository.findById(id);
		if (c == null) {
			return "cliente-nao-encontrado";
		}
		model.addAttribute("cliente", c);
		return "newcadastro";
	}
	@GetMapping("/cliente/{id}/delete")
	public String delete (@PathVariable("id") int id) {
		Cliente c = repository.findById(id);
		if (c == null) {
			return "cliente-nao-encontrado";
		}
		repository.delete(c);
		return "redirect:/especiarias/cadastro/admin";

  }
	
	@GetMapping("/especiarias/inicio")
	public String inicial() {
		return "paginainicial";
	}
	
	@GetMapping("/especiarias/produtos")
	public String produtos() {
		return "paginaprodutos";	
	}
	
	@GetMapping("/especiarias/login")
	public String login() {
		return "pagLogin";
	}
	
	@GetMapping("/especiarias/sobrenos")
	public String nos() {
		return "sobrenos";
	}
}
