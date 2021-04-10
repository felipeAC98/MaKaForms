grammar makaforms;

//COMENTARIO: 

/* define palavras reservadas (palavres chaves) da linguagem makaforms*/
PALAVRAS_CHAVE: 'inicioFormulario' | 'fimFormulario' | 'campo' | 'botao' |
                'texto' | 'senha'| 'data' | 'email' | 'escolhaUnica' |
                'escolhaMultipla' | 'arquivo' | 'caixaTexto' | 'foto' | 
                'pdf' | 'um' | 'multiplos';

/* define identificadores das opções de algum campo, pode conter letras, números, caracteres especiais e espaço"*/
IDENT: ('\u0080'..'\uffff')*;

/* define cadeias da linguagem  */
/* sequencia ao longo do algoritmo que necessariamente comeca e finaliza com aspas  */
/* onde entre essas aspas nao pode ocorrer " ou quebra de linha */
CADEIA: '"'(~('"'|'\n'))*'"';

/* definicao de numeros inteiros    */
/* formados pela sequencia de 1 ou + caracteres numericos de 0 a 9.*/
NUM_INT: ('0'..'9')+; 

/* define caracteres que nao sao letras */
SIMBOLOS: ',' ; 

WS: [ \t\r\n]+ -> skip;    

//Inicio do sintatico

programa: 'inicioFormulario' titulo corpo 'fimFormulario';

titulo: 'titulo' CADEIA;

corpo: (cmp)*;

cmp: 'campo' (cmpTexto | cmpSenha | cmpData | cmpEmail | cmpEUnica | cmpEMultipla
    );

cmpTexto: 'texto' expressao;
cmpSenha: 'senha' expressao;
cmpData: 'data' expressao;
cmpEmail: 'email' expressao;
cmpEUnica: 'escolhaUnica' identificador (',' identificador)* expressao;
cmpEMultipla: 'escolhaMultipla' identificador (',' identificador)* expressao;

expressao: CADEIA;

identificador: IDENT;