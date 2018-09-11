package co.com.accenture.service.oferta.api;

import co.com.accenture.service.oferta.model.JsonApiBodyRequest;
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
public class EditarApiController implements EditarApi {

	@Autowired
	UserRepository ofertaRepository;
    private static final Logger log = LoggerFactory.getLogger(EditarApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public EditarApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<?> editarPut(@ApiParam(value = "oferta que va a ser editada" ,required=true )  @Valid @RequestBody JsonApiBodyRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            JsonApiBodyResponseSuccess respuestaExitosa = new JsonApiBodyResponseSuccess();
			JsonApiBodyResponseErrors responseError = new JsonApiBodyResponseErrors();
			
			String idoferta = body.getOferta().get(0).getIdoferta();
			String idnegocio = body.getOferta().get(0).getIdnegocio();
			String producto = body.getOferta().get(0).getProducto();
			String detalle = body.getOferta().get(0).getDetalle();
			
			OfertasRequest oferta =ofertaRepository.findOne(idoferta);
			if(oferta == null) {
				responseError.setCodigo("3");
				responseError.setDetalle("La oferta a editar no existe");
				return new ResponseEntity<JsonApiBodyResponseErrors>(responseError, HttpStatus.NOT_IMPLEMENTED);

			}
			OfertasRequest ofertaeditada = ofertaRepository.save(body.getOferta().get(0));
			respuestaExitosa.setIdoferta(idoferta);
			respuestaExitosa.setIdnegocio(idnegocio);
			respuestaExitosa.setProducto(producto);
			respuestaExitosa.setDetalle(detalle);
			
			return new ResponseEntity<JsonApiBodyResponseSuccess>(respuestaExitosa, HttpStatus.OK);
        }

        return new ResponseEntity<JsonApiBodyResponseSuccess>(HttpStatus.NOT_IMPLEMENTED);
    }

}
