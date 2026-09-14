/* ***************************************************************
* Autor............: Heverton Dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 28/08/2026
* Ultima alteracao.: 14/09/2026
* Nome.............: CamadaAplicacaoReceptora.java
* Funcao...........: Representar a camada de aplicacao do lado do destinatario.
*************************************************************** */

package model;

public class CamadaAplicacaoReceptora {
  public static void enviarParaAplicacao(int[] quadro) {
    String mensagem = decodificarArrayInt(quadro);

    AplicacaoReceptora.exibir(mensagem);
  }

  private static String decodificarArrayInt(int[] quadro) {
    String mensagem = "";
    int mascara = -16777216;

    for (int i = 0; i < quadro.length; i++) {
      for (int j = 0; j < 4; j++) {
        mensagem += (char) ((quadro[i] & mascara) >> 24);
        quadro[i] <<= 8;
      }
    }

    return mensagem;
  }
}
