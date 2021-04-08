import java.util.Vector;

public class Repositorio implements IRepositorioUsuario {
  
  // Atributos da classe Repositorio

  private Vector<Perfil> repositorio;


  // Métodos da classe Repositorio
  
  public Repositorio(){
    repositorio = new Vector<Perfil>();
  }

  public void cadastrar(Perfil usuario) throws UJCException {
    // É necessário verificar se já existe algum perfil com o nome de usuário
    // igual ao perfil passado como parâmetro

    // O nome de usuario do perfil 'usuario' está na lista?
    if (!(buscar(usuario.getUsuario()) == null)) {
      // Sim
      // Exceção levantada
      throw new UJCException();
    } else {
      // Não
      // Então, o usuário é cadastrado no repositorio
      repositorio.add(usuario);
    }
  }

  public Perfil buscar(String usuario) {
    // Caso o usuario esteja no repositorio, o seu perfil deve ser retornado
    for (Perfil perfil: repositorio){
      if (perfil.getUsuario().equals(usuario)) return perfil;
    }
    // Caso o perfil não esteja no repositorio
    return null;
  }

  public void atualizar(Perfil usuario) throws UNCException {
    Perfil old = buscar(usuario.getUsuario());
    // O usuario pertence ao repositorio?
    if (old == null){
      // Não
      // Então, a exceção UNCException é levantada
      throw new UNCException();
    } else {
      // Sim
      // Atualize o usuario     
      repositorio.remove(old);
      repositorio.add(usuario);
    }
  }

}
