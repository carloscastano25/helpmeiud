package co.edu.iudigital.helpmeiud.controllers;

import co.edu.iudigital.helpmeiud.exceptions.RestException;
import co.edu.iudigital.helpmeiud.models.Delito;
import co.edu.iudigital.helpmeiud.services.ifaces.IDelitoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/delitos")
//@Api(value = "/delitos", tags = {"Delitos"})
public class DelitoController {

    // TODO: USAR DTOS ???

    @Autowired
    private IDelitoService delitoService;

    @ApiResponses(
            value = {
               @ApiResponse(responseCode = "400", description = "Bad Request"),
               @ApiResponse(responseCode = "401", description = "Unauthorized"),
               @ApiResponse(responseCode = "403", description = "Forbidden"),
               @ApiResponse(responseCode = "500", description = "Internal Error Server")
            }
    )

    @Operation(
            summary = "Guardar un delito",
            description = "Endpoint para guardar un delito"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Delito> save(@Valid @RequestBody Delito delito) throws RestException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(delitoService.crearDelito(delito));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),// TODO: AGREGAR EXCEPTION PERSONALIZADA
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "500", description = "Internal Error Server")
            }
    )

    @Operation(
            summary = "Actualizar un delito",
            description = "Endpoint para actualizar un delito por ID"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public ResponseEntity<Delito> update(
            @RequestBody Delito delito,
            @PathVariable(value = "id") Long id
    ) throws RestException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(delitoService.actualizarDelitoPorID(id, delito));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),// TODO: AGREGAR EXCEPTION PERSONALIZADA
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "500", description = "Internal Error Server")
            }
    )

    @Operation(
            summary = "Eliminar un delito",
            description = "Endpoint para eliminar un delito por ID"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable(value = "id") Long id
    ) throws RestException {
        delitoService.eliminarDelitoPorID(id);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"), // TODO: AGREGAR EXCEPTION PERSONALIZADA
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "500", description = "Internal Error Server")
            }
    )

    @Operation(
            summary = "Consultar un delito",
            description = "Endpoint para consultar un delito por ID"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<Delito> getOne(
            @PathVariable(value = "id") Long id
    ) throws RestException {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(delitoService.consultarDelitoPorID(id));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "500", description = "Internal Error Server")
            }
    )


    @Operation(
            summary = "Consultar todos los delitos",
            description = "Endpoint para consultar todos los delitos"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<List<Delito>> index() throws RestException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(delitoService.consultarDelitos());
    }

}