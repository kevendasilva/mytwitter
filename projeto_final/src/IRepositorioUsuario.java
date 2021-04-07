interface IRepositorioUsuario {

  public void cadastrar(Perfil usuario);

  public Perfil buscar(String usuario);

  public void atualizar(Perfil usuario);

}