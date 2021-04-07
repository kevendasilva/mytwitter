public class PDException extends Exception {

  /**
   * Exceção - Perfil desativado.
   */
  private static final long serialVersionUID = 1L;

  public String getMessage(){
      return "O perfil está desativado!";
  }

}
