import java.util.Scanner;
import java.util.Vector;

class Menu {

  // Atributos da classe

  // Repositorio
  private Repositorio repositorio = new Repositorio();

  // MyTwitter
  private MyTwitter myTwitter = new MyTwitter(repositorio);

  // Menus

  private String[] menuPrincipal = {" _____________________________",
                                    "|                             |",
                                    "|          MyTwitter          |",
                                    "|                             |",
                                    "| 1. Entrar                   |",
                                    "| 2. Sair                     |",
                                    "|                             |",
                                    "|        3. Cadastrar         |",
                                    "|_____________________________|\n"};

  private String[] menuPerfil = {" _____________________________ ",
                                 "|                             |",
                                 "|  Escolha o tipo da conta:   |",
                                 "|                             |",
                                 "|  1. Pessoa Fisica           |",
                                 "|  2. Pessoa Juridica         |",
                                 "|_____________________________|\n"};

  private String[] menuInterno =  {" _____________________________",
                                   "|                             |",
                                   "|            Menu             |",
                                   "|                             |",
                                   "| 1. Tweetar                  |",
                                   "| 2. Seguir                   |",
                                   "| 3. Timeline                 |",
                                   "| 4. Meus tweets              |",
                                   "| 5. Número de seguidores     |",
                                   "| 6. Seguidos                 |",
                                   "| 7. Seguidores               |",
                                   "| 8. Cancelar Perfil          |",
                                   "|                             |",
                                   "|          0. Sair            |",
                                   "|_____________________________|\n"};

  private String usuario;


  // Métodos da classe

  public void init() {
    apresenteMenu(menuPrincipal);
  }

  private void apresenteMenu(String[] menu) {
    for (String line: menu) {
      System.out.println(line);
    }
  }

  public int getOpcao(Scanner leitor) {
    System.out.print("Sua opção: ");
    int opcao = leitor.nextInt();
    leitor.nextLine();
    return opcao;
  }

  public void entrar(Scanner leitor){
    // Pegando a timeline do usuário

    Vector<Tweet> timeline = null;

    System.out.print("\nDigite o seu nome de usuario: ");

    usuario = leitor.nextLine();

    // Carregando a timeline do perfil
    try {
      timeline = myTwitter.timeline(usuario);
    } catch (PIException e) {
      timeline = null;
      getErroMensagem(e.getMessage());
    } catch (PDException e) {
      timeline = null;
      getErroMensagem(e.getMessage());
    } finally {
      if (timeline != null) entre(leitor, timeline);
    }
  }
      
  public void cadastrar(Scanner leitor){

    String mensagem = null;

    // Caso uma pessoa tente criar um perfil
    System.out.print("\n \nEscolha um nome de usuario (sem @): ");

    String usuarioCadastro = leitor.nextLine();

    apresenteMenu(menuPerfil);

    int tipo = getOpcao(leitor);

    // Menu criar contar
    switch (tipo) {
      // Pessoa fisisca
      case 1:
        PessoaFisica perfilPF = new PessoaFisica(usuarioCadastro);

        System.out.print("\n \nDigite o seu CPF: ");

        long cpf = leitor.nextLong();

        perfilPF.setCpf(cpf);

        // Realizando o cadastro
        try {
          myTwitter.criarPerfil(perfilPF);
          mensagem = "\nPerfil cadastrado com sucesso!\n";
        } catch (PEException e) {
          mensagem = e.getMessage();
        } finally {
          System.out.println(mensagem);
        }

        break;

      // Pessoa juridica
      case 2:
        PessoaJuridica perfilPJ = new PessoaJuridica(usuarioCadastro);

        System.out.print("\n \nDigite o seu CNPJ: ");

        long cnpj = leitor.nextLong();

        perfilPJ.setCnpj(cnpj);

        // Realizando o cadastro
        try {
          myTwitter.criarPerfil(perfilPJ);
          mensagem = "\nPerfil cadastrado com sucesso!\n";
        } catch (PEException e) {
          mensagem = e.getMessage();
        } finally {
          System.out.println("\n" + mensagem);
        }

        break;

      // Opção inválida
      default:
        getErroMensagem("Opção inválida");
        break;
    }
  }
  
