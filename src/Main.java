import model.Pedido;
import model.Produto;
import service.PedidoService;
import service.ProdutoService;

public class Main {

    public static void main(String[] args) {

        // Inicialização dos services
        ProdutoService produtoService = new ProdutoService();
        PedidoService pedidoService =
                new PedidoService(produtoService.getRepository());

        // Cadastro de produtos
        Produto tv = new Produto(1, "TV", 3000, 10);
        Produto arroz = new Produto(2, "Arroz", 30, 20);
        Produto notebook = new Produto(3, "Notebook", 5000, 5);
        Produto mouse = new Produto(4, "Mouse", 80, 0);

        produtoService.adicionarProduto(tv);
        produtoService.adicionarProduto(arroz);
        produtoService.adicionarProduto(notebook);
        produtoService.adicionarProduto(mouse);

        // Listagem de produtos
        System.out.println("\n================ PRODUTOS ================\n");
        produtoService.listarProdutos();

        // Testando Streams
        System.out.println("\n================ PRODUTOS CAROS ================\n");
        System.out.println(produtoService.listarProdutosCaros());

        System.out.println("\n================ PRODUTOS SEM ESTOQUE ================\n");
        System.out.println(produtoService.listarProdutosSemEstoque());

        System.out.println("\n================ NOMES DOS PRODUTOS ================\n");
        System.out.println(produtoService.listarNomesProdutos());

        // Criando pedido
        Pedido pedido1 = new Pedido(1, "João", 0);

        pedido1.adicionarProduto(
                new Produto(1, "TV", 3000, 3)
        );

        pedido1.adicionarProduto(
                new Produto(2, "Arroz", 30, 5)
        );

        // Realizando pedido
        pedidoService.realizarPedido(pedido1);

        // Exibindo pedido
        System.out.println("\n================ PEDIDO REALIZADO ================\n");
        System.out.println(pedido1);

        // Stream de pedidos
        System.out.println("\n================ PEDIDOS ACIMA DE R$1000 ================\n");
        System.out.println(
                pedidoService.listarPedidosAcimaDeValor(1000)
        );

        // Estoque após pedido
        System.out.println("\n================ ESTOQUE ATUALIZADO ================\n");
        produtoService.listarProdutos();

        // Testando exception de produto duplicado
        System.out.println("\n================ TESTE PRODUTO DUPLICADO ================\n");

        try {

            produtoService.adicionarProduto(
                    new Produto(1, "TV2", 1000, 5)
            );

        } catch (Exception e) {

            System.out.println("Erro: " + e.getMessage());
        }

        // Testando exception de estoque insuficiente
        System.out.println("\n================ TESTE ESTOQUE INSUFICIENTE ================\n");

        Pedido pedidoErro = new Pedido(2, "Maria", 0);

        pedidoErro.adicionarProduto(
                new Produto(1, "TV", 3000, 100)
        );

        try {

            pedidoService.realizarPedido(pedidoErro);

        } catch (Exception e) {

            System.out.println("Erro: " + e.getMessage());
        }
    }
}