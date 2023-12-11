/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package p2_castellsp;

/**
 * @author gerardcastells
 */

import java.sql.PreparedStatement;
import java.util.Scanner;

public class P2_CastellsP {

    //DECLAREM TOTES LES CONSTANTS DEL CODI
    private static final String CATEGORIA_0 = "Educació i formació";
    private static final String CATEGORIA_1 = "Oci i temps lliure";
    private static final String CATEGORIA_2 = "Novel·la";
    private static final String CATEGORIA_3 = "Salut i benestar";

    private static final String SUBCATEGORIA_EDU_0 = "Ciència i enginyeria";
    private static final String SUBCATEGORIA_EDU_1 = "Biografies i memòries";
    private static final String SUBCATEGORIA_EDU_2 = "Finances";

    private static final String SUBCATEGORIA_OCI_0 = "Guies de viatge";
    private static final String SUBCATEGORIA_OCI_1 = "Cuina";
    private static final String SUBCATEGORIA_OCI_2 = "Esports";

    private static final String SUBCATEGORIA_NOVELA_0 = "Adolescents";
    private static final String SUBCATEGORIA_NOVELA_1 = "Ciència ficció i fantasia";
    private static final String SUBCATEGORIA_NOVELA_2 = "Comèdia i humor";

    private static final String SUBCATEGORIA_SALUT_0 = "Nutrició";
    private static final String SUBCATEGORIA_SALUT_1 = "Medicina General";
    private static final String SUBCATEGORIA_SALUT_2 = "Fisioteràpia";

    private static final int INTENTS_MAX = 3;

