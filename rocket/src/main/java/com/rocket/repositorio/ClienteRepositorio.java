package com.rocket.repositorio;

import com.rocket.models.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {


}
