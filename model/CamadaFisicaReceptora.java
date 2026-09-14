/* ***************************************************************
* Autor............: Heverton Dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 28/08/2026
* Ultima alteracao.: 14/09/2026
* Nome.............: CamadaFisicaReceptora.java
* Funcao...........: Representar a camada fisica do lado do destinatario,
* decodifica as mensagens recebidas.
*************************************************************** */

package model;

public class CamadaFisicaReceptora {
  public static void enviarParaCamadaDeAplicacao(char[] fluxoBrutoDeBits) {
    int[] quadro;

    switch (Estado.tipoDeCodificacao) {
      case BINARIA:
        quadro = DecodificacaoBinaria(fluxoBrutoDeBits);
        break;
      case MANCHESTER:
        quadro = DecodificacaoManchester(fluxoBrutoDeBits);
        break;
      default:
        quadro = DecodificacaoManchesterDiferencial(fluxoBrutoDeBits);
    }

    CamadaAplicacaoReceptora.enviarParaAplicacao(quadro);
  }

  private static int[] DecodificacaoBinaria(char[] sinais) {
    int quadro[] = new int[sinais.length / 32];

    for (int i = 0; i < quadro.length; i++) {
      for (int j = 0; j < 32; j++) {
        quadro[i] <<= 1;
        quadro[i] |= sinais[j + i * 32] == 'A' ? 1 : 0;
      }
    }

    return quadro;
  }

  private static int[] DecodificacaoManchester(char[] sinais) {
    int quadro[] = new int[sinais.length / 64];

    for (int i = 0; i < quadro.length; i++) {
      for (int j = 0; j < 32; j++) {
        quadro[i] <<= 1;
        quadro[i] |= manchesterParaBit(sinais, i, j);
      }
    }

    return quadro;
  }

  private static int[] DecodificacaoManchesterDiferencial(char[] sinais) {
    int quadro[] = new int[sinais.length / 64];
    int bitAnterior = 0;

    for (int i = 0; i < quadro.length; i++) {
      for (int j = 0; j < 32; j++) {
        int bit = manchesterParaBit(sinais, i, j);
        quadro[i] <<= 1;

        if (i == 0 && j == 0) {
          quadro[i] |= bit;
          bitAnterior = bit;
        } else {
          if (bit == bitAnterior) {
            quadro[i] |= 0;
          } else {
            quadro[i] |= 1;
            bitAnterior = bit;
          }
        }
      }
    }

    return quadro;
  }

  private static int manchesterParaBit(char[] sinais, int i, int j) {
    if (sinais[j * 2 + i * 64] == 'A' &&
        sinais[j * 2 + 1 + i * 64] == 'B') {
      return 1;
    } else {
      return 0;
    }
  }
}
