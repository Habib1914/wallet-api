package br.com.limaisaias.walletapi.controller;

import br.com.limaisaias.walletapi.dto.UsuarioDTO;
import br.com.limaisaias.walletapi.entity.Usuario;
import br.com.limaisaias.walletapi.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    public static final Long ID = 1L;
    public static final String EMAIL = "test@test.com";
    public static final String SENHA = "******";
    public static final String NAME = "teste";
    private static final String URL = "/user";

    @MockBean
    UsuarioService usuarioService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testSave() throws Exception {
        BDDMockito.given(usuarioService.save(BDDMockito.any())).willReturn(getMockUser());
        mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, NAME, SENHA, EMAIL)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value(ID))
                .andExpect(jsonPath("$.data.name").value(NAME))
                .andExpect(jsonPath("$.data.email").value(EMAIL))
                .andExpect(jsonPath("$.data.password").value(SENHA));
    }

    @Test
    public void testInvalidSave() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(URL).content(getJsonPayload(ID, "", "SENHA", "EMAIL")).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").isNotEmpty());
    }

    public Usuario getMockUser() {
        return Usuario.builder().id(ID).name(NAME).password(SENHA).email(EMAIL).build();
    }

    public String getJsonPayload(Long id, String name, String senha, String email) throws JsonProcessingException {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setPassword(senha);
        dto.setEmail(email);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(dto);
    }

}

