package co.com.accenture.service.oferta.api;

import co.com.accenture.service.oferta.model.JsonApiBodyRequest;
import co.com.accenture.service.oferta.model.JsonApiBodyResponseErrors;
import co.com.accenture.service.oferta.model.OfertasRequest;
import co.com.accenture.service.oferta.repository.UserRepository;

import com.amazonaws.Response;
import com.amazonaws.services.codedeploy.model.RegisterApplicationRevisionRequest;
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
import java.util.ArrayList;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-26T21:24:59.171-05:00")

@Controller
public class ListarApiController implements ListarApi {
	 @Autowired
	 UserRepository ofertaRepository;
	
    private static final Logger log = LoggerFactory.getLogger(ListarApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ListarApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<?> listarGet() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            JsonApiBodyResponseErrors ResponseError = new JsonApiBodyResponseErrors();
			List<OfertasRequest> ofertas = (List<OfertasRequest>) ofertaRepository.findAll();
			JsonApiBodyRequest body = new JsonApiBodyRequest();
			body.setOferta(ofertas);
			
			if(ofertas.isEmpty()) {
				ResponseError.setCodigo("1");
				ResponseError.setDetalle("Base de datos vacia");
				return new ResponseEntity<JsonApiBodyResponseErrors>(ResponseError, HttpStatus.FAILED_DEPENDENCY);
			}
			return new ResponseEntity<JsonApiBodyRequest>(body, HttpStatus.OK);
        }

        return new ResponseEntity<JsonApiBodyRequest>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<?> listarIdofertaIdofertaGet(@ApiParam(value = "ID de la oferta",required=true) @PathVariable("idoferta") String idoferta) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
            		OfertasRequest oferta = ofertaRepository.findOne(idoferta);
            		if(idoferta == null || oferta == null) {
            			responseError.setCodigo("2222");
                    	responseError.setDetalle("Id_oferta no ingresado o no existe");
                    	return new ResponseEntity<JsonApiBodyResponseErrors> (responseError, HttpStatus.NOT_IMPLEMENTED);
            			
            		}
            		JsonApiBodyRequest body = new JsonApiBodyRequest();
            		List<OfertasRequest> lista = new ArrayList<OfertasRequest>();
            		lista.add(oferta);
            		body.setOferta(lista);
                return new ResponseEntity<JsonApiBodyRequest>(body, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                System.out.println("problemas al listar por idoferta");
            }
        }

        return new ResponseEntity<JsonApiBodyRequest>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    public ResponseEntity<?> listarIdnegocioIdnegocioGet(@ApiParam(value = "ID del negocio",required=true) @PathVariable("idnegocio") String idnegocio) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
            	JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
            		List<OfertasRequest> ofertas = ofertaRepository.findByIdnegocio(idnegocio);
            				
            		if(idnegocio == null || ofertas == null || ofertas.isEmpty()) {
            			responseError.setCodigo("2222");
                    	responseError.setDetalle("Id_negocio no ingresado o no existen ofertas para este megocio");
                    	return new ResponseEntity<JsonApiBodyResponseErrors> (responseError, HttpStatus.NOT_IMPLEMENTED);
            			
            		}
            		JsonApiBodyRequest body = new JsonApiBodyRequest();
    
            		body.setOferta(ofertas);
                return new ResponseEntity<JsonApiBodyRequest>(body, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                System.out.println("problemas al listar por idnegocio");
            }
        }

        return new ResponseEntity<JsonApiBodyRequest>(HttpStatus.NOT_IMPLEMENTED);
    }
    
    public ResponseEntity <?> listarIdnegocioIdnegocioTipoOfertaGet (@ApiParam(value = "ID del negocio",required=true) @PathVariable("idnegocio") String idnegocio, @PathVariable("tipooferta") String tipooferta) {
    	 String accept = request.getHeader("Accept");
         if (accept != null && accept.contains("application/json")) {
             try {
             	JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
             		List<OfertasRequest> ofertas = ofertaRepository.findByIdnegocioAndTipooferta(idnegocio, tipooferta);
             				
             		if(idnegocio == null || ofertas == null || ofertas.isEmpty() || tipooferta == null) {
             			responseError.setCodigo("2222");
                     	responseError.setDetalle("Id_negocio o tipo de oferta no ingresado o no existen ofertas para este negocio");
                     	return new ResponseEntity<JsonApiBodyResponseErrors> (responseError, HttpStatus.NOT_IMPLEMENTED);
             			
             		}
             		JsonApiBodyRequest body = new JsonApiBodyRequest();
     
             		body.setOferta(ofertas);
                 return new ResponseEntity<JsonApiBodyRequest>(body, HttpStatus.OK);
             } catch (Exception e) {
                 log.error("Couldn't serialize response for content type application/json", e);
                 System.out.println("problemas al listar por idnegocio y tipo de oferta");
             }
         }

         return new ResponseEntity<JsonApiBodyRequest>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<JsonApiBodyRequest> listarProductoProductoGet(@ApiParam(value = "ofertas por productos",required=true) @PathVariable("producto") String producto) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<JsonApiBodyRequest>(objectMapper.readValue("{  \"oferta\" : [ {    \"fechainicio\" : \"fechainicio\",    \"idnegocio\" : \"idnegocio\",    \"foto\" : \"foto\",    \"descuento\" : \"descuento\",    \"valor\" : \"valor\",    \"producto\" : \"producto\",    \"idoferta\" : \"idoferta\",    \"fechafinal\" : \"fechafinal\",    \"detalle\" : \"detalle\"  }, {    \"fechainicio\" : \"fechainicio\",    \"idnegocio\" : \"idnegocio\",    \"foto\" : \"foto\",    \"descuento\" : \"descuento\",    \"valor\" : \"valor\",    \"producto\" : \"producto\",    \"idoferta\" : \"idoferta\",    \"fechafinal\" : \"fechafinal\",    \"detalle\" : \"detalle\"  } ]}", JsonApiBodyRequest.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<JsonApiBodyRequest>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<JsonApiBodyRequest>(HttpStatus.NOT_IMPLEMENTED);
    }

	


	
}
