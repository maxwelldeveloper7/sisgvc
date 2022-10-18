package br.com.devmax.sisgvc.modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.*;
import java.util.*;
import java.sql.Date;
import javax.swing.JOptionPane;

/**
 * Autor: Maxwell de Oliveira Chaves
 */
public class Utilidades {

    private static Utilidades instanciaSingleton = null;

    private Utilidades() {
    }

    public static Utilidades getInstancia() {

        if (instanciaSingleton == null) {
            instanciaSingleton = new Utilidades();
        }
        return instanciaSingleton;
    }

    /*public static void main (String args[]){

     Utilidades util = Utilidades.getInstancia();
     //System.out.println(util.formataDataSQL("00/00/2009"));

     System.out.println(util.VenceuDataDeLocacao(4, 7, 2009));
     }*/
    public static Date formataDataSQL(String str) {
        Date dataSql = null;
        String result = getDigitos(str);

        /*
         if(result.length() < 8){
         JOptionPane.showMessageDialog(null, "Data inválida: "+ str);
         }*/
        if (!result.equals("")) {
            int ano = Integer.parseInt(result.substring(4, 8)) - 1900;
            int mes = Integer.parseInt(result.substring(2, 4)) - 1;
            int dia = Integer.parseInt(result.substring(0, 2));
            dataSql = new Date(ano, mes, dia);

        }
        return dataSql;
    }

    public static String formataDataSTR(Date dataSql) {
        DateFormat data = new SimpleDateFormat("dd/MM/yyyy");

        if (dataSql == null) {
            return "";
        } else {
            return data.format(dataSql);
        }

    }

    public static String formataTelefoneSQL(String str) {
        String result = getDigitos(str);

        if (result.equals("")) {
            result = "null";
        }

        return result;
    }

    public static String formataTelefoneSTR(String str) {
        String result = getDigitos(str);

        if (result.trim().equals("")) {
            result = "";
        }

        return result;
    }

    public static String formataCepSQL(String str) {
        String result = getDigitos(str);

        if (result.equals("")) {
            result = "null";
        }

        return result;
    }

    public static String formataCepSTR(String str) {
        String result = getDigitos(str);

        if (result.trim().equals("")) {
            result = "";
        }

        return result;
    }

    public static String formataHoraSQL(String str) {
        String result = getDigitos(str);
        if (!result.trim().equals("")) {
            result = result.substring(0, 2) + ":"
                    + result.substring(2, 4) + ":00";
        }
        return result;
    }

    public static String formataFlutuante(String str) {
        String result = "";

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '1') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '2') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '3') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '4') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '5') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '6') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '7') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '8') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '9') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == ',') {
                result = result + ".";
            }
        }

        if (result.equals("")) {
            result = "0";
        }

        return result;
    }

    public static BigDecimal formataMonetarioSQL(String str) {
        String result = "";

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '1') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '2') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '3') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '4') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '5') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '6') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '7') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '8') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == '9') {
                result = result + str.charAt(i);
            } else if (str.charAt(i) == ',') {
                result = result + ".";
            }
        }

        if (result.equals("")) {
            result = "0.00";
        }

        return new BigDecimal(result);
    }

    public static String formataMontetarioSTR(BigDecimal decimal) {
        DecimalFormat df = new DecimalFormat("0.00");
        if (decimal == null) {
            decimal = new BigDecimal("0.00");
        }
        return df.format(decimal);
    }

    public static String formataMoeda(String str) {
        String result;
        Locale local = new Locale("Pt", "Br");
        NumberFormat numForm;
        DecimalFormat decForm = null;

        if (str.equals("")) {
            str = "0.00";
        }

        result = str;
        numForm = NumberFormat.getCurrencyInstance(local);
        decForm = (DecimalFormat) numForm;
        return decForm.format(Double.parseDouble(result));
    }

    public static String formataHoje() {
        java.util.Date hoje = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(hoje);
    }

    public static String getDigitos(String str) {
        String result = "";

        if (str.equals("null")) {
            str = " ";
        }
        if (!str.equals("null")) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '1') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '2') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '3') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '4') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '5') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '6') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '7') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '8') {
                    result = result + str.charAt(i);
                } else if (str.charAt(i) == '9') {
                    result = result + str.charAt(i);
                }
            }
        }
        return result;
    }

    /**
     * Este método verifica vencimento da data de uso do sistema.
     *
     * @param dia (ex: 1 a 31)
     * @param mes (ex: 1 a 12)
     * @param ano (ex: 2009)
     * @return boolean
     */
    public static boolean VenceuDataDeLocacao(int dia, int mes, int ano) {

        Calendar dataAtual = Calendar.getInstance(); //instanciamos data atual
        dataAtual.getTime();
        Calendar dataLicenca = Calendar.getInstance(); //instanciamos data da licença

        dataLicenca.set(ano, mes - 1, dia + 1); //definimos a data de vencimento da licença

        if (dataAtual.before(dataLicenca)) {
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "A licença de uso do sistema"
                    + " expirou em " + dia + "/" + mes + "/" + ano + "." + "\n\n"
                    + "Adquira uma nova licença pelo suporte técnico:   "
                    + "  \n\n"
                    + "Telefone: 33 9104 5946 - 3621 1126\n"
                    + "E-mail: maxwellchaves@hotmail.com\n",
                    "Mensagem do Sistema", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }

    }

    public static String removeCaracteres(String s, char c) {

        String r = "";

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                break;
            }
            if (s.charAt(i) != c) {
                r += s.charAt(i);
            }
        }
        r.trim();
        return r;
    }

    public static void realizarBackupSQL() {
        Calendar data = Calendar.getInstance();
        data.setTime(new java.util.Date());
        SimpleDateFormat formatador = new SimpleDateFormat("ddMMyyyyHHmmss");
        String nomeArquivo;
        nomeArquivo = formatador.format(data.getTime());

        try {
            ProcessBuilder pb;
            Process p;
                        //C:/Program Files/PostgreSQL/9.1/bin/pg_dump.exe
                        //"C:\\Arquivos de programas\\PostgreSQL\\9.1\\bin\\pg_dump.exe "
            String path = "C:\\Arquivos de programas\\PostgreSQL\\9.1\\bin\\pg_dump.exe ";
            pb = new ProcessBuilder(getPath(), "-h", "localhost", "-p", "5432", "-U", "postgres", "-v", "-f", "C:\\SISGVC\\backups\\" + nomeArquivo + "BKP.sql", "SAC");
            pb.environment().put("PGPASSWORD", "311208");
            pb.redirectErrorStream(true);
            p = pb.start();

            JOptionPane.showMessageDialog(null, "A cópia de segurança da base de dados foi realizada com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    /*
     Restore de um banco local:

     psql -U usuario -d banco < banco.sql

     pg_restore -d banco banco.sql*/
    
    public static String getPath() throws FileNotFoundException, IOException{
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./configuracoes.properties");
        props.load(file); 
        String caminho;
        
        caminho = props.getProperty("path.backup");
        
        System.out.println(caminho);
        return caminho;
    }
    
    public static String getHost() throws FileNotFoundException, IOException{
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./configuracoes.properties");
        props.load(file);        
        String host;
        
        host = props.getProperty("host");
        
        System.out.println(host);
        return host;
    }
}
