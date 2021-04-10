import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

      Menu menu = new Menu();
      Scanner leitor = new Scanner(System.in);

      // Opção
      int opcao = 0;

      // Controle do menu
      do {
        // Menu princiapal
        menu.init();
        try {
          opcao = menu.getOpcao(leitor);

          // Opções (menu principal)
          switch (opcao) {
            // Caso o usuario escolha entrar
            case 1:
              menu.entrar(leitor);
              break;
            // Caso o usuario queira sair
            case 2:
              break;
            // Caso o usuario deseje criar um novo perfil
            case 3:
              menu.cadastrar(leitor);
              break;
            default:
              menu.getErroMensagem("Opção inválida");
          }
        } catch (InputMismatchException e) {
          menu.getErroMensagem("Entrada inválida");;
        }
      } while (opcao != 2);

      leitor.close();
  }
}
