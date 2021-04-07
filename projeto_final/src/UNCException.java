public class UNCException extends Exception {

  /**
   * Exceção - Usuário não cadastrado.
   */
  private static final long serialVersionUID = 1L;

  public String getMessage(){
    return "Usuário não cadastrado!";
  }

}
