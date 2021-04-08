public class PessoaFisica extends Perfil {

  // Atributos da classe PessoaFisica

  private long cpf;


  // Métodos da classe PessoaFisica

  public PessoaFisica(String usuario) {
    super(usuario);
  }

  public void setCpf(long cpf){
    this.cpf = cpf;
  }

  public long getCpf(){
    return cpf;
  }
    
}
