package com.contaazul.boleto.resources;

import com.contaazul.boleto.beans.BoletoBean;
import io.swagger.annotations.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Api(description = "Efetuar operações LISTAR, CRIAR, VER DETALHES, PAGAR e CANCELAR para boletos.")
@ApiResponses(
        value = {
                @ApiResponse(code = 201, message = "Bankslip created"),
                @ApiResponse(code = 204, message = "No content"),
                @ApiResponse(code = 400, message = "Bankslip not provided in the request body"),
                @ApiResponse(code = 404, message = "Bankslip not found with the specified id"),
                @ApiResponse(code = 422, message = "Invalid bankslip provided.The possible reasons are:\n" +
                        "* A field of the provided bankslip was null or with invalid values"),
        }
)
public interface IBoletoResource {

    @ApiOperation(value = "Lista boletos", response = BoletoBean.class)
    List<BoletoBean> listarBoletos();

    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Bankslip created"),
                    @ApiResponse(code = 422, message = "Invalid bankslip provided.The possible reasons are:\n" +
                            "* A field of the provided bankslip was null or with invalid values"),
            }
    )
    @ApiOperation(value = "Criar Boleto", response = BoletoBean.class)
    BoletoBean criarBoleto(BoletoBean boletoBean);

    @ApiOperation(value = "Ver detalhes do Boleto", response = BoletoBean.class)
    BoletoBean detalhesBoleto(@ApiParam(value = "ID do boleto", required = true) String id);

    @ApiOperation(value = "Pagar um Boleto", response = BoletoBean.class)
    void pagarBoleto(@ApiParam(value = "ID do boleto", required = true) String id, HashMap<String, LocalDate> requestData);

    @ApiOperation(value = "Cancelar um Boleto", response = BoletoBean.class)
    void cancelarBoleto(@ApiParam(value = "ID do boleto", required = true) String id);
}