    public static void main(String[] args) {

        //FEM LA CREACIÓ DEL NOSTRE SCANNER PER LLEGIR DADES
        Scanner scanner = new Scanner(System.in);

        //AQUESTS SON ELS ARRAYS QUE UTILITZAREM PER EMMAGATZEMAR DADES DELS LLIBRES QUE ENREGISTREM
        int[] diccionariCodis = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        String[] diccionariCategories = {"", "", "", "", "", "", "", "", "", ""};
        int[] dicCategoriesNum = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        String[] diccionariSubcategories = {"", "", "", "", "", "", "", "", "", ""};
        int[] diccionariPreus = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        int[] diccionariUnitats = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

        int index = 0; //AQUESTA VARIABLE ENS SERVEIX PER RECORRER ELS ARRAYS DELS LLIBRES

        //DEFINIM DIVERSES VARIABLES
        int codiLlibre = 0, numCategoria = 0, numSubcategoria = 0, preu = 0, unitatsVenudes = 0, registresIntroduits = 1, respostaRegistre;

        //AQUESTES VARIABLES ENS SERVIRAN PER DESPRÉS MOSTRAR EL RESUM ESTADÍSTIC DE LES DADES
        int llibresCat_0 = 0, llibresCat_1 = 0, llibresCat_2 = 0, llibresCat_3 = 0;

        String categoriaSeleccionada = "", subcategoriaSeleccionada = "";

        //DEFINIM ELS VALORS BOOL QUE ENS SERVIRAN PER ENTRAR I SORTIR DELS BUCLES I VALIDAR DADES
        boolean respostaValida = false;
        boolean calIntroduir = true;
        boolean registreAdicional = true;

        //BUCLE PRINCIPAL QUE S'EXECUTA MENTES ES VULGUIN INTRODUIR DADES DE LLIBRES
        while (calIntroduir) {

            boolean intentsEsgotats = false;
            int intents = 0;

            //LOOP DE VALIDACIÓ DE DADES PELS CODIS DELS LLIBRES
            while (!respostaValida && !intentsEsgotats) {

                System.out.println("Codi llibre?: ");
                codiLlibre = scanner.nextInt();

                if (codiLlibre < 100 || codiLlibre > 999) {
                    System.out.println("Error en el codi del llibre, no compleix paràmetres. Introdueix de nou: ");
                    intents++;
                    if (intents == INTENTS_MAX) {
                        intentsEsgotats = true;
                    }
                } else {
                    respostaValida = true;
                    diccionariCodis[index] = codiLlibre; //GUARDEM EL CODI DEL LLIBRE AL NOSTRE DICCIONARI
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

                if (numCategoria < 0 || numCategoria > 3) {
                    System.out.println("Error en la categoria del llibre, no compleix paràmetres. Introdueix de nou: ");
                    intents++;
                    if (intents == INTENTS_MAX) {
                        intentsEsgotats = true;
                    }
                } else {
                    respostaValida = true;
                    dicCategoriesNum[index] = numCategoria;
                }

            }
            respostaValida = false;
            intents = 0;


            if (!intentsEsgotats) {
                switch (numCategoria) {
                    case 0:
                        llibresCat_0++;
                        categoriaSeleccionada = CATEGORIA_0;
                        System.out.println("Subcategoria de " + CATEGORIA_0 + "?: ");
                        System.out.println("    " + SUBCATEGORIA_EDU_0 + " (0)");
                        System.out.println("    " + SUBCATEGORIA_EDU_1 + " (1)");
                        System.out.println("    " + SUBCATEGORIA_EDU_2 + " (2)");
                        numSubcategoria = scanner.nextInt();


                        while (!respostaValida && !intentsEsgotats) {

                            if (numSubcategoria < 0 || numSubcategoria > 3) {
                                System.out.println("Error en la subcategoria del llibre, no compleix paràmetres. Introdueix de nou: ");
                                intents++;
                                if (intents == INTENTS_MAX) {
                                    intentsEsgotats = true;
                                }
                            } else {
                                respostaValida = true;
                                switch (numSubcategoria) {
                                    case 0:
                                        subcategoriaSeleccionada = SUBCATEGORIA_EDU_0;
                                        break;
                                    case 1:
                                        subcategoriaSeleccionada = SUBCATEGORIA_EDU_1;
                                        break;
                                    case 2:
                                        subcategoriaSeleccionada = SUBCATEGORIA_EDU_2;
                                        break;
                                }
                                System.out.println(subcategoriaSeleccionada);
                                diccionariSubcategories[index] = subcategoriaSeleccionada;
                                diccionariCategories[index] = categoriaSeleccionada;
                            }

                        }
                        break;

                    case 1:
                        llibresCat_1++;
                        categoriaSeleccionada = CATEGORIA_1;
                        System.out.println("Subcategoria de " + CATEGORIA_1 + "?: ");
                        System.out.println("    " + SUBCATEGORIA_OCI_0 + " (0)");
                        System.out.println("    " + SUBCATEGORIA_OCI_1 + " (1)");
                        System.out.println("    " + SUBCATEGORIA_OCI_2 + " (2)");
                        numSubcategoria = scanner.nextInt();
                        while (!respostaValida && !intentsEsgotats) {

                            if (numSubcategoria < 0 || numSubcategoria > 3) {
                                System.out.println("Error en la subcategoria del llibre, no compleix paràmetres. Introdueix de nou: ");
                                intents++;
                                if (intents == INTENTS_MAX) {
                                    intentsEsgotats = true;
                                }
                            } else {
                                respostaValida = true;
                                switch (numSubcategoria) {
                                    case 0:
                                        subcategoriaSeleccionada = SUBCATEGORIA_OCI_0;
                                        break;
                                    case 1:
                                        subcategoriaSeleccionada = SUBCATEGORIA_OCI_1;
                                        break;
                                    case 2:
                                        subcategoriaSeleccionada = SUBCATEGORIA_OCI_2;
                                        break;
                                }
                                System.out.println(subcategoriaSeleccionada);
                                diccionariSubcategories[index] = subcategoriaSeleccionada;
                                diccionariCategories[index] = categoriaSeleccionada;
                            }

                        }
                        break;

                    case 2:
                        llibresCat_2++;
                        categoriaSeleccionada = CATEGORIA_2;
                        System.out.println("Subcategoria de " + CATEGORIA_2 + "?: ");
                        System.out.println("    " + SUBCATEGORIA_NOVELA_0 + " (0)");
                        System.out.println("    " + SUBCATEGORIA_NOVELA_1 + " (1)");
                        System.out.println("    " + SUBCATEGORIA_NOVELA_2 + " (2)");
                        numSubcategoria = scanner.nextInt();
                        while (!respostaValida && !intentsEsgotats) {

                            if (numSubcategoria < 0 || numSubcategoria > 3) {
                                System.out.println("Error en la subcategoria del llibre, no compleix paràmetres. Introdueix de nou: ");
                                intents++;
                                if (intents == INTENTS_MAX) {
                                    intentsEsgotats = true;
                                }
                            } else {
                                respostaValida = true;
                                switch (numSubcategoria) {
                                    case 0:
                                        subcategoriaSeleccionada = SUBCATEGORIA_NOVELA_0;
                                        break;
                                    case 1:
                                        subcategoriaSeleccionada = SUBCATEGORIA_NOVELA_1;
                                        break;
                                    case 2:
                                        subcategoriaSeleccionada = SUBCATEGORIA_NOVELA_2;
                                        break;
                                }
                                System.out.println(subcategoriaSeleccionada);
                                diccionariSubcategories[index] = subcategoriaSeleccionada;
                                diccionariCategories[index] = categoriaSeleccionada;
                            }

                        }
                        break;

                    case 3:
                        llibresCat_3++;
                        categoriaSeleccionada = CATEGORIA_3;
                        System.out.println("Subcategoria de " + CATEGORIA_3 + "?: ");
                        System.out.println("    " + SUBCATEGORIA_SALUT_0 + " (0)");
                        System.out.println("    " + SUBCATEGORIA_SALUT_1 + " (1)");
                        System.out.println("    " + SUBCATEGORIA_SALUT_2 + " (2)");
                        numSubcategoria = scanner.nextInt();
                        while (!respostaValida && !intentsEsgotats) {

                            if (numSubcategoria < 0 || numSubcategoria > 3) {
                                System.out.println("Error en la subcategoria del llibre, no compleix paràmetres. Introdueix de nou: ");
                                intents++;
                                if (intents == INTENTS_MAX) {
                                    intentsEsgotats = true;
                                }
                            } else {
                                respostaValida = true;
                                switch (numSubcategoria) {
                                    case 0:
                                        subcategoriaSeleccionada = SUBCATEGORIA_SALUT_0;
                                        break;
                                    case 1:
                                        subcategoriaSeleccionada = SUBCATEGORIA_SALUT_1;
                                        break;
                                    case 2:
                                        subcategoriaSeleccionada = SUBCATEGORIA_SALUT_2;
                                        break;
                                }
                                diccionariSubcategories[index] = subcategoriaSeleccionada;
                                diccionariCategories[index] = categoriaSeleccionada;
                            }

                        }
                        break;
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
                    diccionariPreus[index] = preu;
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
                    diccionariUnitats[index] = unitatsVenudes;
                }

            }
            respostaValida = false;
            intents = 0;

            System.out.println("Codi: " + codiLlibre + " || Categoria: " + categoriaSeleccionada + " || Subcategoria: " + subcategoriaSeleccionada + " || Preu: " + preu + " || Unitats: " + unitatsVenudes);
            System.out.println("Vol introduïr un altre registre? SI (1) NO (2)");
            respostaRegistre = scanner.nextInt();
            if (respostaRegistre == 1) {
                registresIntroduits++;
                index++;

            } else {
                System.out.println("S'han introduït " + registresIntroduits + " registres de llibres");
                index = 0;
                while (diccionariCodis[index] != -1) {
                    System.out.println("codi: " + diccionariCodis[index] + " categoria: " + diccionariCategories[index] + " subcategoria: " + diccionariSubcategories[index] + " preu " + diccionariPreus[index] + " unitats: " + diccionariUnitats[index]);
                    index++;
                }
                calIntroduir = false;

            }

        }

        int catConsultada = 0;
        int arraySize = 4;


        System.out.println("Vol consultar per categoria? (SI (1) NO (2))");
        int volConsultar = scanner.nextInt();
        if (volConsultar == 1) {
            index = 0;
            System.out.println("Quina categoria? ");
            System.out.println("    Educació i formació (0)");
            System.out.println("    Oci i temps lliure (1)");
            System.out.println("    Novel·la (2)");
            System.out.println("    Salut i benestar (3)");
            catConsultada = scanner.nextInt();
            System.out.println("Codi" + "   Categoria   " + "  Subcategoria   " + "   Preu   " + "   Unitats   ");


            for (int k = 0; k < arraySize; k++) {
                for (int l = k + 1; l < arraySize; l++) {
                    if (diccionariCodis[k] > diccionariCodis[l]) {
                        int canvi = diccionariCodis[k];
                        diccionariCodis[k] = diccionariCodis[l];
                        diccionariCodis[l] = canvi;

                        int canviCategoria = dicCategoriesNum[k];
                        dicCategoriesNum[k] = dicCategoriesNum[l];
                        dicCategoriesNum[l] = canviCategoria;

                        String canviSubCategoria = diccionariSubcategories[k];
                        diccionariSubcategories[k] = diccionariSubcategories[l];
                        diccionariSubcategories[l] = canviSubCategoria;

                        int canviPreu = diccionariPreus[k];
                        diccionariPreus[k] = diccionariPreus[l];
                        diccionariPreus[l] = canviPreu;

                        int canviUnitats = diccionariUnitats[k];
                        diccionariUnitats[k] = diccionariUnitats[l];
                        diccionariUnitats[l] = canviUnitats;
                    }
                }
            }

            for (int i = 0; i < arraySize; i++) {
                if (dicCategoriesNum[i] == catConsultada) {
                    System.out.println(diccionariCodis[i] + "   " + diccionariCategories[i] + "   " + diccionariSubcategories[i] + "   " + diccionariPreus[i] + "   " + diccionariUnitats[i]);
                }
            }


        }

        System.out.println("Vol veure un resum estadístic de les dades? (SI (1) NO (2)");
        int respostaResum = scanner.nextInt();
        if (respostaResum == 1) {
            System.out.println("Número de llibres introduïts: " + registresIntroduits);
            System.out.println("Número de llibres per categoria: ");
            System.out.println("    Educació i formació: " + llibresCat_0);
            System.out.println("    Oci i temps lliure: " + llibresCat_1);
            System.out.println("    Novel·la: " + llibresCat_2);
            System.out.println("    Salut i benestar: " + llibresCat_3);
        }


    }


}



    

