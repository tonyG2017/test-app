package org.tony;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.tony.mapper.UserMapper;
import org.tony.model.User;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AppUserControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void before(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @MockBean
    private UserMapper userMapper;

    private static User user = User.builder()
            .fullName("Tom")
            .id(1)
            .jobTitle("ic4")
            .password("12345")
            .build();
    private static ObjectMapper mapper = new ObjectMapper();
    //        or use string directly
    //        String userJsonStr = "{\"id\":0, \"fullName\":\"Tom\", \"jobTitle\":\"ic4\", \"password\":\"12345\"}";
    //https://blog.csdn.net/xiaoyu19910321/article/details/78595302
    //https://blog.csdn.net/wo541075754/article/details/88983708
    @Test
    public void appUserControllerAddUser() throws Exception {
        String userJsonStr = mapper.writeValueAsString(user);
        when(userMapper.addUser(user)).thenReturn(2);
        String response = mockMvc.perform(
                post("/users").contentType(MediaType.APPLICATION_JSON).content(userJsonStr).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(0)))
                .andExpect(jsonPath("$.fullName", is("Tom")))
                .andExpect(jsonPath("$.jobTitle",is("ic4")))
                .andExpect(jsonPath("$.password",is("12345")))
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println("&&&&&&&&appUserControllerAddUser&&&&&&g&&&&response"+response);
    }

    @Test
    public void appUserControllerUpdateUserById() throws Exception{
        String userJsonStr = mapper.writeValueAsString(user);
        when(userMapper.updateUserById(user)).thenReturn(1);
        mockMvc.perform(put("/users/1").contentType(MediaType.APPLICATION_JSON).content(userJsonStr).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void appUserControllerGetById() throws Exception {
        when(userMapper.getUserById(1)).thenReturn(user);
        String response = mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.fullName", is("Tom")))
                .andExpect(jsonPath("$.jobTitle",is("ic4")))
                .andExpect(jsonPath("$.password",is("12345")))
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println("!!!!!!!!!!!!AppUserControllerTest!!!response"+response);
    }

    @Test
    public void appUserControllerDeleteUserById() throws Exception {
        when(userMapper.deleteUserById(123)).thenReturn(1);
        mockMvc.perform(delete("/users/123"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
