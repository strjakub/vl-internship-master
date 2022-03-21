package com.virtuslab.internship.api;

import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = {BasketEndpoint.class, ReceiptGenerator.class, ProductDb.class})
@AutoConfigureMockMvc
@EnableWebMvc
public class BasketEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShouldCheckStatusCode() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/receipts")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                "products" : [{"name" : "Milk", "type" : "DAIRY", "price" : 5},
                                        {"name" : "Milk", "type" : "DAIRY", "price" : 5},
                                        {"name" : "Steak", "type" : "MEAT", "price" : 30}]
                                }
                                """))
                .andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }
}

