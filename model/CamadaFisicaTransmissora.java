/* ***************************************************************
* Autor............: Heverton Dos Santos Borges
* Matricula........: 202511495
* Inicio...........: 28/08/2026
* Ultima alteracao.: 14/09/2026
* Nome.............: CamadaFisicaTransmissora.java
* Funcao...........: Representar a camada fisica do lado do remetente,
* codifica e envia as mensagens.
*************************************************************** */

package model;

public class CamadaFisicaTransmissora {
  public static void enviarPelaRede(int[] quadro) {
    char[] fluxoBrutoDeBits;

    switch (Estado.tipoDeCodificacao) {
      case BINARIA:
        fluxoBrutoDeBits = codificacaoBinaria(quadro);
        break;
      case MANCHESTER:
        fluxoBrutoDeBits = codificacaoManchester(quadro);
        break;
      default:
        fluxoBrutoDeBits = codificacaoManchesterDiferencial(quadro);
    }

    MeioDeComunicacao.transportar(fluxoBrutoDeBits);
  }

  private static char[] codificacaoBinaria(int[] quadro) {
    char[] sinais = new char[quadro.length * 32];

    for (int i = 0; i < quadro.length; i++) {
      for (int j = 0; j < 32; j++) {
        sinais[j + i * 32] = lerBit(quadro, i) == 1 ? 'A' : 'B';
      }
    }

    return sinais;
  }

  private static char[] codificacaoManchester(int[] quadro) {
    char[] sinais = new char[quadro.length * 64];

    for (int i = 0; i < quadro.length; i++) {
      for (int j = 0; j < 32; j++) {
        bitParaManchester(lerBit(quadro, i), sinais, i, j);
      }
    }

    return sinais;
  }

  private static char[] codificacaoManchesterDiferencial(int[] quadro) {
    char[] sinais = new char[quadro.length * 64];
    int bitAnterior = 0;

    for (int i = 0; i < quadro.length; i++) {
      for (int j = 0; j < 32; j++) {
        int bit = lerBit(quadro, i);

        if (i == 0 && j == 0) {
          bitParaManchester(bit, sinais, i, j);
          bitAnterior = bit;
        } else {
          if (bit == 1) {
            bitAnterior = 1 - bitAnterior;
            bitParaManchester(bitAnterior, sinais, i, j);
          } else {
            bitParaManchester(bitAnterior, sinais, i, j);
          }
        }
      }
    }

    return sinais;
  }

  private static int lerBit(int[] quadro, int indiceContainer) {
    int mascara = 1 << 31;
    int bit = (quadro[indiceContainer] & mascara) >>> 31;
    quadro[indiceContainer] <<= 1;
    return bit;
  }

  private static void bitParaManchester(int bit, char[] sinais, int i, int j) {
    if (bit == 1) {
      sinais[j * 2 + i * 64] = 'A';
      sinais[j * 2 + 1 + i * 64] = 'B';
    } else {
      sinais[j * 2 + i * 64] = 'B';
      sinais[j * 2 + 1 + i * 64] = 'A';
    }
  }
}