  private void entre(Scanner leitor, Vector<Tweet> timeline) {
    int opcao;

    String mensagem = null;

    do {
      apresenteMenu(menuInterno);
      opcao = getOpcao(leitor);
      
      switch (opcao) {
        // Sair
        case 0:
          break;

        // Tweetar
        case 1:

          System.out.print("Escreva o seu tweet (1 a 140 caracteres): ");
          
          mensagem = "\nTweet criado com sucesso.\n";

          String mensagemTweet = leitor.nextLine();

          try {
            myTwitter.tweetar(usuario, mensagemTweet);
          } catch (MFPException e) {
            mensagem = "\n" + e.getMessage() + "\n";
          } catch (PIException e) {
          } catch (PDException e) {
          } finally {
            System.out.println(mensagem);
          }
          break;

        // Seguir
        case 2:
          System.out.print("Perfil a ser seguido: ");

          mensagem = "\nUsuário seguido!\n";

          String seguido = leitor.nextLine();

          try {
            myTwitter.seguir(usuario, seguido);
          } catch (PIException e) {
            mensagem = "\n" + e.getMessage() + "\n";
          } catch (PDException e) {
            mensagem = "\n" + e.getMessage() + "\n";
          } catch (SIException e) {
            mensagem = "\n" + e.getMessage() + "\n";
          } finally {
            System.out.println(mensagem);
          }
          break;

        // Timeline
        case 3:

          // Apresentando a timeline do usuario
          try {
            System.out.println(" _____________________________");
            System.out.println("|                             |");
            System.out.println("|          Timeline           |");
            System.out.println("|                             |");    

            apresenteTweets(myTwitter.timeline(usuario));

            System.out.println("|                             |");
            System.out.println("|_____________________________|");

          } 
          catch (PIException e) {} 
          catch (PDException e) {}
          break;
        
        // Meus tweets
        case 4:

          // Apresentando os tweets do usuário
          try {

            System.out.println(" _____________________________");
            System.out.println("|                             |");
            System.out.println("|            Meu              |");
            System.out.println("|           Tweets            |");    
            System.out.println("|                             |");

            apresenteTweets(myTwitter.tweets(usuario));

            System.out.println("|                             |");
            System.out.println("|_____________________________|");
          }
          catch (PIException e) {} 
          catch (PDException e) {}
          break;

        // Número de seguidores
        case 5:

          // Número de seguidores do usuário
          try {
            System.out.println("\n Você possui: " + myTwitter.numeroSeguidores(usuario) + " seguidores.");
          }
          catch (PIException e) {} 
          catch (PDException e) {}
          break;

        // Seguidos
        case 6:

          // Apresentando a lista de seguidos do perfil
          try {
            System.out.println(" _____________________________");
            System.out.println("|                             |");
            System.out.println("|           Perfis            |");
            System.out.println("|          Seguidos           |");
            System.out.println("|                             |");      
                    
            listePerfis(myTwitter.seguidos(usuario));
                
            System.out.println("|                             |");
            System.out.println("|_____________________________|");
          
          
          }
          catch (PIException e) {} 
          catch (PDException e) {}
          break;
          
        // Seguidores
        case 7:
        
          // Apresentando a lista de seguidores do perfil
          try {
            System.out.println(" _____________________________");
            System.out.println("|                             |");
            System.out.println("|           Perfis            |");
            System.out.println("|         Seguidores          |");    
            System.out.println("|                             |");

            listePerfis(myTwitter.seguidores(usuario));

            System.out.println("|                             |");
            System.out.println("|_____________________________|");

          }
          catch (PIException e) {} 
          catch (PDException e) {}
          break;
        
        // Cancelar Perfil
        case 8:
          
          // Tentando cancelar o perfil
          try {
            opcao = 0; // Saindo do 'sistema'
            myTwitter.cancelarPerfil(usuario);
          } 
          catch (PIException e) {} 
          catch (PDException e) {}

          break;

        //Opção inválida
        default:
          getErroMensagem("Opção inválida");
          break;
      }

    } while (opcao != 0);
  }

  // Apresentando os tweets do perfil (timeline ou tweets do perfil)
  private void apresenteTweets(Vector<Tweet> tweets) {

    int m = 0, i = 0;

    for (Tweet tweet: tweets) {
      
      m = 0; 
      i = 0;

      while (m != tweet.getMensagem().length()) {
        System.out.print("  ");
        m += 27;
        if (m > tweet.getMensagem().length()) m = tweet.getMensagem().length();

        for (int j = i ; j < m ; j++) {
          System.out.print(tweet.getMensagem().charAt(j));
        }

        System.out.print("\n");

        i = m - 1;
      }

      System.out.println("\n  @" + tweet.getUsuario() + "\n \n");
    }

  }

  // Listando perfis
  private void listePerfis(Vector<Perfil> perfis) {
    String line;

    for (Perfil perfil: perfis) {
      line = "| @" + perfil.getUsuario();
      for (int i = 0 ; i < (27 - perfil.getUsuario().length()) ; i++){
        line += " ";
      }
      line += "|";
      System.out.println(line);
    }

  }

  // Mensagem de erro do menu
  public void getErroMensagem(String mensagem){
    String erroMensagem = "\n(!) " + mensagem + " (!)\n";
    System.out.println(erroMensagem);
  }

}
