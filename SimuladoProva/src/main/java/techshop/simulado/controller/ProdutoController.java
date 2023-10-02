package techshop.simulado.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techshop.simulado.entity.Produto;
import techshop.simulado.service.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    ProdutoService produtoService;
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> criarProduto(@RequestBody Produto produto){
        try {
            produto = produtoService.criarProduto(produto);
            return new ResponseEntity<>("Produto criado com sucesso",HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>("Erro na criacao do protudo!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("todos")
    public ResponseEntity<List<Produto>> buscarProdutos(){
        try {
            List<Produto> lista = produtoService.buscarProdutos();
            return new ResponseEntity(lista, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity("Erro na requisicao", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{codProduto}")
    public ResponseEntity<?> buscarProduto(@PathVariable("codProduto") Long codProduto) {
        try {
            Produto produto = produtoService.buscarProduto(codProduto);
            return new ResponseEntity(produto, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
