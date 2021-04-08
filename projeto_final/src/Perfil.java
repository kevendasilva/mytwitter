import java.util.Vector;

abstract class Perfil {

  // Atributos da classe Perfil

  private String usuario;
  private Vector<Perfil> seguidos;
  private Vector<Perfil> seguidores;
  private Vector<Tweet> timeline; 
  private Boolean ativo;


  // Métodos da classe Perfil

  public Perfil(String usuario){
    this.usuario = usuario;
    seguidos = new Vector<Perfil>();
    seguidores = new Vector<Perfil>();
    timeline = new Vector<Tweet>();
    ativo = true;
  }

  public void addSeguido(Perfil usuario){
    seguidos.add(usuario);
  }

  public void addSeguidor(Perfil usuario){
    seguidores.add(usuario);
  }

  public void addTweet(Tweet tweet){
    timeline.add(tweet);
  }

  public void setUsuario(String usuario){
    this.usuario = usuario;
  }

  public String getUsuario(){
    return usuario;
  }

  public Vector<Perfil> getSeguidos(){
    return seguidos;
  }

  public Vector<Perfil> getSeguidores(){
    return seguidores;
  }

  public Vector<Tweet> getTimeline(){
    return timeline;
  }
  
  public void setAtivo(Boolean valor){
    ativo = valor;
  }

  public Boolean isAtivo(){
    return ativo;
  }
  
}
