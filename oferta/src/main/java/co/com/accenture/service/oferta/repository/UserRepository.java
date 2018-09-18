package co.com.accenture.service.oferta.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;


import co.com.accenture.service.oferta.model.OfertasRequest;

@EnableScan
public interface UserRepository extends CrudRepository<OfertasRequest, String>{
	//public List<RegistrarRequest> findByEmail(String email);
	public List<OfertasRequest> findByIdnegocio (String idnegocio);
	public List<OfertasRequest> findByIdnegocioAndTipooferta (String idnegocio, String tipooferta);
	public List<OfertasRequest> findByIdnegocioAndTipoofertaAndDescuento (String idnegocio, String tipooferta, String tag);
	
}
