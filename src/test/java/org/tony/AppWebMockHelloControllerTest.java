package org.tony;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.tony.controller.HelloContoller;
import org.tony.service.HelloService;

import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HelloContoller.class)
public class AppWebMockHelloControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloService helloService;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        when(helloService.sayHello()).thenReturn("hello world");
        this.mockMvc.perform(get("/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("hello world")));
    }
}