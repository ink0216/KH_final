package kh.em.albaya.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.em.albaya.board.model.service.DeclareService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("declare")
@RequiredArgsConstructor
public class DeclareController {
	
	private final DeclareService service;

}
