/* ***************************************************************
* Autor............: Heverton Dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 06/09/2026
* Ultima alteracao.: 06/09/2026
* Nome.............: Estado.java
* Funcao...........: Compartilhar de objetos de forma global.
*************************************************************** */

package model;

import javafx.scene.control.TextArea;
import javafx.scene.shape.Polyline;

public class Estado {
  public static TipoDeCodificacaoEnum tipoDeCodificacao;
  public static Polyline representacaoSinais = new Polyline();
  public static TextArea caixaTextoMorpheu;
}
