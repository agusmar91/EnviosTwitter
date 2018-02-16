package com.envios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EnviosController {
	
	@RequestMapping(value="/entregar")
	public String principal() {
		//Redireccion hacia el form de entrega
		return "entregar";
	}

}
