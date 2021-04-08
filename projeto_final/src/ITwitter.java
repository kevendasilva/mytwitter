import java.util.Vector;

interface ITwitter {

  public void criarPerfil(Perfil usuario) throws PEException;

  public void cancelarPerfil(String usuario) throws PIException, PDException;

  public void tweetar(String usuario, String mensagem) throws PIException, PDException, MFPException;

  public Vector<Tweet> timeline(String usuario) throws PIException, PDException; 

  public Vector<Tweet> tweets(String usuario) throws PIException, PDException;

  public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException;

  public int numeroSeguidores(String usuario) throws PIException, PDException;

  public Vector<Perfil> seguidores(String usuario) throws PIException, PDException;

  public Vector<Perfil> seguidos(String usuario) throws PIException, PDException;

}