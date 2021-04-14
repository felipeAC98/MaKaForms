# MaKaForms

## Autores

  Felipe Alves      RA: 744335
  
  Mariana Zagatti   RA: 628620
  
## Sobre

Trabalho realizado para a disciplina de Construção de Compiladores, com o professor Daniel Lucrédio.

## Vídeo descritivo



## Descrição

A linguagem MaKaForms é destinada a facilitar a criação de formulários HTML, transformando comandos de apenas uma linha em formulários completos.

## Como compilar o compilador

Caso o usuário queira somente usar o compilador, pode-se fazer o download do [arquivo .jar](https://github.com/felipeAC98/MaKaForms/blob/master/target/MakaForms-1.0-SNAPSHOT-jar-with-dependencies.jar). Para executá-lo, deve ser utilizado no Prompt de Comando do Windows o comando abaixo. Nota-se que o usuário deve ter instalado em sua máquina o Java, e sua variável de ambiente deve estar configurada.

> java -jar "parâmetro 1" "parâmetro 2" "parâmetro 3"

O parâmetro 1 trata-se do caminho do arquivo .jar, o parâmetro 2 é o arquivo .txt de entrada, com o código na linguagem MaKaForms, e o parâmetro 3 é o arquivo .html que terá como saída o formulário. Abaixo, é apresentado um exemplo real de uso do comando.

> java -jar MakaForms-1.0-SNAPSHOT-jar-with-dependencies.jar entrada_makaforms.txt saida_html.html

Caso o usuário queira modificar o código do compilador, os autores sugerem que seja instalado o software Apache Netbeans 12. É possível fazer seu download no site oficial clicando [aqui](https://netbeans.apache.org/download/index.html).

Durante a instalação, deve-se optar por instalar o plugin para a ferramenta Apache Maven.

Em seguida, o usuário precisa clonar o projeto em sua máquina utilizando a ferramenta Git, ou fazer o download do .zip e descompactá-lo em um diretório, para então poder modificá-lo no Netbeans.

Deve-se lembrar de, sempre que fizer alguma modificação no código e quiser testá-lo, é necessário dar um build no projeto. A tecla F11 funciona como um atalho para essa função.

Em seguida, para rodá-lo, deve-se seguir as instruções já mostradas nessa seção.

## Como compilar o código

## Exemplos de uso (casos de teste)



