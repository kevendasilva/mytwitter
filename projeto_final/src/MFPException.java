public class MFPException extends Exception {

  /**
   * Exceção - Mensagem fora do padrão, ou seja, que não segue o modelo de 1 a 140 caracteres.
   */
  private static final long serialVersionUID = 1L;
    
  public String getMessage(){
    return "A mensagem digitada está fora do padrão.";
  }
    
}
