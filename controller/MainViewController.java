/* ***************************************************************
* Autor............: Heverton Dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 06/09/2026
* Ultima alteracao.: 06/09/2026
* Nome.............: MainViewController.java
* Funcao...........: Gerenciar as interacoes na interface principal.
*************************************************************** */

package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;

public class MainViewController {
  @FXML
  private AnchorPane panePrincipal;
  @FXML
  private AnchorPane paneConfiguracoes;
  @FXML
  private TextArea caixaTextoNeo;
  @FXML
  private TextArea caixaTextoMorpheu;

  @FXML
  private void enviarMensagem() {
    String mensagem = caixaTextoNeo.getText();
  }

  @FXML
  private void alterarTipoCodificacao(ActionEvent event) {
    Button button = (Button) event.getSource();

    switch (button.getUserData().toString()) {
      case "binaria":
        break;
      case "manchester":
        break;
      case "manchester-diferencial":
        break;
    }

    esconderPaneConfiguracoes();
  }

  @FXML
  private void exibirPaneConfiguracoes() {
    GaussianBlur blur = new GaussianBlur();
    panePrincipal.setEffect(blur);
    paneConfiguracoes.setVisible(true);
  }

  private void esconderPaneConfiguracoes() {
    panePrincipal.setEffect(null);
    paneConfiguracoes.setVisible(false);
  }
}
