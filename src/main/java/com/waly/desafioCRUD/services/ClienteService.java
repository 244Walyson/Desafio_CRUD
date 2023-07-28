package com.waly.desafioCRUD.services;

import com.waly.desafioCRUD.dto.ClienteDto;
import com.waly.desafioCRUD.entities.Cliente;
import com.waly.desafioCRUD.repositories.ClienteRepository;
import com.waly.desafioCRUD.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;


    @Transactional(readOnly = true)
    public ClienteDto findById(Long id){
         Cliente cliente = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ClienteDto(cliente);
    }

    @Transactional(readOnly = true)
    public Page<ClienteDto> findAll(Pageable pageable){
        Page<Cliente> result = repository.findAll(pageable);
        return result.map(ClienteDto::new);
    }

    @Transactional
    public ClienteDto insert(ClienteDto dto){
        Cliente entity = new Cliente();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClienteDto(entity);
    }

    @Transactional
    public ClienteDto update(ClienteDto dto, Long id){
        Cliente entity = repository.getReferenceById(id);
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClienteDto(entity);
    }

    @Transactional
    public void delete(Long id){
       if(!repository.existsById(id)){
           throw new ResourceNotFoundException("recurso não encontrado");
       }
       repository.deleteById(id);
    }

    private void copyDtoToEntity(ClienteDto dto, Cliente entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setBirthDate(dto.getBirthDate());
        entity.setIncome(dto.getIncome());
        entity.setChildren(dto.getChildren());
    }

}
