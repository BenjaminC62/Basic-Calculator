/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 *
 * (c) 2022-2023 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.ihm.calc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * La classe CalculatorController permet de gérer les actions réalisées par
 * l'utilisateur sur l'interface de la calculatrice.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public class CalculatorController {

    /**
     * La mémoire de la calculatrice, qui contient la dernière valeur calculée.
     */
    private int memory = 0;

    /**
     * Le dernier opérateur arithmétique sur lequel l'utilisateur a appuyé.
     */
    private String operator;

    /**
     * Un booléen pour savoir s'il faut réinitialiser la calculatrice au
     * prochain appui sur un chiffre.
     */
    private boolean toClear = false;

    //Méthodes pour button :

    @FXML // fx:id="butonAC"
    private Button butonAC; // Value injected by FXMLLoader

    @FXML // fx:id="displayNumber"
    private Label displayNumber; // Value injected by FXMLLoader

    @FXML
    void onClickButonAllClear(ActionEvent event) {
        memory = 0;
        setScreenContent("");
        operator = null;
        toClear = false;
    }

    @FXML
    void onClickButonDivided(ActionEvent event) {
        pressOperator("/");
    }

    @FXML
    void onClickButonEight(ActionEvent event) {
        pressDigit(8);
    }

    @FXML
    void onClickButonEquals(ActionEvent event) {
        computeResult();
        toClear = true;
        operator = null;
    }

    @FXML
    void onClickButonFive(ActionEvent event) {
        pressDigit(5);
    }

    @FXML
    void onClickButonFour(ActionEvent event) {
        pressDigit(4);
    }

    @FXML
    void onClickButonMinus(ActionEvent event) {
        pressOperator("-");
    }

    @FXML
    void onClickButonMultiply(ActionEvent event) {
        pressOperator("*");
    }

    @FXML
    void onClickButonNine(ActionEvent event) {
        pressDigit(9);
    }

    @FXML
    void onClickButonOne(ActionEvent event) {
        pressDigit(1);
    }

    @FXML
    void onClickButonPlus(ActionEvent event) {
        pressOperator("+");
    }

    @FXML
    void onClickButonSeven(ActionEvent event) {
        pressDigit(7);
    }

    @FXML
    void onClickButonSix(ActionEvent event) {
        pressDigit(6);
    }

    @FXML
    void onClickButonThree(ActionEvent event) {
        pressDigit(3);
    }

    @FXML
    void onClickButonTwo(ActionEvent event) {
        pressDigit(2);
    }

    @FXML
    void onClickButonZero(ActionEvent event) {
        pressDigit(0);
    }


    /**
     * Modifie le contenu affiché par l'écran de la calculatrice.
     *
     * @param content Le nouveau contenu à afficher.
     */
    private void setScreenContent(String content) {
        displayNumber.setText(content);
    }

    /**
     * Donne le contenu affiché par l'écran de la calculatrice.
     *
     * @return Ce qui est actuellement affiché sur l'écran de la calculatrice.
     */
    private String getScreenContent() {
        return displayNumber.getText();
    }

    /**
     * Exécute l'action correspondant à l'appui sur un chiffre.
     *
     * @param value La valeur du chiffre sur lequel l'utilisateur a appuyé.
     */
    private void pressDigit(int value) {
        if (toClear) {
            // Il faut effacer pour commencer un nouveau calcul.
            setScreenContent("");
            toClear = false;
        }

        // On ajoute le chiffre au nombre affiché à l'écran.
        setScreenContent(getScreenContent() + value);
    }

    /**
     * Exécute l'action correspondant à l'appui sur un opérateur.
     *
     * @param symbol Le symbole de l'opérateur sur lequel l'utilisateur a appuyé.
     */
    private void pressOperator(String symbol) {
        if (operator == null) {
            // Il faut garder en mémoire le nombre choisi.
            memory = Integer.parseInt(getScreenContent());

        } else {
            // Un opérateur a déjà été choisi.
            // Dans ce cas, il faut d'abord calculer le résultat intermédiaire.
            memory = computeResult();
        }

        // Il faut garder le symbole de l'opérateur en mémoire.
        operator = symbol;

        // Lorsqu'un nouveau chiffre sera choisi, il faudra effacer l'écran.
        toClear = true;
    }

    /**
     * Calcule le résultat de l'opération saisie par l'utilisateur.
     *
     * @return Le résultat de l'opération.
     */
    private int computeResult() {
        // On récupère la dernière valeur saisie.
        int value = Integer.parseInt(getScreenContent());

        // On calcule le résultat en fonction de l'opérateur.
        int result = memory;
        if (operator != null) {
            result = switch (operator) {
                case "+" -> memory + value;
                case "-" -> memory - value;
                case "*" -> memory * value;
                case "/" -> memory / value;
                default -> throw new UnsupportedOperationException();
            };
        }

        // On affiche ensuite le résultat obtenu.
        setScreenContent(Integer.toString(result));
        return result;
    }

}
