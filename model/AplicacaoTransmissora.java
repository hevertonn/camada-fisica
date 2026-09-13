/* ***************************************************************
* Autor............: Heverton Dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 28/08/2026
* Ultima alteracao.: 28/08/2026
* Nome.............: AplicacaoTransmissora.java
* Funcao...........: Representar o remetente dos dados.
*************************************************************** */

package model;

public class AplicacaoTransmissora {
  public static void enviarParaCamadaDeAplicacao(String mensagem) {
    CamadaAplicacaoTransmissora.enviarParaCamadaFisica(mensagem);
  }
}
