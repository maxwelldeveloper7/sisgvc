/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import br.com.devmax.sisgvc.daos.RotinaDAO;
import br.com.devmax.sisgvc.modelo.RotinaBean;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maxwell
 */
public class TestRotinaDao {

    private void imprimirLista(List<RotinaBean> rotinas) {
        for (int i = 0; i < rotinas.size(); i++) {
            System.out.print(rotinas.get(i).getId() + " - ");
            System.out.println(rotinas.get(i).getNomeRotina());
        }
    }

    public static void main(String args[]) {
        TestRotinaDao teste = new TestRotinaDao();
        RotinaBean rotina = new RotinaBean();
        List<RotinaBean> rotinas = new ArrayList();
        RotinaDAO dao = new RotinaDAO();

        System.out.println("testando insert");
        rotina.setNomeRotina("rotina de teste");
        dao.inserir(rotina);
        rotinas = dao.listar();
        teste.imprimirLista(rotinas);

        System.out.println("testando update");
        rotina.setNomeRotina("rotina alterada");
        rotina.setId(2);
        dao.alterar(rotina);
        rotinas = dao.listar();
        teste.imprimirLista(rotinas);

        System.out.println("testando delete");
        rotina.setId(2);
        dao.excluir(rotina);
        rotinas = dao.listar();
        teste.imprimirLista(rotinas);

    }
}
