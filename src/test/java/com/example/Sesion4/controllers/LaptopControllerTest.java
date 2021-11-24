package com.example.Sesion4.controllers;

import com.example.Sesion4.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void saludar() {
        ResponseEntity<String> response =
                testRestTemplate.getForEntity("/hola", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("hola mundo que hase", response.getBody());
    }

    @Test
    void findAll() {

        ResponseEntity<Laptop[]> response =
            testRestTemplate.getForEntity("/api/laptops", Laptop[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        List<Laptop> laptops = Arrays.asList(response.getBody());
        System.out.println(laptops.size());

    }

    @Test
    void findnOneById() {
        ResponseEntity<Laptop> response =
                testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        //assertEquals(200, response.getStatusCodeValue());
        //assertEquals("hola mundo que hase", response.getBody());
    }


    @Test
    void create() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "marca": "Juarez Test",
                    "modelo": "jaime Test",
                    "version": 4.4
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);

       ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);

       Laptop result = response.getBody();

       assertEquals(1l, result.getId());
       assertEquals("Juarez Test", result.getMarca());
    }
/*
    @Test
    void update() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "id": 1
                    "marca": "Jonezz Test",
                    "modelo": "jimy Test",
                    "version": 4.4
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops/", HttpMethod.PUT, request, Laptop.class);

        Laptop result = response.getBody();

        assertTrue(result != null);
        //assertTrue(result.getId() != null);
        assertEquals("Jonezz Test", result.getMarca());
        //assertEquals(4.4, result.getVersion());
        //assertEquals("Jonezz Test", result.getMarca());

    }
*/
    @Test
    void delete() {
        int idTest = 2;
        //CREAMOS EL OBJETO CABECERA
        HttpHeaders headers = new HttpHeaders();     //CREAMOS EL OBJETO CABECERA
        headers.setContentType(MediaType.APPLICATION_JSON);   // Indicamos que vamos a enviar un tipo de dato json
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));  // Indicamos que recibimos un dato json, antes lo convertimos a list para poder trabajar con el

        String json = null;

        HttpEntity<String> request = new HttpEntity<String>(json,headers);
        ResponseEntity<Laptop> response2 = testRestTemplate.exchange("/api/laptops"+idTest,HttpMethod.DELETE,request,Laptop.class);
        Laptop result2 = response2.getBody();
        assertEquals(null,result2);


    }

    @Test
    void deleteAll() {
        HttpHeaders headers = new HttpHeaders();     //CREAMOS EL OBJETO CABECERA
        headers.setContentType(MediaType.APPLICATION_JSON);   // Indicamos que vamos a enviar un tipo de dato json
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));  // Indicamos que recibimos un dato json, antes lo convertimos a list para poder trabajar con el

        String json = null;

        HttpEntity<String> request = new HttpEntity<String>(json,headers);  //Acceso y guardado del listado
    }
}