lexer grammar makaforms;

//COMENTARIO: 

/* define palavras reservadas (palavres chaves) da linguagem makaforms*/
PALAVRAS_CHAVE: 'inicioFormulario' | 'fimFormulario' | 'campo' | 'botao' |
                'texto' | 'senha'| 'data' | 'email' | 'escolhaUnica' |
                'escolhaMultipla' | 'arquivo' | 'caixaTexto';

/* define identificadores da linguagem apenas com restricao de nao inicializar com caracteres numericos, podendo conter apenas _ como caractere especial alem de letras e numeros*/
IDENT: ('A'..'Z' | 'a'..'z' | '_')('A'..'Z' | 'a'..'z' | '0'..'9' | '_')*;

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