package com.project.gestaoFesta;

import com.project.gestaoFesta.model.Convidado;
import com.project.gestaoFesta.repository.Convidados;
import com.project.gestaoFesta.service.ConvidadosService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
@Sql(scripts = "/postgres/insert_for_Test.sql")
public class ConvidadosServiceTest {

    @Autowired
    private ConvidadosService serviceTest;

    @Autowired
    private Convidados repositoryTest;

    @Test
    public void saveTest(){
        Convidado convidado = new Convidado();
        convidado.setNome("convidadoTeste");
        convidado.setQuantidadeAcompanhantes(2);
        convidado.setConfirmado(false);
        serviceTest.save(convidado);

        Optional<Convidado> buscaInsert = repositoryTest.findById(convidado.getId());
        assertTrue(buscaInsert.isPresent());
        serviceTest.delete(convidado.getId());
    }

    @Test
    public void findByIdTest(){
        Optional<Convidado> convidado = serviceTest.findById(500L);
        assertEquals("convidadoTeste",convidado.get().getNome());
        assertEquals(2,convidado.get().getQuantidadeAcompanhantes());
        assertEquals(false,convidado.get().getConfirmado());
    }

    @Test
    public void checkPresence(){
        Convidado convidado = serviceTest.checkPresence(500L);
        assertEquals(true,convidado.getConfirmado());
    }

    @Test
    public void deleteTest(){
        serviceTest.delete(500L);
        Optional<Convidado> convidado = repositoryTest.findById(500L);
        assertFalse(convidado.isPresent());
    }


}
