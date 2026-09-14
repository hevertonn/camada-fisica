/* ***************************************************************
* Autor............: Heverton Dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 28/08/2026
* Ultima alteracao.: 14/09/2026
* Nome.............: CamadaAplicacaoTransmissora.java
* Funcao...........: Representar a camada de aplicacao do lado do remetente.
*************************************************************** */

package model;

public class CamadaAplicacaoTransmissora {
  public static void enviarParaCamadaFisica(String mensagem) {
    if (!mensagem.isEmpty()) {
      int[] quadro = codificarEmArrayInt(mensagem);

      CamadaFisicaTransmissora.enviarPelaRede(quadro);
    }
  }

  private static int[] codificarEmArrayInt(String mensagem) {
    int quadro[] = new int[(int) Math.ceil(mensagem.length() / 4.0)], indiceQuadro = 0;

    for (int i = 1; i <= mensagem.length(); i++) {
      quadro[indiceQuadro] |= mensagem.charAt(i - 1);

      if (i % 4 != 0) {
        quadro[indiceQuadro] <<= 8;
      } else {
        indiceQuadro++;
      }
    }

    int espacosLivres = mensagem.length() % 4 == 0 ? 0 : 3 - (mensagem.length() % 4);
    quadro[quadro.length - 1] <<= espacosLivres * 8;

    return quadro;
  }
}
