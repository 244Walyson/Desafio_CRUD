package com.waly.desafioCRUD.repositories;

import com.waly.desafioCRUD.entities.Cliente;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
