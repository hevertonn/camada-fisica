/* ***************************************************************
* Autor............: Heverton Dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 06/09/2026
* Ultima alteracao.: 14/09/2026
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
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.AplicacaoTransmissora;
import model.Estado;
import model.TipoDeCodificacaoEnum;

public class MainViewController {
  @FXML
  private AnchorPane panePrincipal;
  @FXML
  private AnchorPane paneConfiguracoes;
  @FXML
  private Pane paneAnimacao;
  @FXML
  private TextArea caixaTextoNeo;
  @FXML
  private TextArea caixaTextoMorpheu;

  @FXML
  private void initialize() {
    Estado.caixaTextoMorpheu = caixaTextoMorpheu;

    Rectangle areaDeCorte = new Rectangle(564, 55);
    areaDeCorte.setVisible(false);

    paneAnimacao.getChildren().add(Estado.representacaoSinais);
    paneAnimacao.getChildren().add(Estado.caminhoAnimacao);
    paneAnimacao.setClip(areaDeCorte);

    Estado.representacaoSinais.getStyleClass().add("representacao-sinais");

    Estado.caminhoAnimacao.setStartY(55 / 2);
    Estado.caminhoAnimacao.setEndY(55 / 2);
    Estado.caminhoAnimacao.setVisible(false);
  }

  @FXML
  private void enviarMensagem() {
    String mensagem = caixaTextoNeo.getText();
    caixaTextoNeo.setText("");

    Estado.transition.stop();
    AplicacaoTransmissora.enviarParaCamadaDeAplicacao(mensagem);
  }

  @FXML
  private void alterarTipoCodificacao(ActionEvent event) {
    Button button = (Button) event.getSource();

    switch (button.getUserData().toString()) {
      case "binaria":
        Estado.tipoDeCodificacao = TipoDeCodificacaoEnum.BINARIA;
        break;
      case "manchester":
        Estado.tipoDeCodificacao = TipoDeCodificacaoEnum.MANCHESTER;
        break;
      case "manchester-diferencial":
        Estado.tipoDeCodificacao = TipoDeCodificacaoEnum.MANCHESTER_DIFERENCIAL;
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
