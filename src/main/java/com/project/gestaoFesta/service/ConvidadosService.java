package com.project.gestaoFesta.service;

import com.project.gestaoFesta.model.Convidado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.gestaoFesta.repository.Convidados;

import java.util.List;
import java.util.Optional;

//Camada de serviço para ligar o controller com o repository
@Service
public class ConvidadosService {

    @Autowired
    private Convidados repository;

    //método responsável por salvar um convidado
    public Convidado save(Convidado convidado) {
        repository.save(convidado);
        return convidado;
    }

    //método responsável por remover um convidado
    public Optional<Convidado> delete(Long id) {
        Optional<Convidado> convidado = this.findById(id);
        repository.deleteById(id);
        return convidado;
    }

    //Busca e Retorna um convidado pelo seu Id
    public Optional<Convidado> findById(Long id) {
        return repository.findById(id);
    }

    //Busca e retorna uma lista com todos os convidados
    public List<Convidado> findAll(){
        List<Convidado> list = repository.findAll();
        return list;
    }

    //Confirma a presença de um convidado
    public Convidado checkPresence(Long id_convidado) {
        Optional<Convidado> aux = this.repository.findById(id_convidado);//Busca o convidado pelo Id

        aux.get().setConfirmado(true);//Muda seu status de presença para true

        return this.repository.saveAndFlush(aux.get());
    }
}
