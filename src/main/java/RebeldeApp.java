import com.rnv.letscode.InteligenciaCentral;

import java.util.Locale;
import java.util.Scanner;

public class RebeldeApp {

    public void showHeader() {
        System.out.println("::==================================::");
        System.out.println("::     BEM-VINDO A CAUSA REBELDE    ::");
        System.out.println("::==================================::");
        System.out.println("::         Escolha uma opção        ::");
        System.out.println("::                                  ::");
        System.out.println("::     [J] Juntar-se a causa        ::");
        System.out.println("::     [S] Sair                     ::");
        System.out.println("::                                  ::");
        System.out.println("::==================================::");
    }

    public void init() {
        while (true) {
            this.showHeader();
            System.out.print(":: Digite uma opção: ");
            String op = new Scanner(System.in).next();
            switch (op.toLowerCase()){
                case "j" :
                    //this.cadastrar();
                    new InteligenciaCentral().novoRebelde();
                    break;
                case "s" :
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente!");
            }
        }
    }

    public static void main(String args[]) {
        new RebeldeApp().init();
    }


}
