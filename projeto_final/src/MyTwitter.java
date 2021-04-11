import java.util.Vector;


public class MyTwitter implements ITwitter{

  // Atributos da classe MyTwitter

  private Repositorio repositorio;


  // Métodos da classe MyTwitter

  public MyTwitter(Repositorio repositorio){
    this.repositorio = repositorio;
  }
  
  private Boolean isAtivoAndExiste(Perfil usuario) throws PIException {
    Perfil perfil = null;

    if (usuario != null) perfil = repositorio.buscar(usuario.getUsuario());

    // O perfil existe?
    if (perfil == null) {
      // Não
      throw new PIException();
    } else {
      // Sim
    
      // Está ativo?
      if (perfil.isAtivo()) {
        // Sim
        return true;
      } else {
        // Não
        return false;
      }
    }
  }

  public void criarPerfil(Perfil usuario) throws PEException {
    // Não é permitido cadastrar um perfil com o mesmo usuario
    try {
      // Tentando cadastrar
      repositorio.cadastrar(usuario);
    } catch (UJCException e) {
      // Caso o usuario já tenha sido cadastrado
      // A exceção PEException é levantada
      throw new PEException();
    }
  }

  public void cancelarPerfil(String usuario) throws PIException, PDException {
    Perfil perfil = repositorio.buscar(usuario);
    // O perfil deve existir e estar ativo

    if (isAtivoAndExiste(perfil)) {
      // Sim

      try {
        // Ativo recebe falso
        perfil.setAtivo(false);

        // Atualizando o repositório
        repositorio.atualizar(perfil);
      } catch (UNCException e) {
        // Caso o usuário não esteja cadastrado no repositorio
        // A exceção PIException será levantada. Não sendo necessário tratar a 
        // exceção UNCException.
      }
    } else {
      // Não
      throw new PDException();
    }
  }

  public void tweetar(String usuario, String mensagem) throws PIException, PDException, MFPException {
    Perfil perfil = repositorio.buscar(usuario);
    // O perfil deve existir e estar ativo

    if (isAtivoAndExiste(perfil)) {
      // Sim
      
      // A mensagem possui entre 1 e 140 caracteres?
      if (141 > mensagem.length() && mensagem.length() > 0) {
        // Sim

        // Criando o tweet
        Tweet tweet = new Tweet();
        // Adicionando a mensagem
        tweet.setMensagem(mensagem);
        tweet.setUsuario(usuario);

        // Adicionando o tweet ao perfil
        perfil.addTweet(tweet);

        // Atualizando a timeline dos seguidores do perfil
        for (Perfil seguidor: perfil.getSeguidores()) {
          if (seguidor.isAtivo()){
            seguidor.addTweet(tweet);
             // Atualizando o seguidor
            try {
              repositorio.atualizar(seguidor);
            } catch (UNCException e) {}
          }

        }

        // Atualizando o perfil do repositório
        try {
          repositorio.atualizar(perfil);
        } catch (UNCException e) {}
      } else {
        // Não
        throw new MFPException();
      }
    } else {
      // Não
      throw new PDException(); 
    }
  }

  public Vector<Tweet> timeline(String usuario) throws PIException, PDException {
    Perfil perfil = repositorio.buscar(usuario);
    // O perfil deve existir e estar ativo

    if (isAtivoAndExiste(perfil)) {
      // Sim

      // Retorne a timeline do usuario
      return perfil.getTimeline();
    } else {
      // Não
      throw new PDException();
    }
  }

  public Vector<Tweet> tweets(String usuario) throws PIException, PDException {
    Perfil perfil = repositorio.buscar(usuario);
    // O perfil deve existir e estar ativo

    if(isAtivoAndExiste(perfil)) {
      // Sim

      // Tweets
      Vector<Tweet> tweets = perfil.getTimeline();
      Vector<Tweet> tweetsN = new Vector<Tweet>();

      // Percorrendo todos os tweets da timeline 
      // e removendo aqueles que não pertecem ao usuario
      for (Tweet tweet: perfil.getTimeline()) {
        if (!tweet.getUsuario().equals(perfil.getUsuario())) tweetsN.add(tweet);
      }

      for (Tweet tweet: tweetsN) {
        tweets.remove(tweet);
      }

      // Retorne os Tweets do usuario
      return tweets;
    } else {
      // Não
      throw new PDException();
    }
  }

