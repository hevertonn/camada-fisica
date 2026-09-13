/* ***************************************************************
* Autor............: Heverton Dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 28/08/2026
* Ultima alteracao.: 30/08/2026
* Nome.............: AplicacaoReceptora.java
* Funcao...........: Representar o destinatario dos dados.
*************************************************************** */

package model;

public class AplicacaoReceptora {
  public static void exibir(String mensagem) {
    Estado.caixaTextoMorpheu.setText(mensagem);
  }
}
