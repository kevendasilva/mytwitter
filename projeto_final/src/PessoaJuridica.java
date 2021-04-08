public class PessoaJuridica extends Perfil {

  // Atributos da classe PessoaJuridica
  
  private long cnpj;

  
  // Métodos da classe PessoaJuridica

  public PessoaJuridica(String usuario) {
    super(usuario);
  }

  public void setCnpj(long cnpj){
    this.cnpj = cnpj;
  }

  public long getCnpj(){
    return cnpj;
  }
    
}
