/* ***************************************************************
* Autor............: Heverton Dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 28/08/2026
* Ultima alteracao.: 14/09/2026
* Nome.............: MeioDeComunicacao.java
* Funcao...........: Meio pelo qual os dados sao compartilhados.
*************************************************************** */

package model;

import java.util.ArrayList;

import javafx.util.Duration;

public class MeioDeComunicacao {
  public static void transportar(char[] fluxoBrutoDeBits) {
    ArrayList<Double> pontos = new ArrayList<>();
    double x = 40, y = 20;

    pontos.add(-564d);
    pontos.add(y);
    pontos.add(0d);
    pontos.add(y);

    for (int i = 0; i < fluxoBrutoDeBits.length; i++) {
      if (fluxoBrutoDeBits[fluxoBrutoDeBits.length - (i + 1)] == 'A') {
        pontos.add(x * i);
        pontos.add(-y);
        pontos.add(x * (i + 1));
        pontos.add(-y);
      } else {
        pontos.add(x * i);
        pontos.add(y);
        pontos.add(x * (i + 1));
        pontos.add(y);
      }
    }

    pontos.add(pontos.get(pontos.size() - 2));
    pontos.add(y);
    pontos.add(pontos.get(pontos.size() - 2) + 564);
    pontos.add(y);

    Estado.representacaoSinais.getPoints().clear();
    Estado.representacaoSinais.getPoints().addAll(pontos);

    Estado.caminhoAnimacao.setStartX(564 - Estado.representacaoSinais.getBoundsInLocal().getWidth() / 2);
    Estado.caminhoAnimacao.setEndX(Estado.representacaoSinais.getBoundsInLocal().getWidth() / 2);

    Estado.transition.setNode(Estado.representacaoSinais);
    Estado.transition.setDuration(Duration.millis(Estado.representacaoSinais.getBoundsInLocal().getWidth() * 5));
    Estado.transition.setPath(Estado.caminhoAnimacao);

    Estado.transition.play();
    Estado.transition.setOnFinished(event -> CamadaFisicaReceptora.enviarParaCamadaDeAplicacao(fluxoBrutoDeBits));
  }
}
