public class UJCException extends Exception {

    /**
     * Exceção - Usuário já cadastrado.
     */
    private static final long serialVersionUID = 1L;

    public String getMessage(){
        return "Usuário já cadastrado!";
    }

}
