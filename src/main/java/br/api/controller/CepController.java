//package br.api.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//import br.api.service.CepService;
//import br.api.entities.Adress;
//import br.api.repository.CepRepository;
//
//@RestController
//public class CepController {
//
//    @Autowired
//    private CepService cepService;
//
//
//    @Autowired
//    private CepRepository cepRepository;
//
//    @GetMapping("/{cep}")
//    public ModelAndView getCep(@PathVariable String cep) {
//
//        Adress adress = cepService.findAdressByCep(cep);
//
//        ModelAndView modelAndView = new ModelAndView("adress");
//        modelAndView.addObject("adress", adress);
//
//        return modelAndView;
//    }
//
//    @PostMapping("/{cep}")
//    public ModelAndView post(@PathVariable String cep) {
//        Adress adress = cepService.findAdressByCep(cep);
//
//        cepRepository.save(adress);
//
//        ModelAndView modelAndView = new ModelAndView("adress");
//        modelAndView.addObject("adress", adress);
//
//        return modelAndView;
//    }
//
//}