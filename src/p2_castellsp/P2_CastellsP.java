package p2_castellsp;

/**
 * @author gerardcastells
 */

import java.sql.PreparedStatement;
import java.util.Scanner;

public class P2_CastellsP {

    //DECLAREM TOTES LES CONSTANTS DEL CODI
    private static final String[][] catSubcat = {
            {
                    "Educació i formació",
                    "Oci i temps lliure",
                    "Novel·la",
                    "Salut i benestar"
            },
            {
                    "Ciència i enginyeria",
                    "Biografies i memòries",
                    "Finances"
            },
            {
                    "Guies de viatge",
                    "Cuina",
                    "Esports"
            },
            {
                    "Adolescents",
                    "Ciència ficció i fantasia",
                    "Comèdia i humor"
            },
            {
                    "Nutrició",
                    "Medicina General",
                    "Fisioteràpia"
            }
    };

    private static final int INDEX_CODI = 0;
    private static final int INDEX_CATEGORIA = 1;
    private static final int INDEX_SUBCATEGORIA = 2;
    private static final int INDEX_PREU = 3;
    private static final int INDEX_UNITATS = 4;

    private static final int INDEX_CATEGORIES_TITOLS = 0;

    private static final double IVA = 0.04;
    private static final int INTENTS_MAX = 3;

