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

    //Testando o método save do service
    @Test
    public void saveTest(){
        //Criação do objeto para ser salvo
        Convidado convidado = new Convidado();
        convidado.setNome("convidadoTeste");
        convidado.setQuantidadeAcompanhantes(2);
        convidado.setConfirmado(false);

        serviceTest.save(convidado);

        //Busca para verificar se foi adicionado com sucesso ou não
        Optional<Convidado> buscaInsert = repositoryTest.findById(convidado.getId());
        assertTrue(buscaInsert.isPresent());

        //Remoção após o teste para não ficar no banco de dados
        serviceTest.delete(convidado.getId());
    }

    //Testando o método findById do service
    @Test
    public void findByIdTest(){
        //Salvando o objeto com as informações passadas pelo script "insert_for_Test.sql"
        Optional<Convidado> convidado = serviceTest.findById(500L);

        //Verificação se as informações buscadas estão corretas
        assertEquals("convidadoTeste",convidado.get().getNome());
        assertEquals(2,convidado.get().getQuantidadeAcompanhantes());
        assertEquals(false,convidado.get().getConfirmado());
    }

    //Testando o método checkPresence do service
    @Test
    public void checkPresenceTest(){
        Convidado convidado = serviceTest.checkPresence(500L);
        assertEquals(true,convidado.getConfirmado());
    }

    //Testando o método delete do service
    @Test
    public void deleteTest(){
        serviceTest.delete(500L);

        //Verifica se após a remoção, as informações nao estao mais no banco de dados
        Optional<Convidado> convidado = repositoryTest.findById(500L);
        assertFalse(convidado.isPresent());
    }


}
