package br.unifor.saa.restful;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.unifor.saa.to.EnderecoTO;

/**
 * @author adrianopatrick@gmail.com
 * @since 16 de dez de 2015
 */
@RestController
public class ConsultaCEP {

	public static EnderecoTO getEndereco(String cep) {

		final String uri = "http://api.postmon.com.br/v1/cep/{cep}";
		ResourceBundle bundle = ResourceBundle.getBundle("proxy");

		Map<String, String> params = new HashMap<>();
		params.put("cep", cep.trim().replace(".", "").replace("-", ""));

		RestTemplate restTemplate = null;
		if (Boolean.parseBoolean(bundle.getString("proxy.enable"))) {
			SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
			Proxy proxy = new Proxy(Proxy.Type.HTTP,
					new InetSocketAddress(bundle.getString("proxy.host"),
							Integer.parseInt(bundle.getString("proxy.port"))));
			clientHttpRequestFactory.setProxy(proxy);
			restTemplate = new RestTemplate(clientHttpRequestFactory);
		} else {
			restTemplate = new RestTemplate();
		}

		return restTemplate.getForObject(uri, EnderecoTO.class, params);
	}
	
	public static void main(String[] args) {
		EnderecoTO enderecoTO = ConsultaCEP.getEndereco("60135460");
		System.out.println(enderecoTO);
	}

}
