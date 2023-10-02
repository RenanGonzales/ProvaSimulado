package techshop.simulado.service;

import org.springframework.stereotype.Service;
import techshop.simulado.entity.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private List<Produto> produtos;

    public ProdutoService(){
        produtos = new ArrayList<>();
    }

    public Produto criarProduto(Produto produto) {
        produtos.add(produto);
        return produto;
    }

    public List<Produto> buscarProdutos(){
        return produtos;
    }

    public Produto buscarProduto(Long codProduto) throws Exception{
        Optional<Produto> produto = produtos.stream().filter(prod -> prod.getCodProduto() == codProduto).findFirst();
        if (produto.isPresent()){
            return produto.get();
        }else{
            throw new Exception("Produto nao encontrado");
        }
    }

    public void excluirProduto(Long codProduto) throws Exception{
        Optional<Produto> produtoOptional = produtos.stream().filter(prod -> prod.getCodProduto() == (codProduto)).findFirst();
        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            produtos.remove(produto);
        }else{
            throw new Exception("Produto não encontrado");
        }
    }
}
