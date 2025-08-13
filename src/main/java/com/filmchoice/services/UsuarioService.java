import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dto.UsuarioDTO;
import com.filmchoice.entities.Usuario;
import com.filmchoice.services.ServiceException;

public interface UsuarioService{
   Usuario cadastrarUsuario(UsuarioDTO usuarioEntrada) throws ServiceException;
   boolean verificarUsuarioCadastrado(UsuarioDTO usuarioEntrada) throws ServiceException;
}


