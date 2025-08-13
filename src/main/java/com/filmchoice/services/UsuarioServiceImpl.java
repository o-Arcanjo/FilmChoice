
import com.filmchoice.dao.PersistenciaDawException;
import com.filmchoice.dao.UsuarioDAO;
import com.filmchoice.dto.UsuarioDTO;
import com.filmchoice.entities.Usuario;
import com.filmchoice.mapper.impl.UsuarioMapper;
import com.filmchoice.services.ServiceException;

public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioDAO usuarioDAO, UsuarioMapper usuarioMapper) {
        this.usuarioDAO = usuarioDAO;
        this.usuarioMapper = usuarioMapper;
    }

    public boolean verificarUsuarioCadastrado(UsuarioDTO usuarioDTO) throws ServiceException{
        try {
            return usuarioDAO.buscarPorEmail(usuarioDTO.getEmail()).isPresent();
        } catch (PersistenciaDawException e) {
            throw new ServiceException("Erro ao verificar usuário cadastrado", e);
        }
    }

    public Usuario cadastrarUsuario(UsuarioDTO usuarioDTO) throws ServiceException{
        if (verificarUsuarioCadastrado(usuarioDTO)) {
            throw new ServiceException("Usuário já cadastrado com o e-mail: " + usuarioDTO.getEmail());
        }

        try {
            Usuario usuario = usuarioMapper.converterElementoEntidade(usuarioDTO);
            return usuarioDAO.save(usuario);
        } catch (PersistenciaDawException e) {
            throw new ServiceException("Erro ao cadastrar usuário", e);
        }
    }
}