package com.project.gestaoFesta.service;

import com.project.gestaoFesta.model.Convidado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.gestaoFesta.repository.Convidados;

import java.util.List;
import java.util.Optional;

@Service
public class ConvidadosService {

    @Autowired
    private Convidados repository;

    //testado
    public Convidado save(Convidado convidado) {
        repository.save(convidado);
        return convidado;
    }

    //testado
    public Optional<Convidado> delete(Long id) {
        Optional<Convidado> convidado = this.findById(id);
        repository.deleteById(id);
        return convidado;
    }

    //testado
    public Optional<Convidado> findById(Long id) {
        return repository.findById(id);
    }

    public List<Convidado> findAll(){
        List<Convidado> list = repository.findAll();
        return list;
    }

    public Convidado checkPresence(Long id_convidado) {
        Optional<Convidado> aux = this.repository.findById(id_convidado);

        aux.get().setConfirmado(true);

        return this.repository.saveAndFlush(aux.get());
    }
}
