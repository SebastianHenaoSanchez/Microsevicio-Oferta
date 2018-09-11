package co.com.accenture.service.oferta.api;

import co.com.accenture.service.oferta.model.JsonApiBodyResponseErrors;
import co.com.accenture.service.oferta.model.JsonApiBodyResponseSuccess;
import co.com.accenture.service.oferta.model.OfertasRequest;
import co.com.accenture.service.oferta.repository.UserRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-26T21:24:59.171-05:00")

@Controller
public class EliminarApiController implements EliminarApi {

	@Autowired
	UserRepository ofertaRepository;
    private static final Logger log = LoggerFactory.getLogger(EliminarApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public EliminarApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<?> eliminarIdofertaDelete(@ApiParam(value = "id de oferta a eliminar",required=true) @PathVariable("idoferta") String idoferta) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
        	
            JsonApiBodyResponseSuccess respuestaExitosa = new JsonApiBodyResponseSuccess();
			JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
			
			OfertasRequest oferta = ofertaRepository.findOne(idoferta);
			
			if(oferta == null) {
				responseError.setCodigo("2");
				responseError.setDetalle("No existe tal oferta");
				return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.NOT_IMPLEMENTED);
			}
			
			ofertaRepository.delete(idoferta);
			respuestaExitosa.setIdoferta(idoferta);
			respuestaExitosa.setIdnegocio(oferta.getIdnegocio());
			respuestaExitosa.setProducto(oferta.getProducto());
			respuestaExitosa.setDetalle(oferta.getDetalle());
            return new ResponseEntity<JsonApiBodyResponseSuccess>(respuestaExitosa,HttpStatus.OK);

        }

        return new ResponseEntity<JsonApiBodyResponseSuccess>(HttpStatus.NOT_IMPLEMENTED);
    }

}
