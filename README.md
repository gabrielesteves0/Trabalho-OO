# Trabalho-OO
Trabalho referente ao semestre 2021.3 da disciplina DCC025 - Orientação a Objetos da Universidade Federal de Juiz de Fora. Autor: Gabriel Antônio Esteves Matta

Descrição completa do trabalho:
Será realizado neste trabalho a implementação em Java de um jogo, na qual o jogador irá controlar um personagem que possui o objetivo de completar uma masmorra e, conforme o andamento do jogo, o mesmo vai descendo os níveis da masmorra, explorando-os, coletando itens e se fortificando para conseguir concluir a mesma.
A exploração da masmorra será dividida em salas, aonde em cada nível, um número predeterminado de salas será gerado aleatoriamente e, para completar o nível atual, o jogador deve encontrar a escada para o nível seguinte. As salas serão geradas aleatoriamente, podendo conter uma das opções a seguir dentro da mesma:
I.	Monstro: ao entrar na sala, o jogador irá se deparar com um inimigo, e caso consiga derrotá-lo, será recompensado com experiência e algum item que o mesmo guardava. Porém, caso o jogador perca o combate e não consiga fugir, ele perde todo o seu progresso até ali e retorna ao começo da masmorra.

II.	Mercador: ao entrar na sala, o jogador se depara com um vendedor de itens. Nestas salas, o jogador poderá vender seus itens atuais, trocando-os por ouro, podendo comprar novos itens oferecidos pelo mercador ou “comprar” um nível, dando ao jogador a experiência que falta para subir para o próximo nível.

III.	Item ou Benção: ao entrar na sala, o jogador encontra algum item aleatório, que poderá ser coletado e usado pelo mesmo, ou recebe uma bênção, que pode acrescer seu Poder de Combate temporariamente.

IV.	Armadilha: ao entrar na sala, o jogador sofrerá algum tipo de penalidade devido a uma armadilha, seja ela uma maldição (como perda de Poder de Combate temporário ou quebra de algum item específico).

V.	Escada: ao entrar na sala, o jogador encontra a escada para o próximo nível.

VI.	Nada: nestas salas, não há nada.


O sistema de combate do jogo será baseado em Poder de Combate. Ao enfrentar um inimigo, o jogador será informado do poder do mesmo e, caso seu poder seja maior que o do oponente, ele sai vitorioso, caso contrário ele perde a disputa. Em casos de empate, o monstro foge com os seus tesouros, e o jogador não é penalizado em nada (porém não é recompensado também). 
Quando o jogador perde a disputa, ele tem a opção de fugir, que varia de monstro para monstro e depende da diferença de Nível entre o monstro e o jogador. Caso o jogador fuja, ele retorna para a sala anterior e a sala seguinte é gerada novamente. Caso ele não consiga fugir, é game over.

O poder de combate do jogador é afetado pelas seguintes características:
I.	Nível: Para cada nível do personagem, seu Poder de Combate é aumentado em 2 pontos.

II.	Itens: Cada item terá um valor de Poder que, quando o jogador utiliza ou equipa o mesmo, este valor é acrescido ao Poder de Combate do personagem.

III.	Bênçãos ou Maldições: Caso o jogador esteja sendo afetado por alguma bênção ou maldição, seu Poder de Combate será acrescido ou diminuído, de acordo com o efeito da mesma.
Os itens são separados em dois grupos: os Equipáveis e os Consumíveis. Os itens equipáveis, como o nome sugere, devem ser “vestidos” pelo personagem para que seus efeitos sejam utilizados, e são divididos em 4 subcategorias, que são: Armas, Armaduras, Botas e Mantos. O jogador só poderá equipar um item de cada categoria por vez, limitando-o a quatro itens equipáveis simultâneos.
Já os itens Consumíveis não são divididos em nenhuma subcategoria, e podem ser utilizados durante o combate, mas, após seu uso, o item é gasto. Em geral, estes itens servem para aumentar o seu Poder de Combate ou abaixar o Poder do inimigo temporariamente ou cancelar algum efeito de maldição.
Os itens Consumíveis, para serem utilizados, devem ser colocados nos Espaços do Cinto, para que seja possível seu uso durante o combate. Os itens que não forem Equipados ou colocados nos Espaços do Cinto, ficarão alocados na Mochila, que só pode ser gerenciada ao final da sala (depois de combates ou aplicação de efeitos de maldições).
Os monstros, maldições, bênçãos e itens não são separados por Poder ou Nível, ou seja, mesmo que o jogador tenha de começar, ele pode encontrar o monstro mais poderoso na primeira sala.
Para concluir o jogo, o jogador deve completar o Nível 10 na masmorra, e o Nível máximo que o personagem pode alcançar é o Nível 15. A quantidade de Salas por nível é a seguinte (incluindo a sala com a Escada):
•	Nível 1: 03 Sala;
•	Nível 2: 04 Salas;
•	Nível 3: 06 Salas;
•	Nível 4: 06 Salas;
•	Nível 5: 07 Salas;
•	Nível 6: 07 Salas;
•	Nível 7: 08 Salas;
•	Nível 8: 08 Salas;
•	Nível 9: 10 Salas;
•	Nível 10: 10 Salas;

Os tipos de cada sala irão ser determinados aleatoriamente (com exceção da Escada, que sempre será a última sala do nível), que serão:
•	Níveis 1-3: Monstro (30%), Mercador (5%), Armadilha (15%), Item ou Bênção (30%), Nada (20%).
•	Níveis 4-6: Monstro (40%), Mercador (15%), Armadilha (20%), Item ou Bênção (20%), Nada (5%).
•	Níveis 7-10: Monstro (50%), Mercador (10%), Armadilha (25%), Item ou Bênção (15%), Nada (0%).

