/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Maxwell
 */
public class BackupPostgres {

    public static void main(String args[]) {
        BackupPostgres b = new BackupPostgres();
        b.realizarBackupSQL();
    }

    private void realizarBackupSQL() {
        Calendar data = Calendar.getInstance();
        data.setTime(new java.util.Date());
        SimpleDateFormat formatador = new SimpleDateFormat("ddMMyyyyHHmmss");
        String nomeArquivo;
        nomeArquivo = formatador.format(data.getTime());

        try {
            ProcessBuilder pb;
            Process p;
            pb = new ProcessBuilder("C:/Program Files/PostgreSQL/9.1/bin/pg_dump.exe ", "-h", "localhost", "-p", "5432", "-U", "postgres", "-v", "-f", "C:\\SISGVC\\backups\\"+nomeArquivo+"BKP.sql", "SAC");
            pb.environment().put("PGPASSWORD", "311208");
            pb.redirectErrorStream(true);
            p = pb.start();
            JOptionPane.showMessageDialog(null, "Cópia de segurança da base de dados realizada com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private void realizaBackupBin() {
        try {
            ProcessBuilder pb;
            Process p;
            pb = new ProcessBuilder("C:/Program Files/PostgreSQL/9.1/bin/pg_dump.exe ", "-h", "localhost", "-p", "5432", "-U ","postgres","--no-password", "-F", "c", "-b", "-v", "-f", "C:\\Users\\Maxwell\\Desktop\\TesteBKP.backup", "SAC");
            //pb.environment().put("PGPASSWORD", "311208");
            pb.redirectErrorStream(true);
            p = pb.start();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
