package com.project.gestaoFesta.repository;

import com.project.gestaoFesta.model.Convidado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Convidados extends JpaRepository<Convidado, Long> {
}