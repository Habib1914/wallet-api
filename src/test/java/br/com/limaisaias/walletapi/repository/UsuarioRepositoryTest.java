package br.com.limaisaias.walletapi.repository;

import br.com.limaisaias.walletapi.entity.Usuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

    private static final String EMAIL = "email-setup@teste.com";
    @Autowired
    UsuarioRepository userRepository;

    @Before
    public void setUp() {
        Usuario u = new Usuario();
        u.setName("Setup User");
        u.setPassword("******");
        u.setEmail(EMAIL);
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void testSave() {
        Usuario u = new Usuario();
        u.setName("Teste");
        u.setPassword("******");
        u.setEmail("test@test.com");

        Usuario response = userRepository.save(u);
        assertNotNull(response);
    }

    @Test
    public void testFindByEmail() {
        assertNotNull(userRepository.findByEmailEquals(EMAIL));
    }
}