    public static void main(String[] args) {


        //FEM LA CREACIÓ DEL NOSTRE SCANNER PER LLEGIR DADES
        Scanner scanner = new Scanner(System.in);

        int[][] dadesLlibres = new int[5][10]; //DEFINIM L'ARRAY BIDIMENSIONAL

        int[] numLlibresPerCategoria = new int[4];

        int indexLlibreActual = 0; //AQUESTA VARIABLE ENS SERVEIX PER RECORRER ELS ARRAYS DELS LLIBRES

        //DEFINIM DIVERSES VARIABLES
        int codiLlibre = 0, numCategoria = 0, numSubcategoria = 0, preu = 0, unitatsVenudes = 0, respostaRegistre;

        //AQUESTES VARIABLES ENS SERVIRAN PER DESPRÉS MOSTRAR EL RESUM ESTADÍSTIC DE LES DADES
        int llibresCat_0 = 0, llibresCat_1 = 0, llibresCat_2 = 0, llibresCat_3 = 0;
        double guanysTotals = 0;

        String categoriaSeleccionada = "", subcategoriaSeleccionada = "";

        //DEFINIM ELS VALORS BOOL QUE ENS SERVIRAN PER ENTRAR I SORTIR DELS BUCLES I VALIDAR DADES
        boolean respostaValida = false;
        boolean calIntroduir = true;

        //BUCLE PRINCIPAL QUE S'EXECUTA MENTES ES VULGUIN INTRODUIR DADES DE LLIBRES
        while (calIntroduir) {

            boolean intentsEsgotats = false;
            int intents = 0;

            //BUCLE DE VALIDACIÓ DE DADES PELS CODIS DELS LLIBRES
            System.out.println("Codi llibre?: ");
            while (!respostaValida && !intentsEsgotats) {

                codiLlibre = scanner.nextInt();

                if (codiLlibre < 100 || codiLlibre > 999) {
                    System.out.println("Error en el codi del llibre, no compleix paràmetres. Introdueix de nou: ");
                    intents++;
                    if (intents == INTENTS_MAX) {
                        intentsEsgotats = true;

                    }
                } else {
                    respostaValida = true;
                    dadesLlibres[INDEX_CODI][indexLlibreActual] = codiLlibre; //GUARDEM EL CODI DEL LLIBRE AL NOSTRE DICCIONARI
                }

            }
            //AQUESTES DUES LINIES DE CODI (89-90) SERVEIXEN PER FER RESET DE LES POSSIBLES DADES EN EL BUCLE I PODER ENTRAR AL SEGÜENT
            respostaValida = false;
            intents = 0;

            if (!intentsEsgotats) {
                System.out.println("Categoria del llibre?: ");
                System.out.println("    Educació i formació (0)");
                System.out.println("    Oci i temps lliure (1)");
                System.out.println("    Novel·la (2)");
                System.out.println("    Salut i benestar (3)");
            }

            while (!respostaValida && !intentsEsgotats) {

                numCategoria = scanner.nextInt();

                if (numCategoria < 0 || numCategoria > catSubcat[INDEX_CATEGORIES_TITOLS].length - 1) {
                    System.out.println("Error en la categoria del llibre, no compleix paràmetres. Introdueix de nou: ");
                    intents++;
                    if (intents == INTENTS_MAX) {
                        intentsEsgotats = true;
                    }
                } else {
                    switch (numCategoria) {
                        case 0:
                            llibresCat_0++;
                            break;
                        case 1:
                            llibresCat_1++;
                            break;
                        case 2:
                            llibresCat_2++;
                            break;
                        case 3:
                            llibresCat_3++;
                    }
                    respostaValida = true;
                    dadesLlibres[INDEX_CATEGORIA][indexLlibreActual] = numCategoria;
                }

            }
            respostaValida = false;
            intents = 0;


            if (!intentsEsgotats) {

                numLlibresPerCategoria[numCategoria]++;
                categoriaSeleccionada = catSubcat[INDEX_CATEGORIES_TITOLS][numCategoria];
                System.out.println("Subcategoria de " + categoriaSeleccionada + "?: ");
                for (int i = 0; i < catSubcat[numCategoria + 1].length; i++) {
                    System.out.println("    " + catSubcat[numCategoria + 1][i] + " (" + i + ") ");
                }
                numSubcategoria = scanner.nextInt();


                while (!respostaValida && !intentsEsgotats) {

                    if (numSubcategoria < 0 || numSubcategoria > catSubcat[numCategoria + 1].length - 1) {
                        intents++;
                        if (intents == INTENTS_MAX) {
                            intentsEsgotats = true;
                        } else {
                            System.out.println("Error en la subcategoria del llibre, no compleix paràmetres. Introdueix de nou: ");
                            numSubcategoria = scanner.nextInt();
                        }
                    } else {
                        respostaValida = true;

                        dadesLlibres[INDEX_SUBCATEGORIA][indexLlibreActual] = numSubcategoria;

                        System.out.println(subcategoriaSeleccionada);

                    }

                }


            }
            respostaValida = false;
            intents = 0;
            while (!respostaValida && !intentsEsgotats) {

                System.out.println("Preu?: ");
                preu = scanner.nextInt();

                if (preu < 0 || preu > 99) {
                    System.out.println("Error en el preu del llibre, no compleix paràmetres. Introdueix de nou: ");
                    intents++;
                    if (intents == INTENTS_MAX) {
                        intentsEsgotats = true;
                    }
                } else {
                    respostaValida = true;
                    dadesLlibres[INDEX_PREU][indexLlibreActual] = preu;
                }
            }

            respostaValida = false;
            intents = 0;

            while (!respostaValida && !intentsEsgotats) {

                System.out.println("Unitats venudes?: ");
                unitatsVenudes = scanner.nextInt();

                if (unitatsVenudes < 0 || unitatsVenudes > 999999) {
                    System.out.println("Error en les unitats venudes del llibre, no compleix paràmetres. Introdueix de nou: ");
                    intents++;
                    if (intents == INTENTS_MAX) {
                        intentsEsgotats = true;
                    }
                } else {
                    respostaValida = true;
                    dadesLlibres[INDEX_UNITATS][indexLlibreActual] = unitatsVenudes;
                }

            }
            respostaValida = false;
            //si el llibre introduit te un error eliminem les dades introduides d'aquell llibre
            if (!intentsEsgotats) {
                for (int i = 0; i <= indexLlibreActual; i++) {
                    int categoriaLlibreNum = dadesLlibres[INDEX_CATEGORIA][i];
                    int subcategoriaLlibreNum = dadesLlibres[INDEX_SUBCATEGORIA][i];
                    System.out.println("codi: " + dadesLlibres[INDEX_CODI][i] +
                            " categoria: " + catSubcat[INDEX_CATEGORIES_TITOLS][categoriaLlibreNum] +
                            " subcategoria: " + catSubcat[categoriaLlibreNum + 1][subcategoriaLlibreNum] +
                            " preu " + dadesLlibres[INDEX_PREU][i] +
                            " unitats: " + dadesLlibres[INDEX_UNITATS][i]);
                    // Calcular guanys bruts per llibre
                    double guanysBruts = preu * unitatsVenudes;
                    System.out.println("Guanys Bruts: " + guanysBruts + " euros");

                    // Calcular IVA corresponent
                    double iva = guanysBruts * IVA;
                    System.out.println("IVA (" + (IVA * 100) + "%): " + iva + " euros");

                    // Guanys bruts
                    guanysTotals += guanysBruts;


                }
            }

            System.out.println("Vol introduïr un altre registre? SI (1) NO (2)");
            respostaRegistre = scanner.nextInt();
            if (respostaRegistre == 1) {
                indexLlibreActual++;

            } else {
                System.out.println("S'han introduït " + (indexLlibreActual + 1) + " registres de llibres");
                for (int i = 0; i <= indexLlibreActual; i++) {
                    int categoriaLlibreNum = dadesLlibres[INDEX_CATEGORIA][i];
                    int subcategoriaLlibreNum = dadesLlibres[INDEX_SUBCATEGORIA][i];
                    System.out.println("codi: " + dadesLlibres[INDEX_CODI][i] +
                            " categoria: " + catSubcat[INDEX_CATEGORIES_TITOLS][categoriaLlibreNum] +
                            " subcategoria: " + catSubcat[categoriaLlibreNum + 1][subcategoriaLlibreNum] +
                            " preu " + dadesLlibres[INDEX_PREU][i] +
                            " unitats: " + dadesLlibres[INDEX_UNITATS][i]);
                }
                calIntroduir = false;

            }

        }

        int catConsultada = 0;

        System.out.println("Vol consultar per categoria? (SI (1) NO (2))");
        int volConsultar = scanner.nextInt();
        if (volConsultar == 1) {
            System.out.println("Quina categoria? ");
            System.out.println("    Educació i formació (0)");
            System.out.println("    Oci i temps lliure (1)");
            System.out.println("    Novel·la (2)");
            System.out.println("    Salut i benestar (3)");
            catConsultada = scanner.nextInt();

            for (int k = 0; k <= indexLlibreActual; k++) {
                for (int l = k + 1; l < indexLlibreActual; l++) {
                    if (dadesLlibres[INDEX_CODI][k] > dadesLlibres[INDEX_CODI][l]) {
                        int canvi = dadesLlibres[INDEX_CODI][k];
                        dadesLlibres[INDEX_CODI][k] = dadesLlibres[INDEX_CODI][l];
                        dadesLlibres[INDEX_CODI][l] = canvi;

                        int canviCategoria = dadesLlibres[INDEX_CATEGORIA][k];
                        dadesLlibres[INDEX_CATEGORIA][k] = dadesLlibres[INDEX_CATEGORIA][l];
                        dadesLlibres[INDEX_CATEGORIA][l] = canviCategoria;

                        int canviSubCategoria = dadesLlibres[INDEX_SUBCATEGORIA][k];
                        dadesLlibres[INDEX_SUBCATEGORIA][k] = dadesLlibres[INDEX_SUBCATEGORIA][l];
                        dadesLlibres[INDEX_SUBCATEGORIA][l] = canviSubCategoria;

                        int canviPreu = dadesLlibres[INDEX_PREU][k];
                        dadesLlibres[INDEX_PREU][k] = dadesLlibres[INDEX_PREU][l];
                        dadesLlibres[INDEX_PREU][l] = canviPreu;

                        int canviUnitats = dadesLlibres[INDEX_UNITATS][k];
                        dadesLlibres[INDEX_UNITATS][k] = dadesLlibres[INDEX_UNITATS][l];
                        dadesLlibres[INDEX_UNITATS][l] = canviUnitats;
                    }
                }
            }

            for (int i = 0; i < indexLlibreActual; i++) {
                int categoriaLlibreNum = dadesLlibres[INDEX_CATEGORIA][i];
                int subcategoriaLlibreNum = dadesLlibres[INDEX_SUBCATEGORIA][i];
                if (dadesLlibres[INDEX_CATEGORIA][i] == catConsultada) {
                    System.out.println("codi: " + dadesLlibres[INDEX_CODI][i] +
                            " categoria: " + catSubcat[INDEX_CATEGORIES_TITOLS][categoriaLlibreNum] +
                            " subcategoria: " + catSubcat[categoriaLlibreNum + 1][subcategoriaLlibreNum] +
                            " preu " + dadesLlibres[INDEX_PREU][i] +
                            " unitats: " + dadesLlibres[INDEX_UNITATS][i]);

                }
            }
        }
        // Calcular guanys bruts per llibre
        double guanysBruts = preu * unitatsVenudes;
        System.out.println("Guanys Bruts: " + guanysBruts + " euros");


        System.out.println("Vol veure un resum estadístic de les dades? (SI (1) NO (2)");
        int respostaResum = scanner.nextInt();
        if (respostaResum == 1) {
            System.out.println("Número de llibres introduïts: " + (indexLlibreActual+1));
            System.out.println("Número de llibres per categoria: ");
            System.out.println("    Educació i formació: " + llibresCat_0);
            System.out.println("    Oci i temps lliure: " + llibresCat_1);
            System.out.println("    Novel·la: " + llibresCat_2);
            System.out.println("    Salut i benestar: " + llibresCat_3);

            System.out.println("Ingresos totals: " + guanysTotals + " euros");
            double ivaTotal = guanysTotals * IVA;
            System.out.println("IVA total (" + (IVA * 100) + "%): " + ivaTotal + " euros");
        }
    }
}

