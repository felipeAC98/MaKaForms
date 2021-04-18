# MaKaForms

## Autores

  Felipe Alves      RA: 744335
  
  Mariana Zagatti   RA: 628620
  
## Sobre

Trabalho realizado para a disciplina Construção de Compiladores, com o professor Daniel Lucrédio.

O trabalho consiste em um compilador completo para a linguagem de programação criada. Ele realiza as análises léxica, sintática e semântica do código, indicando possíveis erros, e gera o código equivalente em HTML.

O analisador léxico irá fazer a tokenização dos valores encontrados no código, além disso irá efetuar a verificação de erros léxicos como comentários ou cadeias iniciadas mas não fechadas e também de símbolos não reconhecidos pela linguagem. Na pasta [entrada_lexico](https://github.com/felipeAC98/MaKaForms/tree/master/entrada_lexico) encontram-se alguns exemplos de códigos com erros léxicos.

Já o analisador sintático verificará erros estruturais do código como por exemplo a falta da definição do título do formulário ou um campo definido sem seu identificador. Na pasta [entrada_sintatico](https://github.com/felipeAC98/MaKaForms/tree/master/entrada_sintatico) encontram-se alguns exemplos de códigos com erros sintáticos.

Por fim, o analisador semântico irá fazer a verificação de 5 diferentes erros semânticos, sendo elas:
- Identificador já declarado, irá verificar se o identificador já não foi declarado anteriormente.
- Identificador possui underscore em seu texto, indentificadores não devem possuir underscore.
- Cor inválida, verificará se o hexadecimal da cor inserida é válido.
- Placeholder não permitido, existem campos que não aceitam placeholder.
- Placeholder não definido, existem campos em que o placeholder é necessário.

Na pasta [entrada_semantico](https://github.com/felipeAC98/MaKaForms/tree/master/entrada_semantico) encontram-se alguns exemplos de códigos com erros semânticos.

Por fim, na pasta [entrada_gerador](https://github.com/felipeAC98/MaKaForms/tree/master/entrada_gerador) disponibilizamos exemplos de códigos prontos.

## Vídeo descritivo

https://youtu.be/Whm-wF7aGq4

## Descrição

A linguagem MaKaForms é destinada a facilitar a criação de formulários HTML, transformando comandos de apenas uma linha em formulários completos. Com ela, é possível criar formulários com diversos tipos de inputs, além de personalizar a cor do background e a cor da fonte, sem se preocupar com os demais aspectos da estrutura HTML.

## Como compilar o compilador

Caso o usuário queira modificar o código do compilador, os autores sugerem que seja instalado o software Apache Netbeans 12. É possível fazer seu download no site oficial clicando [aqui](https://netbeans.apache.org/download/index.html).

Durante a instalação, deve-se optar por instalar o plugin para a ferramenta Apache Maven.

Em seguida, o usuário precisa clonar o projeto em sua máquina utilizando a ferramenta Git, ou fazer o download do .zip e descompactá-lo em um diretório, para então poder modificá-lo no Netbeans.

Deve-se lembrar de, sempre que fizer alguma modificação no código e quiser testá-lo, dar um build no projeto. A tecla F11 funciona como um atalho para essa função.

Em seguida, para rodá-lo, deve-se seguir as instruções da seção abaixo.

## Como compilar o código

Caso o usuário queira usar o compilador, pode-se fazer o download do [arquivo .jar](https://github.com/felipeAC98/MaKaForms/blob/master/target/MakaForms-1.0-SNAPSHOT-jar-with-dependencies.jar). Para executá-lo, deve ser utilizado no Prompt de Comando do Windows o comando abaixo. Nota-se que o usuário deve ter instalado em sua máquina o Java, e sua variável de ambiente deve estar configurada.

> java -jar "parâmetro 1" "parâmetro 2" "parâmetro 3"

O parâmetro 1 trata-se do caminho do arquivo .jar, o parâmetro 2 é o arquivo .txt de entrada, com o código na linguagem MaKaForms, e o parâmetro 3 é o arquivo .html que terá como saída o formulário. Abaixo, é apresentado um exemplo real de uso do comando.

> java -jar MakaForms-1.0-SNAPSHOT-jar-with-dependencies.jar entrada_makaforms.txt saida_html.html

## Exemplos de uso (casos de teste)

A estrutura do código deve sempre ser iniciada com a palavra reservada ***inicioFormulario***, e finalizada com ***fimFormulario***.

Em seguida, escolhe-se a cor do background e a cor da fonte, com os comandos abaixo, seguidos por um código hexadecimal.

>corBackground #FFFFFF
>
>corFonte #000000

Todo formulário deve conter, logo após das cores, um título, no formato ***titulo "cadeira de caracteres"***, e um botao, logo acima de ***fimFormulario***, no formato ***botao "cadeia de caracteres"***.

Segue abaixo um exemplo das instruções acima.

>inicioFormulario
>
>corBackground #FFFFFF
>
>corFonte #000000
>
>titulo "Cadastro de pessoa"
>
>***comandos***
>
>botao "Cadastrar"
>
>fimFormulario

Onde há a palavra ***comandos*** no exemplo acima, devem estar os campos desejados no formulário. Existem oito tipos de campos: ***_texto***, ***_email***, ***_senha***, ***_data***, ***_escolhaUnica***, ***_escolhaMultipla***, ***_arquivo*** e ***_caixaTexto***.

>***_texto*** define um campo de texto de apenas uma linha
>
>***_email*** define um campo para um endereço de email
>
>***_senha*** define um campo para uma senha
>
>***_data*** define um controle para data, com dia, mês e ano
>
>***_escolhaUnica*** define um campo em que o usuário pode selecionar apenas uma opção
>
>***_escolhaMultipla*** define um campo em que o usuário pode selecionar uma ou mais opções
>
>***_arquivo*** define um campo para selecionar um arquivo
>
>***_caixaTexto*** define uma caixa de texto
>

Os comandos para definir campos seguem o padrão abaixo:

>campo _nome_do_campo id_do_campo:"label_do_campo"

Por exemplo:

>campo _texto nome:"Nome"

Alguns dos campos ainda apresentam algumas particularidades. 

Para criar um campo ***_escolhaMultipla*** ou um ***_escolhaUnica***, você deve especificar as opções entre aspas, junto com seus identificadores, como no exemplo abaixo.

>campo _escolhaMultipla gato:"Gato", cachorro:"Cachorro", passaro:"Pássaro", peixe:"Peixe", outros:"Outro" pet:"Animal de estimação"

Para um campo do tipo ***_arquivo***, deve-se optar se o usuário poderá escolher apenas um ou mais arquivos, e se os arquivos a serem escolhidos serão fotos ou PDFs. Abaixo, há um exemplo. A palavra ***_foto*** pode ser trocada por ***_pdf***, e ***um*** pode ser trocada por ***multiplos***.

>campo _arquivo _foto um fotoperfil:"Foto de perfil"

Para o campo ***_caixaTexto***, deve-se colocar logo em seguida a quantidade de linhas e a quantidade de colunas desejadas no campo de texto. Também deve-se colocar um placeholder, logo em seguida, entre aspas. Por exemplo:

> campo _caixaTexto 10 100 descricao:"Descrição" "Placeholder"

Também é necessário colocar placeholder em ***_texto*** e ***_email***, de forma similar.

Abaixo, há um exemplo de código inteiro escrito na linguagem MKForms. Esse código gera a página HTML [deste link](https://github.com/felipeAC98/MaKaForms/blob/master/saida_html.html).

```
inicioFormulario
corBackground #FAAAFF
corFonte #000000
titulo "Cadastro de pessoa"
campo _texto nome:"Nome" "Insira um nome"
campo _texto sobrenome:"Sobrenome" "Insira um sobrenome"
campo _email email:"Email" "Insira um email"
campo _senha senha:"Senha"
campo _data dtNascimento:"Data de nascimento"
campo _escolhaUnica feminino:"Feminino", masculino:"Masculino", outro:"Outro" genero:"Gênero"
campo _escolhaMultipla gato:"Gato", cachorro:"Cachorro", passaro:"Pássaro", peixe:"Peixe", outros:"Outro" pet:"Animal de estimação"
campo _caixaTexto 10 20 descricao:"Descrição" "Insira uma descricao"
campo _arquivo _foto um foto:"Foto de perfil"
botao "Cadastrar"
fimFormulario
```

Se necessário, é possível comentar o código, entre {}, como abaixo.
>{comentário}
