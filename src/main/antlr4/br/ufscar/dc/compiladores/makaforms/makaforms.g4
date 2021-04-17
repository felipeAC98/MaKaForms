grammar makaforms;

//COMENTARIO: 

/* define palavras reservadas (palavres chaves) da linguagem makaforms*/
PALAVRAS_CHAVE: 'inicioFormulario' | 'fimFormulario' | 'campo' | 'botao' |
                '_texto' | '_senha'| '_data' | '_email' | 'escolhaUnica' |
                '_escolhaMultipla' | '_arquivo' | '_caixaTexto' | '_foto' | 
                '_pdf' | 'um' | 'multiplos' | 'corBackground' | 'corFonte';

/* define cadeias da linguagem  */
/* sequencia ao longo do algoritmo que necessariamente comeca e finaliza com aspas  */
/* onde entre essas aspas nao pode ocorrer " ou quebra de linha */
CADEIA: '"'(~('"'|'\n'))*'"';

//Definicao para erro de cadeia
ERRO_CADEIA: '"'(~('"'|'\n'))*;

/* definicao de numeros inteiros    */
/* formados pela sequencia de 1 ou + caracteres numericos de 0 a 9.*/
NUM_INT: ('0'..'9')+; 

/* define identificadores das opções de algum campo, pode conter letras, números, caracteres especiais e espaço"*/
IDENT: ('A'..'Z' | 'a'..'z' | '_' | NUM_INT)('A'..'Z' | 'a'..'z'| '_' | NUM_INT)*;

/* define caracteres que nao sao letras */
SIMBOLOS: ':' | ',' | '(' | ')' | '#'; 

/* definicao para erro - simbolo nao identificado, nao faz parte da linguagem  */
ERRO_SIMBOLO: SIMBOLO_NAODEFINIDO | '}'; 

/* definicao para erro - simbolo nao identificado, nao faz parte da linguagem  */
SIMBOLO_NAODEFINIDO:'*' |'@' | '$' | '¨' | '~' | '!' | ';' | '%' | '?'| '|' | '=' | '[' | ']' | '^'  | '-' | '&' | '..' ; 

//Definicao para numero hexadecimal
COR_HEXA: '#' (NUM_INT | 'A'..'F' | 'a'..'f')*; 

WS: [ \t\r\n]+ -> skip;    

//Inicio do sintatico

programa: 'inicioFormulario' corBackground corFonte titulo corpo botao 'fimFormulario';

//Definicoes de algumas cores para o HTML
corBackground: 'corBackground' corHexa;
corFonte: 'corFonte' corHexa;
corHexa: COR_HEXA;

titulo: 'titulo' CADEIA;

corpo: (cmp)* ;

//Botao de envio
botao: 'botao' CADEIA;

//Definicoes de cores

//Campos do HTML
cmp: defcmp='campo' (cmpTexto | cmpSenha | cmpData | cmpEmail | cmpEUnica | cmpEMultipla | cmpArquivo | cmpCaixaTexto) placeHolder?;

//Campos e itens de campos seram tratados da mesma forma, serao definidos por um identificador e a expressao referente a ele que sera seu conteudo
identCampo: identificador ':' expressao ; 
item: identCampo;
cmpTexto: '_texto' identCampo;
cmpSenha: '_senha' identCampo;
cmpData: '_data' identCampo;
cmpEmail: '_email' identCampo;
cmpEUnica: '_escolhaUnica' item (',' item)* identCampo;
cmpEMultipla: '_escolhaMultipla' item (',' item)* identCampo;
cmpArquivo: '_arquivo' (foto='_foto' | pdf='_pdf') ('um'|'multiplos') identCampo;
cmpCaixaTexto: '_caixaTexto' tamanhoVertical tamanhoHorizontal identCampo;
tamanhoVertical: NUM_INT;
tamanhoHorizontal: NUM_INT;

placeHolder: CADEIA;

expressao: CADEIA;

identificador: IDENT;