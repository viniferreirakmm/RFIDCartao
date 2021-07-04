# RFIDCartao
<i>Uma API baseada em RFID (Radio Frequency Identification) utilizando a interface de comunicação 
Serial do tipo RS232 para leitura e escrita em TAG's (cartões inteligentes)</i>

## Especificações

> O leitor e gravador para cartões 13.56MHz, ISO 14443A, RF-CLASSIC é destinado
aos Integradores de Moedeiros Eletrônicos, Validadores de Transações e
aplicações complexas

> Versão do Modelo: 13.56Mhz V2.1-A - INELTEC

> Documentação oficial: http://www.autotag.com.br/produtos/RFIDCC/DOC/Leitores%20Mifare/RF-Classic.pdf

## Planejamento

- [ ]  Bootstrap
    - [ ] CheckPorts
    - [ ] EstabilishCommunication
    - [ ] ResetRFIDReader
    - [ ] ConfigRFIDReader
    

- [ ] GetAction
    - [ ] IOConfig
    - [ ] Read
    - [ ] Write
    - [ ] teste


- [ ] ASCIIHexaConversor
- [ ] ResetCard
- [ ] SoundSignal
- [ ] CardMapper
- [ ] BigEndian/LittleEndian

## Agentes

### Eeprom (Eletrically Erasable Programmable Read-Only Memory)

Chip da Eemprom RFIDCC (Tecnologia Brasileira) = <b>MXT - MS50</b>
São 16 setores com 16 bytes cada. Dependendo de onde o byte ativo estará, alteramos e configuramos 
a Eeprom.

* 00 à 03 - Read only

______________________________________________
* 04 - Comunicação Binária

______________________________________________
* 05 - bit 1 = modo contínuo(ASCII) | bit 2 = modo binário | bit 3 = null | bit 4 = Timeout binário
  > Modo Contínuo: Válido apenas em modo ASCII (não Binário). Se em 1: Transmite contínuamente o nro
  serial do um cartão de proximidade presente no campo do leitor.
  Modo Binário: em 1: Comunicação em modo binario.
  Timeout Binário: em 1: Espera de no máximo 100ms pelo ETX em modo binário.
  Para o comando WS (escrita de 256 bytes), o timeout é de 300ms.

______________________________________________
* 06 - Baud Rate / bit 0 = 9600
> Valores de Baud Rate: 000 =9600 ; 001=19200 ; 010=57600 ; 011=115200, 100=38400.

______________________________________________
* 07 à 0F - Livre

______________________________________________
### Observação:
> Para acessar a eeprom de configuração são usados os comandos “RE” e “WE”.
Exemplo para lêr a posição 04 (ID): RE04
Exemplo para escrever o valor “03” na posição 04: WE0403<cr>
O comando “WE” deve ser terminado com o caracter <cr> para diferenciar de escrita no bloco E0 de
cartão.
Em modo binário o comando “WE” é aceito com ou sem <cr>

### Comandos Eeprom (Cartão)

![img.png](src/main/resources/img/img.png)

* <b>"C" </b> - Leitura Contínua de ID dos Cartões;
* <b>"S" </b> - Select cartão;
* <b>"M" </b> - Seleciona Lista de cartões para operações;
* <b>"L" </b> - Login nos ultimos blocos de cada setor (L + nº do setor + ¹tipo de chave + chave);
* <b>"R" </b> - Lê um único bloco (00 à 0F);
* <b>"W" </b> - Escreve em um bloco;
* <b>"RV"</b> - lê unico bloco 4 bytes hexa;
* <b>"WV"</b> - Escreve 16 blocos 16 bytes;
* <b>"WM"</b> - Escreve chave mestra de login na Eeprom;

> Exemplo de flag: "FF 07 80 FF";
> Tipo de chave: 10 = 16bits(chave A) ou 32 = 64bits(chave B);

* <b>"X" </b> - Reseta Eeprom;

### TAG 13.56MHz (Cartão Inteligente)

Tem capacidade de 1MB sendo 16 setores com 4 blocos cada um.
Cada Bloco possui espaço de 16 bytes, ou 16 caracteres ASCII, ou 32 caracteres Hexadecimais.
No Ultimo bloco de cada setor é onde se encontra a interface de configuração de leitura/escrita.
O Ultimo bloco de cada setor é composto de: 

* 6 primeiros bytes --> chave A;
* 4 bytes seguintes --> flag de Acesso;
* 6 ultimos bytes   --> chave B;

### Comandos de Leitor
![img.png](src/main/resources/img/ComandosDeLeitor.png)


## Do Projeto

1) Verificar se a Eeprom está conectada à porta.
2) Resetar Eeprom
3) Configurar parâmetros da comunicação sr232