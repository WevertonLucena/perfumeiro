package br.com.lucenasistemas.perfumeiro.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lucenasistemas.perfumeiro.model.Cliente;
import br.com.lucenasistemas.perfumeiro.repository.Clientes;
import br.com.lucenasistemas.perfumeiro.service.exception.CpfCnpjClienteJaCadastradoException;


@Service
public class ClienteService {

	@Autowired
	private Clientes clientes;
	
	@Transactional
	public void salvar(Cliente cliente) {
		
		Optional<Cliente> clienteExistente = clientes.findByCpfOuCnpj(
				cliente.getCpfOuCnpjSemFormatacao());
		if(clienteExistente.isPresent()){
			throw new CpfCnpjClienteJaCadastradoException("CPF/CNPJ já cadastrado");
		}
		clientes.save(cliente);
	}
	
}