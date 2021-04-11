grammar makaforms;

//COMENTARIO: 

/* define palavras reservadas (palavres chaves) da linguagem makaforms*/
PALAVRAS_CHAVE: 'inicioFormulario' | 'fimFormulario' | 'campo' | 'botao' |
                '_texto' | '_senha'| '_data' | '_email' | 'escolhaUnica' |
                '_escolhaMultipla' | '_arquivo' | '_caixaTexto' | 'foto' | 
                'pdf' | 'um' | 'multiplos';

/* define identificadores das opções de algum campo, pode conter letras, números, caracteres especiais e espaço"*/
IDENT: ('A'..'Z' | 'a'..'z' | '_' )('A'..'Z' | 'a'..'z' | '_')*;

/* define cadeias da linguagem  */
/* sequencia ao longo do algoritmo que necessariamente comeca e finaliza com aspas  */
/* onde entre essas aspas nao pode ocorrer " ou quebra de linha */
CADEIA: '"'(~('"'|'\n'))*'"';

//Definicao para erro de cadeia
ERRO_CADEIA: '"'(~('"'|'\n'))*;

/* definicao de numeros inteiros    */
/* formados pela sequencia de 1 ou + caracteres numericos de 0 a 9.*/
NUM_INT: ('0'..'9')+; 

/* define caracteres que nao sao letras */
SIMBOLOS: ':' | ',' | '(' | ')'; 

/* definicao para erro - simbolo nao identificado, nao faz parte da linguagem  */
ERRO_SIMBOLO: SIMBOLO_NAODEFINIDO | '}'; 

/* definicao para erro - simbolo nao identificado, nao faz parte da linguagem  */
SIMBOLO_NAODEFINIDO:'@' | '$' | '¨' | '~' | '!' | ';' | '%' | '?'| '|' | '=' | '[' | ']' | '^'  | '-' | '&' | '..' ; 

WS: [ \t\r\n]+ -> skip;    

//Inicio do sintatico

programa: 'inicioFormulario' titulo corpo 'fimFormulario';

titulo: 'titulo' CADEIA;

corpo: (cmp)*;

//Campos do HTML
cmp: 'campo' (cmpTexto | cmpSenha | cmpData | cmpEmail | cmpEUnica | cmpEMultipla
    );

cmpTexto: '_texto' nomeCampo;
cmpSenha: '_senha' nomeCampo;
cmpData: '_data' nomeCampo;
cmpEmail: '_email' nomeCampo;
cmpEUnica: '_escolhaUnica' item (',' item)* nomeCampo;
cmpEMultipla: '_escolhaMultipla' item (',' item)* nomeCampo;

//Itens presentes em cada campo de alternativas, eh definido pelo identificador e a expressao
item: identificador ':' expressao ; 

//Semelhante a um item, o nome do campo tambem eh tratado de forma semelhante
nomeCampo: identificador ':' expressao ; 

expressao: CADEIA;

identificador: IDENT;