  public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException {
    Perfil perfilSeguidor = repositorio.buscar(seguidor);
    Perfil perfilSeguido = repositorio.buscar(seguido);

    // O perfilSeguidor e/ou perfilSeguido existem e estão ativos?
    if (isAtivoAndExiste(perfilSeguidor) && isAtivoAndExiste(perfilSeguido)) {
      // Sim

      // São iguais?
      if (perfilSeguido.getUsuario().equals(perfilSeguidor.getUsuario())) {
        // Sim
        throw new SIException();
      } else {
        // Não
        
        // Adicionar os perfis em suas respectivas listas
        perfilSeguidor.addSeguido(perfilSeguido);
        perfilSeguido.addSeguidor(perfilSeguidor);

        // Atualizando o repositorio
        try {
          repositorio.atualizar(perfilSeguido);
          repositorio.atualizar(perfilSeguidor);
        } catch (UNCException e) {}
      }
    } else {
      // Não
      throw new PDException();
    }
  }

  public int numeroSeguidores(String usuario) throws PIException, PDException {
    Perfil perfil = repositorio.buscar(usuario);
    // O perfil deve existir e estar ativo

    // Número de seguidores
    int numSeguidores = 0;

    if (isAtivoAndExiste(perfil)) {
      // Sim

      Perfil seguidorRep;

      // Verificando quais são os seguidores que estão ativos
      for (Perfil seguidor: perfil.getSeguidores()) {
        seguidorRep = repositorio.buscar(seguidor.getUsuario());
        if (seguidorRep.isAtivo()) numSeguidores++;  
      }

      // Número de seguidores
      return numSeguidores;
    } else {
      // Não
      throw new PDException();
    }
  }

  public Vector<Perfil> seguidores(String usuario) throws PIException, PDException {
    Perfil perfil = repositorio.buscar(usuario);
    // O perfil deve existir e estar ativo

    // O perfil existe?
    if (isAtivoAndExiste(perfil)) {
      // Sim

      // Seguidores
      Vector<Perfil> seguidores = perfil.getSeguidores();
      Vector<Perfil> seguidoresD = new Vector<Perfil>();
      Perfil seguidorRep;

      // Perfis que estão desativados
      for (Perfil seguidor: perfil.getSeguidores()) {
        seguidorRep = repositorio.buscar(seguidor.getUsuario());
        if (!seguidorRep.isAtivo()) seguidoresD.add(seguidor);
      }

      // Removendo perfis desativados
      for (Perfil seguidorD: seguidoresD) {
        seguidores.remove(seguidorD);
      }
      
      return seguidores;
    } else {
      // Não
      throw new PDException();
    }
  }


  public Vector<Perfil> seguidos(String usuario) throws PIException, PDException{
    Perfil perfil = repositorio.buscar(usuario);
    // O perfil deve existir e estar ativo

    // O perfil existe e está ativo?
    if (isAtivoAndExiste(perfil)) {
      // Sim

      // Seguidos
      Vector<Perfil> seguidos = perfil.getSeguidos();
      Vector<Perfil> seguidosD = new Vector<Perfil>();
      Perfil seguidoRep;

      // Perfis que estão desativados
      for (Perfil seguido: perfil.getSeguidos()) {
        seguidoRep = repositorio.buscar(seguido.getUsuario());
        if (!seguidoRep.isAtivo()) seguidosD.add(seguidoRep);
      }

      // Removendo perfis desativados
      for (Perfil seguidoD: seguidosD) {
        seguidos.remove(seguidoD);
      }

      return seguidos;
    } else {
      // Não
      throw new PDException();
    }
  }
}
