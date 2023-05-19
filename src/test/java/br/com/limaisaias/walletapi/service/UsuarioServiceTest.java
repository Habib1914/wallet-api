package br.com.limaisaias.walletapi.service;

import br.com.limaisaias.walletapi.entity.Usuario;
import br.com.limaisaias.walletapi.repository.UsuarioRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioServiceTest {

    @MockBean
    UsuarioRepository repository;

    @Autowired
    UsuarioService service;

    @Before
    public void setUp(){
        BDDMockito.given(repository.findByEmailEquals(Mockito.anyString())).willReturn(Optional.of(new Usuario()));
    }

    @After
    public void tearDown(){}

    @Test
    public void testFindByEmail(){
        Optional<Usuario> user = service.findByEmail("email@email.com");
        Assert.assertNotNull(user.get());
    }

}
