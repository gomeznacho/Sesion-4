package com.example.Sesion4.controllers;


import com.example.Sesion4.entities.Laptop;
import com.example.Sesion4.repositories.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


import java.util.List;
import java.util.Optional;



@RestController
public class LaptopController {

    private final Logger log = LoggerFactory.getLogger(LaptopController.class);


    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {

        this.laptopRepository = laptopRepository;
    }

    //encontrarlos todos (metodo GET)
    @GetMapping("/api/laptops")
    public List<Laptop> findAll(){

        return laptopRepository.findAll();
    }
    /**
     * http://localhost:8080/api/laptops/1
     * http://localhost:8080/api/laptops/2
     * Request
     * Response
     * @param id
     * @return
     */

    //encontrarlos por id
    @GetMapping("/api/laptops/{id}")
    @ApiOperation("Buscar un libro por clave primaria id Long")
    public ResponseEntity<Laptop> findnOneById(@ApiParam("Clave primaria tipo Long")@PathVariable Long id){
        Optional<Laptop> laptopOpt = laptopRepository.findById(id);

        if(laptopOpt.isPresent())
            return  ResponseEntity.ok(laptopOpt.get());
        else
            return ResponseEntity.notFound().build();
    }

    /**
     * Crear un nuevo libro en base de datos
     * Método POST, no colisiona con findAll porque son diferentes métodos HTTP: GET vs. POST
     * @param laptop
     * @param headers
     * @return
     */
    //añadir uno desde postman (método POST), se genera con un id
    @PostMapping("/api/laptops")
    public ResponseEntity <Laptop> create (@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers){
        if(laptop.getId() != null){  //compruebo que no tiene Id porque lo estoy creando
            log.warn("cuidadito porque le sobra el id");
            return ResponseEntity.badRequest().build();
        }

        Laptop result = laptopRepository.save(laptop); //"instancio" el laptop en la base de datos
        return ResponseEntity.ok(result);           //devuelvo la respuesta en función de ese laptop
    }
    @PutMapping("/api/laptops")
    public ResponseEntity <Laptop> update(@RequestBody Laptop laptop){
        if(laptop.getId() == null){ //tiene que tener Id porque es actualizar
            log.warn("cuidadito porque le falta el id");
            return  ResponseEntity.badRequest().build();
        }
        if(!laptopRepository.existsById(laptop.getId())){
            log.warn("cuidadito porque no existe este libro");
            return  ResponseEntity.notFound().build();
        }

        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);

    }
    @ApiIgnore //ignorar para que no aparezca en a documenacion de la api Swagger
    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){

        if(!laptopRepository.existsById(id)){
            log.warn("cuidadito porque no existe este libro o ya lo has borrado");
            return  ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
    @ApiIgnore
    @DeleteMapping("/api/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        log.info("borrando todo");
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
