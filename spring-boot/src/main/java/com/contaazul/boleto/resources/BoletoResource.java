package com.contaazul.boleto.resources;

import com.contaazul.boleto.beans.BoletoBean;
import com.contaazul.boleto.services.BoletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/rest/bankslips")
public class BoletoResource {

    @Autowired
    private BoletoService boletoService;

    @GetMapping
    public List<BoletoBean> listarBoletos() {
        return boletoService.listarBoletos();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public BoletoBean criarBoleto(@Valid @RequestBody BoletoBean boletoBean) {
        return boletoService.criarBoleto(boletoBean);
    }

    @GetMapping("/{id}")
    public BoletoBean detalhesBoleto(@NotNull(message = "Id required") @PathVariable String id) {
        return boletoService.detalhesBoleto(id);
    }

    @PostMapping("/{id}/payments")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void marcarComoPago(@NotNull(message = "Id required") @PathVariable String id){
        boletoService.marcarComoPago(id);
    }




    /*


    @RequestMapping(method = RequestMethod.POST)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public BoletoBean criarBoleto(@RequestBody BoletoBean boletoBean) {
        return boletoService.criarBoleto(boletoBean);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody BoletoBean boletoBean) {
        boletoService.update(boletoBean);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        boletoService.delete(id);
    }*/
}
