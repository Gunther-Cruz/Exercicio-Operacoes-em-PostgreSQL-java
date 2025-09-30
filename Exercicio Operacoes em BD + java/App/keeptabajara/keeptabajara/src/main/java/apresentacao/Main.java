package apresentacao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;
import negocio.Anotacao;
import persistencia.AnotacaoDAO;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
        Scanner in = new Scanner(System.in);
        int escolha = 0;
        int id = 0;
        int keepID;
        String nome, texto, cor, imagem;
        AnotacaoDAO anotacaoDAO = new AnotacaoDAO();

        System.out.println();
        System.out.println("Bem vindo ao KeepTabajara");
        System.out.println();


        while (true) {
            System.out.println(" 1 == Visualizar Anotacao");
            System.out.println(" 2 == Adicionar Anotacao");
            System.out.println(" 3 == Atualizar Anotacao");
            System.out.println(" 4 == Deletar  Anotacao");
            System.out.println(" 5 == Copiar  Anotacao");
            System.out.println(" 6 == Sair do Keep");
            escolha = in.nextInt();


            if (escolha == 1) {
                System.out.println();
                System.out.println("SUAS ANOTACOES\n");
                anotacaoDAO.listar().forEach(p -> System.out.println(p));
                System.out.println();
            }


            if (escolha == 2) {
                in.nextLine();
                System.out.println("Titulo: ");
                nome = in.nextLine();
                System.out.println("Descricao: ");
                texto = in.nextLine();
                System.out.println("Selecione uma cor: ");
                cor = in.nextLine();
                System.out.println("Selecione uma imagem: \nExemplo = /img/imagem.png");
                imagem = in.nextLine();

                Anotacao novaAnotacao = anotacaoDAO.obter(id);
                novaAnotacao.setTitulo(nome);
                novaAnotacao.setTexto(texto);
                novaAnotacao.setCor(cor);
                novaAnotacao.setDataHora(LocalDateTime.now());
                novaAnotacao.setFoto(imagem);
                // /home/gunther/Downloads/TrabalhoIGOR/images/cachorro.jpg
                // /home/gunther/Downloads/TrabalhoIGOR/images/compra.jpeg
                // /home/gunther/Downloads/TrabalhoIGOR/images/estudo.jpg

                anotacaoDAO.inserir(novaAnotacao);
                id++;
                System.out.println("REGISTRADA! \nQuer fazer mais alguma coisa?");
            }


            if (escolha == 3) {
                System.out.println("Qual o Id da anotacao que voce deseja atualizar?");
                keepID = in.nextInt();
                Anotacao atualiza = anotacaoDAO.obter(keepID);
                in.nextLine();
                System.out.println("Insira um texto vazio, caso NAO queira modificar algum atributo");
                System.out.println("Titulo: ");
                nome = in.nextLine();
                System.out.println("Descricao: ");
                texto = in.nextLine();
                System.out.println("Selecione uma cor: ");
                cor = in.nextLine();
                System.out.println("Selecione uma imagem: \nExemplo = /img/imagem.png");
                imagem = in.nextLine();

                atualiza.setTitulo(nome);
                atualiza.setTexto(texto);
                atualiza.setCor(cor);
                atualiza.setDataHora(LocalDateTime.now());
                atualiza.setFoto(imagem);

                anotacaoDAO.atualizar(atualiza);
                System.out.println("ATUALIZADA! \nQuer fazer mais alguma coisa?");
            }


            if (escolha == 4) {
                System.out.println("Qual o Id da anotacao que voce deseja deletar?");
                keepID = in.nextInt();
                anotacaoDAO.deletar(keepID);
                System.out.println("DELETADA! \nQuer fazer mais alguma coisa?");
            }


            if (escolha == 5) {
                System.out.println("Qual o Id da anotacao que voce deseja copiar?");
                keepID = in.nextInt();
                Anotacao copia = anotacaoDAO.obter(keepID);
                Anotacao novaCopia = new Anotacao();

                novaCopia.setTitulo(copia.getTitulo());
                novaCopia.setTexto(copia.getTexto());
                novaCopia.setCor(copia.getCor());
                novaCopia.setFoto(copia.getFoto());
                novaCopia.setDataHora(LocalDateTime.now());


                anotacaoDAO.inserir(novaCopia);
                id++;
                System.out.println("COPIADA! \nQuer fazer mais alguma coisa?");
            }


            if (escolha == 6) {
                System.out.println("OBRIGADO.........PROFESSOR IGOR");
                break;
            } 

            
            else if (escolha >= 7) {
                System.out.println("Essa nao eh uma opcao valida, tente de novo.");
            }


        }
        in.close();

    }
}
