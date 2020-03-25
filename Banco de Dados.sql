create table perfilacesso(
id_perfil int primary key,
descricao varchar
);

-- pré determinar que 1 é adm e 2 é usuario comun
insert into perfilacesso(id_perfil,descricao) values (1,'adm');
insert into perfilacesso(id_perfil,descricao) values (2,'comun');


create table login (
id_login serial primary key,
login varchar,
senha varchar,
perfil_id int,
constraint uk_login unique (login),
constraint fk_login_perfil Foreign Key(perfil_id)
references perfilacesso(id_perfil)
);

-- inserir um login do adm
insert into login(login,senha,perfil_id) values ('adm@adm.com','123456',1);

create table genero(
	id_genero int primary key ,
	desc_genero varchar	
);

create table usuario (
id_usuario int primary key,
nome varchar,
sobrenome varchar,
cpf varchar,
telefone varchar,
constraint uk_cpf unique (cpf),
constraint fk_usuario_login Foreign Key(id_usuario)
references login(id_login)
);

-- inserir um adm
insert into usuario(id_usuario,nome,sobrenome,cpf,telefone) values (1,'CERQS','ABC','12345678910','1234');


create table produto(
id_livro serial primary key,
titulo varchar,
autor varchar,
preco numeric,
lancamento date,
isbn varchar,
sinopse varchar,
imagem varchar (100),
pdf varchar,
id_genero int,
status varchar,
constraint pk_isbn unique (isbn),
constraint fk_produto_genero Foreign Key(id_genero)
references genero(id_genero)
);


---GENERO
insert into genero (id_genero,desc_genero) values (1,'Terror'),(2,'Suspense'),(3,'Ação'),(4,'Comedia'),(5,'Educação'),(6,'Romance'),(7,'Quadrinhos'),(8,'Fantasia'),(9,'Biografias'),(10,'Ficçao Cientifica'),(11,'Infantil');



insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco, imagem,id_genero,status) values ('04-01-1990','Diário de um Banana.pdf','Diário de um Banana','Jeff Kinney','978-0-1433-0383-1','Não é fácil ser criança. E ninguém sabe disso melhor do que Greg Heffley, que se vê mergulhado no ensino fundamental, onde fracotes subdesenvolvidos dividem os corredores com garotos que são mais altos, mais malvados e já se barbeiam. Como Greg diz em seu diário: "Só não espere que eu seja todo Querido diário isso, Querido diário aquilo." Para nossa sorte, o que Greg Heffley diz que fará e o que ele realmente faz são duas coisas bem diferentes. No primeiro livro da coleção, o autor e ilustrador Jeff Kinney nos apresenta um herói improvável e encantador. Um garoto comum às voltas com os desafios da puberdade.',
 13.40, 'diariodeumbanana.jpg',11,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco, imagem,id_genero,status) values ('01-01-1991','TESTE.pdf','Diário de um Banana: Rodrick é o Cara','Jeff Kinney','978-01-8109-8799-9','Faça o que quiser, só não pergunte a Greg Heffley como foram suas férias de verão, por que ele realmente não quer falar sobre isso. De volta às aulas, Greg está ansioso para enterrar de vez os últimos três meses... e um acontecimento em particular. Mas seu irmão mais velho, Rodrick, não vai deixar que as coisas caiam no esquecimento assim tão fácil. Ele é testemunha de um "pequeno" incidente que Greg quer manter em sigilo. Mas sabe como são os segredos, não é? Logo, logo estão na boca do povo, especialmente quando há um diário envolvido na confusão.',
 17.96, 'rodrickeocara.jpg',11,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('03-07-1998','TESTE.pdf','Diário de um Banana: A Gota DÁgua','Jeff Kinney','978-02-8109-8821-7','Greg não se emenda mesmo. Mas agora é seu pai quem vai tentar botar um pouco de juízo na cabeça do garoto. Será que vai conseguir? Ou Greg vai estragar tudo? Qual será a gota d’água que vai fazer transbordar a paciência de seu pai?',
 22.10, 'agotadagua.jpg',11,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-12-1999','TESTE.pdf','Diário de um Banana: Dias de cão ','Jeff Kinney','978-03-8109-9751-6','O verão que Greg vai ter vai ser o pior verão de sua vida. Já começa com ele e seu melhor amigo, Rowley Jefferson. Eles vão à um clube, durante as férias, onde o Sr. Jefferson é sócio fecha durante o verão, mas Greg Heffley é expulso, porque ele se queixou do mesmo os mais pequenos impasses. Em seguida, o passeio à praia que estava olhando para a frente a é cancelada porque os Heffleys não tem dinheiro suficiente para ir. eles recorrem para ir à piscina da cidade, que Greg não gosta por causa de seus chuveiros abertos que mostram grandes homens peludos. Mais tarde, Greg e Rowley decidem ter uma festa do pijama e assistir a um filme de terror retirado do quarto de Rodrick.', 
15.00, 'diasdecao.jpg',11,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('21-05-2000','TESTE.pdf','Diário de um Banana: A Verdade Nua e Crua','Jeff Kinney','9178-989-707-043-3','Greg Heffley sempre quis crescer logo. Mas será que ficar velho é tão legal assim? De repente, Greg começa a lidar com as pressões das festas de meninos e meninas, com o aumento das responsabilidades e também com as mudanças embaraçosas que acompanham o crescimento. E depois de uma briga com seu melhor amigo Rowley, parece que Greg vai ter que encarar sozinho "a verdade nua e crua"...',
 12.00, 'averdadenuaecrua.jpg',11,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2001','TESTE.pdf','Diário de um Banana: Casa dos Horrores','Jeff Kinney','978-14-4197-0368-3','Mais uma vez, Greg Heffley entrou numa fria. Melhor dizendo: numa gelada. O muro da escola foi pichado e ele é o principal suspeito. Mas Greg é inocente... ou quase isso. A polícia está atrás dele, mas uma nevasca inesperada impede os Heffley de sair de casa. Greg ganha tempo, mas sabe que, quando o gelo derreter, terá de encarar a dura realidade. Pensando bem, talvez seja muito melhor passar o resto da vida atrás das grades do que preso com a família dentro de casa durante todo o inverno.', 
19.31, 'casadoshorrores.jpg',11,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2002','TESTE.pdf','Diário de Um Banana: Segurando Vela','Jeff Kinney','978-15-4197-0584-7','O Dia dos Namorados está chegando e Greg Heffley continua sozinho. Mas um baile organizado pela escola pode mudar tudo. Ele precisa encontrar uma garota urgentemente. Para isso, conta com a ajuda de outro "solteirão", Rowley Jefferson, seu melhor amigo. A ideia de Greg é usar Rowley como isca para atrair as meninas, uma espécie de coadjuvante que auxilia o ator principal a brilhar. Será que esse plano vai dar certo? Ou será que a flecha do cupido vai tomar um rumo inesperado?', 
18.34, 'segurandovela.png',11,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2003','TESTE.pdf','Diário de Um Banana: Maré de Azar','Jeff Kinney','978-17-4197-1132-9','Greg Heffley está bolado. Seu melhor amigo, Rowley Jefferson, o abandonou, e encontrar novos amigos na escola acabou se revelando uma tarefa muito difícil. Para mudar de estratégia, Greg decide arriscar e tomar decisões de acordo com sua sorte. Será que um lance de dados pode mudar as coisas, ou a vida de Greg está destinada a ser apenas uma maré de azar?', 
20.90, 'maredeazar.jpg',11,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2004','TESTE.pdf','Diário de um Banana: Caindo na Estrada','Jeff Kinney','9784-141-971-189-3','Uma viagem de carro em família tem tudo para ser algo divertidíssimo... ou não, ainda mais se for a família do Greg Heffley. A jornada começa cheia de promessas, mas logo sofre reviravoltas dramáticas. Banheiros de posto de gasolina, gaivotas ensandecidas, malas perdidas, um porco faminto... Mas até a viagem mais desastrosa pode virar uma grande aventura – e desta os Heffley não vão se esquecer tão cedo.', 
21.60, 'caindonaestrada.png',11,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2005','TESTE.pdf','Diário de Um Banana: Bons Tempos','Jeff Kinney','978-989-707-3626-5','A vida era melhor antigamente. Pelo menos é o que dizem. Mas Greg Heffley, um garoto bastante acostumado ao conforto do mundo moderno, não concorda muito com isso. E uma decisão polêmica vai colocar o seu paraíso tecnológico em curto-curcuito: todos em sua cidade decidem dar um tempo dos aparelhos eletrônicos. Dentro e fora de casa, Greg terá que enfrentar o dia a dia a moda antiga. Será que ele vai conseguir sobreviver do mesmo jeitinho que se fazia nos bons e velhos tempos?', 
20.99, 'bonstempos.png',11,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2006','A Culpa é das Estrelas.pdf','A Culpa é das Estrelas','John Green','978-989-232-0945-6','Hazel é uma paciente terminal. Ainda que, por um milagre da medicina, seu tumor tenha encolhido bastante — o que lhe dá a promessa de viver mais alguns anos —, o último capítulo de sua história foi escrito no momento do diagnóstico. Mas em todo bom enredo há uma reviravolta, e a de Hazel se chama Augustus Waters, um garoto bonito que certo dia aparece no Grupo de Apoio a Crianças com Câncer. Juntos, os dois vão preencher o pequeno infinito das páginas em branco de suas vidas.', 
9.90, 'aculpaedasestrelas.jpg',6,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2007','Começando a Programar em Java Para Leigos.pdf','Começando a Programar em Java Para Leigos','Barry Burd','9784-857-608-832-5','Começando a Programar em Java Para Leigos usa uma poderosa linguagem de programação de computadores de uso geral. Mas as sutilezas do Java e as excentricidades não são foco principal do livro. Ao contrário, este livro enfatiza um processo – o processo de criar instruções para um computador seguir. Muitos livros pomposos descrevem a mecânica desse processo – as regras, convenções e formalismo.', 
54.09, 'javaparaleigos.jpg',5,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2008','Desenvolvimento de Aplicativos Android Para Leigos.pdf','Desenvolvimento de Aplicativos Android Para Leigos',' Michael Burton & Donn Felker','9748-857-608-832-5','Com Desenvolvimento de Aplicativos Android Para Leigos, a sua boa ideia de aplicativo Android se tornará realidade! Comece baixando o SDK e o JDK para criar coisas simples e então avance rumo aos aplicativos mais complexos e disponibilize-os na Google Play Store. Você também descobrirá como melhorar seus aplicativos e tornar suas interfaces ainda mais bonitas.', 
54.09, 'androidparaleigos.jpg',5,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2010','Eu, Robô.pdf','Eu, Robô','Isaac Asimov','978-857-6507-2400-8','Sensíveis, divertidos e instigantes, os contos de “Eu, robô” são um marco na história da ?cção-cientifica, seja pela introdução das célebres Leis da Robótica, pelos personagens inesquecíveis ou por seu olhar completamente novo a respeito das máquinas. Vivam eles na Terra ou no espaço sideral; sejam domésticos ou especializados, submissos ou rebeldes, meramente mecânicos ou humanizados, os robôs de Asimov conquistaram a cabeça e a alma de gerações de escritores, cineastas e cientistas, sendo até hoje fonte de inspiração de tudo o que lemos e assistimos sobre essas criaturas mecânicas.', 
23.50, 'eurobo.png',10,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2011','Neuromancer.pdf','Neuromancer','William Gibson','978-857-65047-049-3','O livro conta a história de Case, um ex-hacker (cowboy, como são chamados os hackers em Neuromancer) que foi impossibilitado de exercer sua profissão, graças a um erro que cometeu ao tentar roubar seus patrões. Eles então envenenaram Case com uma micotoxina, que danificou seu sistema neural e o impossibilitou de se conectar à Matrix. Antes deixaram uma quantia de dinheiro com ele, pois "iria precisar dele".
Case então procura as clínicas clandestinas de medicina de Chiba City, onde gasta todo seu dinheiro com exames, sem conseguir encontrar uma cura. Drogado, sem dinheiro, desempregado - é nessa condição que Molly o encontra e a trama se inicia, com uma cura para os danos de Case à vista.
Diversos personagens interessantes são introduzidos durante a trama (Molly, Armitage, Wintermute) e vai se descobrindo o passado obscuro de cada um deles no desenrolar da história, que possui um final surpreendente.', 
35.20, 'neuromancer.jpg',10,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2011','TESTE.pdf','As Crônicas de Gelo e Fogo: A Guerra dos Tronos','George R.R. Martin','978-8056-293-652-4','Em A guerra dos tronos, o primeiro livro da aclamada série As crônicas de gelo e fogo, George R. R. Martin - considerado o 
Tolkien americano - cria uma verdadeira obra de arte, trazendo o melhor que o gênero pode oferecer. Uma história de lordes e damas, soldados e mercenários, assassinos e bastardos, que se juntam em um tempo de presságios malignos. Cada um esforçando-se para ganhar este conflito mortal: a guerra dos tronos. Mistério, intriga, romance e aventura encherão as páginas deste livro, agora também um blockbuster da HBO!', 
30.60, 'guerradostronos.png',8,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2012','TESTE.pdf','As Crônicas de Gelo e Fogo: A Fúria dos Reis','George R.R. Martin','9708-858-044-027-0','Em A fúria dos Reis, o segundo livro da aclamada série As crônicas de gelo e fogo, George R. R. Martins segue a épica aventura nos Sete Reinos, onde muitos perigos e disputas ainda estão por vir. Além dos combates que se estendem por todos os lados, a ameaça agora também chega pelo céu, quando um cometa vermelho como sangue cruza o céu ameaçadoramente. Uma terra onde irmão luta contra irmão e a morte caminha na noite fria, nada é o que parece ser, e inocência é uma palavra que não existe. Quando os reis estão em guerra, a terra toda treme!', 
39.90, 'furiadosreis.jpg',8,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2013','TESTE.pdf','As Crônicas de Gelo e Fogo: Tormenta de Espadas','George R.R. Martin','978-858-0404-262-5','A tormenta de espadas, o terceiro livro da série de George R. R. Martin, onde os Sete Reinos já sentem o rigoroso inverno que chega, mas as batalhas parecem estar mais cruéis e impiedosas. Enquanto os Sete Reinos estremecem com a chegada dos temíveis selvagens pela Muralha, numa maré interminável de homens, gigantes e terríveis bestas, Jon Snow, o Bastardo de Winterfell, que se encontra entre eles, divide-se entre sua consciência e o papel que é forçado a desempenhar. Robb Stark, o Jovem Lobo, vence todas as suas batalhas, mas será que ele conseguirá vencer os desafios que não se resolvem apenas com a espada? Arya continua a caminho de Correrrio, mas mesmo alguém tão desembaraçado como ela terá grande dificuldade em ultrapassar os obstáculos que se aproximam. Na corte de Joffrey, em Porto Real, Tyrion luta pela vida, depois de ter sido gravemente ferido na Batalha da Água Negra; e Sansa, livre do compromisso com o homem que agora ocupa o Trono de Ferro, precisa lidar com as consequências de ser a segunda na linha de sucessão de Winterfell, uma vez que Bran e Rickon estariam mortos. No Leste, Daenerys Targaryen navega em direção às terras da sua infância, mas antes ela precisará aportar às desprezíveis cidades dos esclavagistas. Mas a menina indefesa agora é uma mulher poderosa. Quem sabe quanto tempo falta para se transformar em uma conquistadora impiedosa?', 
32.90, 'tormentadeespadas.jpg',8,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2014','TESTE.pdf','As Crônicas de Gelo e Fogo: O Festim dos Corvos','George R.R. Martin','978-82158-044-376-9','Continuando a saga mais ambiciosa e imaginativa desde “O Senhor dos Anéis”, “As Crônicas de Gelo” e “Fogo” prosseguem após o violento triunfo dos traidores. Enquanto os senhores do Norte lutam incessantemente uns contra os outros e os Homens de Ferro estão prestes a emergir como uma força implacável, a rainha regente Cersei tenta manter intacta a força dos leões em Porto Real. Os jovens lobos, sedentos por vingança, estão dispersos pela terra, cada um envolvido no perigoso jogo dos tronos. Arya abandonou Westeros rumo a Bravos, Bran desapareceu na vastidão enigmática para além da Muralha, Sansa está nas mãos do ambicioso e maquiavélico Mindinho, Jon Snow foi proclamado comandante da Muralha mas tem que enfrentar a vontade férrea do rei Stannis e, no meio de toda a intriga, começam a surgir histórias do outro lado do mar sobre dragões vivos e fogo... Quando Euron Greyjoy consegue ser escolhido como rei das Ilhas de Ferro não são só as ilhas que tremem. “O Olho de Corvo” tem o objetivo declarado de conquistar Westeros. E o seu povo parece acreditar nele. Mas será ele capaz? Em Porto Real, Cersei enreda-se cada vez mais nas teias da corte. Desprovida do apoio da família, e rodeada por um conselho que ela própria considera incapaz, é ainda confrontada com a presença ameaçadora de uma nova corrente militante da Fé. Como se desvencilhará de tal enredo?', 
39.90, 'festimdoscorvos.png',8,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2015','TESTE.pdf','As Crônicas de Gelo e Fogo: A Dança dos Dragões','George R.R. Martin','978-858-044-48211-0','O futuro dos Sete Reinos ainda é incerto - novas ameaças e novos inimigos surgem a cada momento. 
Além do Mar Estreito, Daenerys Targaryen, a última herdeira da Casa Targaryen, governa uma cidade construída sobre o pó e a morte. Mas seus inimigos são cada vez mais numerosos e farão de tudo para destruí-la. Enquanto isso, dois jovens embarcam em missões distintas, mas que podem mudar o destino da Mãe dos Dragões.
No Norte, Jon Snow - 998º Senhor Comandante da Patrulha da Noite - fará de tudo para garantir a segurança da Muralha. Para isso, não hesitará em transformar amigos em inimigos e vice-versa.
Traições, revelações e um fantasma do passado que volta para assombrar quando menos se espera - em todos os cantos de Westeros e de Essos, mercadores, fora da lei, meistres, reis, nobres, escravos, soldados e troca-peles estão prestes a encarar fatos inesperados. Alguns fracassarão, outros se aproveitarão das forças sombrias que crescem cada vez mais. Mas, neste momento de inquietude crescente, as marés da política e do destino levarão inevitavelmente à maior dança de todas.', 
39.90, 'dancadosdragoes.png',8,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2016','TESTE.pdf','A Biografia Intima de Leopoldina','Marsilio Cassotti','978-854-22120-496-4','', 
25.00, 'biografialeopoldina.png',9,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('27-02-1984','D.Pedro - A História Não Contada.pdf','D.Pedro - A História Não Contada','Paulo Rezzutti','978-857-734-51283-0','', 
44.90, 'dompedroahistorianaocontada.png',9,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('09-10-2015','Shazam! - Com Uma Palavra Mágica.pdf','Shazam! - Com Uma Palavra Mágica...','Geoff Jhons & Gary Frank','978-858-368-118-2','SHAZAM! Com essas duas simples sílabas, um jovem perdido, mas de coração puro, se transforma no Mortal Mais Poderoso da Terra e usa seus tremendos poderes para salvar os inocentes. Essa é a história que você conhece… e essa é a história que vai ser revolucionada agora! Pelas mãos de dois criadores consagrados – GEOFF JOHNS e GARY FRANK – um dos maiores ícones das histórias em quadrinhos volta ao Universo DC em uma nova roupagem, mas ainda carregando todas as características que o transformaram em uma lendária figura da cultura mundial. E, junto com ele, toda a sua carismática galeria de aliados e terríveis inimigos!', 
21.50, 'shazam.jpg',7,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('08-10-2012','Guerra Civil.pdf','Guerra Civil','Mark Millar & Steve McNiven','978-857-351-690-6','A ação precipitada de um grupo de jovens super-heróis acarreta uma tragédia sem precedentes, deixando um saldo de centenas de mortos. Diante da pressão popular, o governo sanciona uma lei determinando que todos os superseres sejam registrados. A iniciativa divide a comunidade heroica como nunca antes. De um lado, a facção pró-registro, liderada pelo Homem de Ferro; do outro, os contrários à medida, tendo à frente o Capitão América. E você, de que lado está? A minissérie de maior sucesso de 2007 finalmente numa luxuosa edição encadernada indispensável para qualquer colecionador.', 
43.20, 'guerracivil.jpg',7,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('18-08-2004','O Cirurgião.pdf','O Cirurgião','Tess Gerritsen','978-850-106-976-4','Tess Gerritsen, neste livro assustador, narra o rastro de sangue deixado por um assassino cruel. O agressor entra na casa de suas vítimas na calada da noite e segue até o quarto delas. Mergulhadas em sono profundo, as mulheres ignoram que irão acordar para um terrível pesadelo... A precisão com que ele investe contra as mulheres , somada à crueldade de agressão - útero das vítimas é arrancado -, sugere que o responsável pelas atrocidades seja um médico psicopata. Os jornais de Boston passam então a chamá-lo de O Cirurgião. Em um livro de tirar o fôlego e com descrições minuciosas, a autora nos apresenta a um rico universo de personagens, ao criar um romance de suspense e profundidade inéditos.', 
57.90, 'ocirurgiao.jpg',2,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2005','O Código da Vinci.pdf','O Código da Vinci','Dan Brown','978-857-542-113-0','Um assassinato dentro do Museu do Louvre, em Paris, traz à tona uma sinistra conspiração para revelar um segredo que foi protegido por uma sociedade secreta desde os tempos de Jesus Cristo. A vítima é o respeitado curador do museu, Jacques Saunière, um dos líderes dessa antiga fraternidade, o Priorado de Sião, que já teve como membros Leonardo da Vinci, Victor Hugo e Isaac Newton. Momentos antes de morrer, Saunière consegue deixar uma mensagem cifrada na cena do crime que apenas sua neta, a criptógrafa francesa Sophie Neveu, e Robert Langdon, um famoso simbologista de Harvard, podem desvendar. Os dois transformam-se em suspeitos e em detetives enquanto percorrem as ruas de Paris e de Londres tentando decifrar um intricado quebra-cabeças que pode lhes revelar um segredo milenar que envolve a Igreja Católica.
Apenas alguns passos à frente das autoridades e do perigoso assassino, Sophie e Robert vão à procura de pistas ocultas nas obras de Da Vinci e se debruçam sobre alguns dos maiores mistérios da cultura ocidental - da natureza do sorriso da Mona Lisa ao significado do Santo Graal. Mesclando com perfeição os ingredientes de uma envolvente história de suspense com informações sobre obras de arte, documentos e rituais secretos, Dan Brown consagrou-se como um dos autores mais brilhantes da atualidade. O Código Da Vinci prende o leitor da primeira à última página.', 
33.90, 'codigodavinci.jpg',2,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2007','O Iluminado.pdf','O Iluminado','Stephen King','978-858-105-048-5','Jack Torrence consegue um emprego de zelador em um velho hotel, e acha que será a solução dos problemas de sua família: não vão mais passar por dificuldades, sua esposa não vai mais sofrer e seu filho, Danny, vai poder ter ar puro para se livrar de estranhas convulsões. Mas as coisas não são tão perfeitas como parecem: existem forças malignas rondando os antigos corredores. O hotel é uma chaga aberta de ressentimento e desejo de vingança, e, inevitavelmente, um embate entre o bem e o mal terá de ser travado.', 
39.90, 'oiluminado.jpg',1,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2008','E Não Sobrou Nenhum - 4ª Ed. 2014.pdf','E Não Sobrou Nenhum - 4ª Ed. 2014','Agatha Christie','978-852-505-701-3','Lançado em 1939 E não sobrou nenhum quebrou as regras vigentes até então para o gênero policial e investigativo, porque em sua narrativa nenhum detetive soluciona o mistério e o criminoso escapa das garras da lei. A obra também foi adaptada para o cinema pelo diretor René Clair, em 1945, com o título O Vingador Invisível. Aclamado pelo público trata-se de uma aula de como elaborar um romance do gênero: apegado ao real, sem excessos, com personagens consistentes e fluidez. É sem dúvida um romance basilar do gênero.', 
31.90, 'enaosobrounenhum.jpg',3,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2009','Assassinato No Expresso Oriente.pdf','Assassinato No Expresso Oriente','Agatha Christie','978-852-543-009-0','É perto da meia-noite quando a neve acumulada sobre os trilhos interrompe a jornada do Expresso Oriente, o mais famoso e luxuoso trem de passageiros do mundo, que liga a Ásia à Europa. A bordo, milionários, aristocratas, empregados – e um assassino. Porém, no mesmo vagão encontra-se ninguém menos que Hercule Poirot. Caberá ao meticuloso detetive investigar todos os passageiros e descobrir a identidade do ousado criminoso. Christie propõe um fascinante enredo nos moldes do clássico subgênero do “locked room” (“mistério do quarto fechado”), em que o crime ocorre num local isolado, e a suspeita recai sobre todos os presentes. Publicado em 1934, o romance foi levado com estrondoso sucesso ao cinema pelo diretor Sidney Lumet em 1974, com Albert Finney, Lauren Bacall, Sean Connery, Jacqueline Bisset e Ingrid Bergman no elenco – até hoje uma das mais aclamadas adaptações jamais feitas de um clássico da literatura de mistério.', 
14.90, 'assassinatonoexpressooriente.jpg',3,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2010','Aconteceu Em Paris.pdf','Aconteceu Em Paris','Molly Hopkins','978-858-163-259-9','Evie Dexter quer fazer carreira como guia de turismo. Determinada como é, e cheia de coragem por causa de um ou outro drink, ela logo começa a “melhorar” seu currículo. E consegue um ótimo emprego: acompanhar turistas por toda Paris. 
Agora é só uma questão de se firmar como profissional demonstrando o seu melhor. Mas os vinhos franceses são tão gostosos... E seu tutor, Rob, é bonito demais!
O primeiro romance de Molly Hopkins é um livro que todo mundo gostaria de ler. É verdade que você pode se incomodar com o comportamento de Evie quando ela descobre que Rob é muito rico, e pode até ser que você ache que Rob é exageradamente controlador. Mas nada é maior que as gargalhadas que você dará quanto mais conhecer a garota descomedida, apaixonada e com um imenso coração que é Evie. Uma moça como muitas que conhecemos.', 
35.90, 'aconteceuemparis.jpg',4,'ativo');
insert into produto (lancamento,pdf,titulo,autor,isbn,sinopse, preco,imagem, id_genero,status) values ('01-01-2011','Subindo Pelas Paredes - 2ª Ed. 2014.pdf','Subindo Pelas Paredes - 2ª Ed. 2014','Alice Clayton','978-858-240-147-7','A primeira noite de Caroline em seu novo apartamento é promessa de que dias – e noites – agitada virão. Ela não poderia imaginar que dividira a fina parede de seu quarto com um cara capaz de deixar uma mulher completamente maluca na cama. Aliás, uma não Caroline já contou pelo menos três gritos e gemidos diferentes.
Conviver toda madrugada com a animação do apartamento ao lado deixa Caroline ainda mais afundada na crise sexual que a acompanha há tempos. Mas ela nem sequer pode imaginar que o vizinho que ela abomina pode ser o único capaz de lhe trazer de volta seus orgasmos.
Em “Subindo pelas paredes”, Alice Clayton mistura humor paixão e boas doses de sensualidade, capazes de fazer qualquer uma cair de joelhos e se apaixonar.', 
17.90, 'subindopelasparedes.jpg',4,'ativo');


create table pedido(
id_pedido serial primary key,
id_usuario int,
status varchar,
de date,
ate date,
valor_total real,
codigo_boleto varchar,
constraint fk_pedido_usuario Foreign Key(id_usuario)
references usuario (id_usuario)
);

create table importacaoBoleto(
id_boleto serial primary key,
codigo varchar,
status varchar
);


create table itemDeCompra(
id_itens serial primary key,
id_livro int,
id_pedido int,
valor_unitario real,
constraint fk_itens_produto Foreign Key(id_livro)
references produto(id_livro),
constraint fk_itens_pedido Foreign Key(id_pedido)
references pedido(id_pedido)
);


create table estante_virtual(
id_estante serial primary key,
id_livro int,
id_usuario int,
constraint fk_estante_produto Foreign Key(id_livro)
references produto(id_livro),
constraint fk_estante_usuario Foreign Key(id_usuario)
references usuario(id_usuario)
);


create table avaliacao(
id_avaliacao serial primary key,
id_livro int,
id_usuario int,
comentario varchar,
nota int,
data_comentario date,
constraint fk_avaliacao_produto Foreign Key(id_livro)
references produto(id_livro),
constraint fk_avaliacao_usuario Foreign Key(id_usuario)
references usuario(id_usuario)
);



