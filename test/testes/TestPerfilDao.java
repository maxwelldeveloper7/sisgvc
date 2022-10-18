/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import br.com.devmax.sisgvc.daos.PerfilDAO;
import br.com.devmax.sisgvc.modelo.PerfilBean;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maxwell
 */
public class TestPerfilDao {

    private void imprimirLista(List<PerfilBean> rotinas) {
        for (int i = 0; i < rotinas.size(); i++) {
            System.out.print(rotinas.get(i).getId() + " - ");
            System.out.println(rotinas.get(i).getNoPerfil());
            System.out.println(rotinas.get(i).getDsPerfil());
            System.out.println("----------------------------------------------");
        }
    }

    public static void main(String args[]) {
        TestPerfilDao teste = new TestPerfilDao();
        PerfilBean perfil = new PerfilBean();
        List<PerfilBean> perfis = new ArrayList();
        PerfilDAO dao = new PerfilDAO();

        System.out.println("testando insert");
        perfil.setNoPerfil("perfil de teste");
        perfil.setDsPerfil("Descrição do perfil");
        dao.inserir(perfil);
        perfis = dao.listar();
        teste.imprimirLista(perfis);

        System.out.println("testando update");
        perfil.setNoPerfil("perfil alterado");
        perfil.setDsPerfil("Descrição alterada");
        perfil.setId(5);
        dao.alterar(perfil);
        perfis = dao.listar();
        teste.imprimirLista(perfis);

        System.out.println("perfil deletado");
        perfil.setId(5);
        dao.excluir(perfil);
        perfis = dao.listar();
        teste.imprimirLista(perfis);

        System.out.println("teste getId");
        perfil = null;
        perfil = dao.getPerfil(1);
        System.out.println(perfil.getNoPerfil());

    }
}
