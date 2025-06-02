package com.integration.lawyer.Service;

import com.integration.lawyer.Model.Cliente;
import com.integration.lawyer.Repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void delete(Integer id) {
        clienteRepository.deleteById(id);
    }

    public Cliente findById(Integer id) {

        return clienteRepository.findById(id).orElse(null);
    }
}
