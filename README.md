# RFIDCartao
<i>Uma API baseada em RFID (Radio Frequency Identification) utilizando comunicação Serial do tipo RS232 
para leitura e escrita em TAG's (cartões inteligentes)</i>

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
São 16 setores com 16 bytes cada. Dependendo de onde o byte ativo estará, alteramos e configuramos a Eeprom.

* 00 à 03 - Read only
* 04      - Comunicação Binária
* 05      - bit 1 = modo contínuo(ASCII) | bit 2 = modo binário | bit 3 = null | bit 4 = Timeout binário
* 06      - Baud Rate / bit 0 = 9600
* 07 à 0F - Livre

### Comandos Eeprom
* <b>"X" </b> - Reseta Eeprom;
* <b>"C" </b> - Leitura Contínua de ID dos Cartões;
* <b>"S" </b> - Select cartão;
* <b>"L" </b> - Login nos ultimos blocos de cada setor (L + nº do setor + ¹tipo de chave + chave);
* <b>"R" </b> - Lê um único bloco (00 à 0F);
* <b>"W" </b> - Escreve em um bloco;
* <b>"RV"</b> - lê unico bloco 4 bytes hexa;
* <b>"WV"</b> - Escreve 16 blocos 16 bytes;
* <b>"WM"</b> - Escreve chave mestra de login na Eeprom;

> Exemplo de flag: "FF 07 80 FF";
> Tipo de chave: 10 = 16bits(chave A) ou 32 = 64bits(chave B);

### TAG 13.56MHz (Cartão Inteligente)

Tem capacidade de 1MB sendo 16 setores com 4 blocos cada um.
Cada Bloco possui espaço de 16 bytes, ou 16 caracteres ASCII, ou 32 caracteres Hexadecimais.
No Ultimo bloco de cada setor é onde se encontra a interface de configuração de leitura/escrita.
O Ultimo bloco de cada setor é composto de: 

* 6 primeiros bytes --> chave A;
* 4 bytes seguintes --> flag de Acesso;
* 6 ultimos bytes   --> chave B;

