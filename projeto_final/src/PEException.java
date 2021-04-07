public class PEException extends Exception {

  /**
   * Exceção - Perfil existente
   */
  private static final long serialVersionUID = 1L;

  public String getMessage(){
        return "O perfil já existe.";
  }

}
