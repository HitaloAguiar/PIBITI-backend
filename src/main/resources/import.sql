-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO dimensao_maturidade_nit (nome, peso) VALUES ('Estrutura', 0.1116);
INSERT INTO dimensao_maturidade_nit (nome, peso) VALUES ('Relacionamento', 0.1159);
INSERT INTO dimensao_maturidade_nit (nome, peso) VALUES ('Visão', 0.1186);
INSERT INTO dimensao_maturidade_nit (nome, peso) VALUES ('Processos', 0.1381);
INSERT INTO dimensao_maturidade_nit (nome, peso) VALUES ('Recursos Financeiros', 0.1233);
INSERT INTO dimensao_maturidade_nit (nome, peso) VALUES ('Ecossistema', 0.1247);
INSERT INTO dimensao_maturidade_nit (nome, peso) VALUES ('Estratégia', 0.1292);
INSERT INTO dimensao_maturidade_nit (nome, peso) VALUES ('Pessoas', 0.1387);

-- Variáveis da Dimensão Estrutura
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter profissionais em TT trabalhando em tempo integral', 0.1477, 1);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Estar vinculado a uma universidade que possui um grande número de cursos', 0.1171, 1);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Possuir infraestrutura própria', 0.1566, 1);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter sua atividade principal focada na TT', 0.1528, 1);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Possuir uma estrutura organizacional, com uma clara divisão de responsabilidades entre os funcionários', 0.1272, 1);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Possuir um Website próprio para exposição do seu portfólio de atividades relacionadas à TT', 0.1452, 1);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Possuir uma ferramenta de TIC adequada e integrada', 0.1534, 1);

-- Variáveis da Dimensão Relacionamento
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Promover e gerenciar parcerias de pesquisa com o setor privado', 0.1376, 2);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Manter programas de interação entre universidade indústria', 0.1531, 2);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter uma relação estreita com os departamentos de cursos', 0.1145, 2);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter a confiança dos pesquisadores da universidade para promover suas inovações', 0.1534, 2);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter diretores com um bom network com a comunidade empresarial', 0.1558, 2);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter gestores com um bom relacionamento pessoal com empresários locais', 0.1393, 2);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter uma relação de sucesso com agências de fomento à inovação e TT', 0.1462, 2);

-- Variáveis da Dimensão Visão
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Focar mais na exploração comercial da tecnologia do que na proteção da PI', 0.1467, 3);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ser um facilitador de TT para professores/pesquisadores', 0.1627, 3);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Priorizar negociações com empresas parceiras', 0.1148, 3);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter objetivos claros para geração própria de receita', 0.1437, 3);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Focar não só no desenvolvimento local, mas também regional', 0.1469, 3);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ser um facilitador de TT para gerentes e pesquisadores das empresas parceiras', 0.1464, 3);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter como objetivo influenciar positivamente no prestígio da universidade', 0.1388, 3);

-- Variáveis da Dimensão Processos
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Adotar procedimentos de boas práticas na TT para indústria', 0.1471, 4);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter a participação ativa de docentes/pesquisadores', 0.1438, 4);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter um processo formal de revisão de projetos de pesquisas', 0.1476, 4);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter um processo para verificação do recebimento dos Royalties', 0.1326, 4);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter a responsabilidade pela distribuição dos rendimentos e garantir a conformidade dos contratos', 0.1389, 4);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Organizar eventos de networking para facilitar a interação entre pesquisadores e a comunidade empresarial', 0.1408, 4);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter um programa formal de divulgação das suas atividades', 0.1493, 4);

-- Variáveis da Dimensão Recursos Financeiros
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter colaboração e parceria com departamentos de P&D de empresas privadas', 0.1519, 5);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter parcerias com agências de fomento', 0.1327, 5);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter um orçamento próprio para P&D', 0.1583, 5);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter recursos financeiros alocados para o empreendedorismo acadêmico', 0.1501, 5);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter orçamento suficiente para garantir as suas atividades de TT', 0.1469, 5);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Receber recursos extras alocados pela universidade com foco nas atividades de TT', 0.1359, 5);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Receber a participação de fundos de capital de risco para Start-Ups', 0.1241, 5);

-- Variáveis da Dimensão Ecossistema
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ser o vínculo de pesquisa e network com empreendedores acadêmicos e corporações', 0.15, 6);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ser o estímulo ao desenvolvimento de uma cultura de empreendedorismo acadêmico dentro da universidade', 0.1484, 6);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Estar vinculado a uma Universidade localizada em uma região com nível alto de P&D em relação ao PIB', 0.1455, 6);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('O NIT está localizado em uma região com uma expressiva demanda por tecnologia', 0.1473, 6);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Gerenciar e apoiar programas de empreendedorismo acadêmico', 0.1349, 6);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Estar vinculado a uma Universidade com foco nas engenharias e ciências biológicas (medicina, farmacologia, odontologia, etc.)', 0.1092, 6);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Estar vinculado a um parque tecnológico', 0.1647, 6);

-- Variáveis da Dimensão Estratégia
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter regulamentos internos sobre a participação dos pesquisadores na TT', 0.1546, 7);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter políticas de licenciamento de tecnologia como parte de seu plano estratégico', 0.1617, 7);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter autonomia para TT (Desburocratização de processos)', 0.1494, 7);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter gestores cujas tarefas ligadas à TT não colidem com outras atividades profissionais', 0.1023, 7);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter uma política bem definida de recompensa financeira', 0.1369, 7);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter marketing interno, disseminando casos de sucesso', 0.1472, 7);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter mecanismos de aproximação com a comunidade empresarial (Programas de aconselhamento empresarial, painéis, debates e palestras à sociedade)', 0.1479, 7);

-- Variáveis da Dimensão Pessoas
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter gestores com experiência e habilidades administrativas, técnicas, comunicação e marketing', 0.1543, 8);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter funcionários com experiência em negociação e Know-how em TT', 0.1618, 8);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter diretor(es) com experiência (5 anos) em gestão de empresas, além de formação acadêmica', 0.1108, 8);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter diretor(es) com um elevado nível de autoridade e apoio da direção da universidade', 0.146, 8);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Estar vinculado a uma universidade onde a pesquisa é uma prerrogativa do quadro docente', 0.1577, 8);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Ter diretor(es) com doutorado preferencialmente em engenharias, ciências biológicas e da saúde, ciências sociais aplicadas, ciências agrárias, entre outros', 0.1037, 8);
INSERT INTO variavel_maturidade_nit (nome, peso, id_dimensao) VALUES ('Estar vinculado a uma universidade onde as chefias conhecem e valorizam a importância do processo de TT', 0.1657, 8);



INSERT INTO servico_fornecido (nome) VALUES ('Avalia a patenteabilidade de invenções');
INSERT INTO servico_fornecido (nome) VALUES ('Administra os processos de patentes');
INSERT INTO servico_fornecido (nome) VALUES ('Negocia ou administra processos de licenciamento');


-- Insert de Nit apenas para teste
INSERT INTO nit (cnpj, email, telefone, ano_inicio_atividades, ict, privacidade, senha) VALUES ('45.738.018/8453-29', 'teste_email_nit@gmail.com', '(82) 9860-0234', 2025, 'UNTINS', false, 'x6JkviFo/CZc/dYoTsn+KjkyXu9rqbOwZ89vC1horO3B+ZT2N9nhquEvkFxm2WZahBpo5wgui91vSF00c1BYPA==');
INSERT INTO responsavel (nome_completo, cpf, email, telefone, cargo, id_nit) VALUES ('John Dev', '123.456.789-10', 'testeemail@gmail.com', '(84) 93485-2349', 'Diretor', 1);

/* 

{
  "idNit": 1,
  "variaveis": [
    {
      "idVariavel": 1,
      "selecionado": 1
    },
    {
      "idVariavel": 2,
      "selecionado": 1
    },
    {
      "idVariavel": 3,
      "selecionado": 1
    },
    {
      "idVariavel": 4,
      "selecionado": 1
    },
    {
      "idVariavel": 5,
      "selecionado": 0
    },
    {
      "idVariavel": 6,
      "selecionado": 1
    },
    {
      "idVariavel": 7,
      "selecionado": 0
    },
    {
      "idVariavel": 8,
      "selecionado": 1
    },
    {
      "idVariavel": 9,
      "selecionado": 1
    },
    {
      "idVariavel": 10,
      "selecionado": 0
    },
    {
      "idVariavel": 11,
      "selecionado": 0
    },
    {
      "idVariavel": 12,
      "selecionado": 1
    },
    {
      "idVariavel": 13,
      "selecionado": 0
    },
    {
      "idVariavel": 14,
      "selecionado": 0
    },
    {
      "idVariavel": 15,
      "selecionado": 0
    },
    {
      "idVariavel": 16,
      "selecionado": 1
    },
    {
      "idVariavel": 17,
      "selecionado": 1
    },
    {
      "idVariavel": 18,
      "selecionado": 1
    },
    {
      "idVariavel": 19,
      "selecionado": 0
    },
    {
      "idVariavel": 20,
      "selecionado": 0
    },
    {
      "idVariavel": 21,
      "selecionado": 1
    },
    {
      "idVariavel": 22,
      "selecionado": 1
    },
    {
      "idVariavel": 23,
      "selecionado": 0
    },
    {
      "idVariavel": 24,
      "selecionado": 1
    },
    {
      "idVariavel": 25,
      "selecionado": 1
    },
    {
      "idVariavel": 26,
      "selecionado": 0
    },
    {
      "idVariavel": 27,
      "selecionado": 1
    },
    {
      "idVariavel": 28,
      "selecionado": 0
    },
    {
      "idVariavel": 29,
      "selecionado": 0
    },
    {
      "idVariavel": 30,
      "selecionado": 1
    },
    {
      "idVariavel": 31,
      "selecionado": 0
    },
    {
      "idVariavel": 32,
      "selecionado": 0
    },
    {
      "idVariavel": 33,
      "selecionado": 0
    },
    {
      "idVariavel": 34,
      "selecionado": 1
    },
    {
      "idVariavel": 35,
      "selecionado": 0
    },
    {
      "idVariavel": 36,
      "selecionado": 0
    },
    {
      "idVariavel": 37,
      "selecionado": 0
    },
    {
      "idVariavel": 38,
      "selecionado": 1
    },
    {
      "idVariavel": 39,
      "selecionado": 0
    },
    {
      "idVariavel": 40,
      "selecionado": 0
    },
    {
      "idVariavel": 41,
      "selecionado": 1
    },
    {
      "idVariavel": 42,
      "selecionado": 1
    },
    {
      "idVariavel": 43,
      "selecionado": 1
    },
    {
      "idVariavel": 44,
      "selecionado": 1
    },
    {
      "idVariavel": 45,
      "selecionado": 1
    },
    {
      "idVariavel": 46,
      "selecionado": 0
    },
    {
      "idVariavel": 47,
      "selecionado": 0
    },
    {
      "idVariavel": 48,
      "selecionado": 1
    },
    {
      "idVariavel": 49,
      "selecionado": 1
    },
    {
      "idVariavel": 50,
      "selecionado": 1
    },
    {
      "idVariavel": 51,
      "selecionado": 0
    },
    {
      "idVariavel": 52,
      "selecionado": 1
    },
    {
      "idVariavel": 53,
      "selecionado": 0
    },
    {
      "idVariavel": 54,
      "selecionado": 1
    },
    {
      "idVariavel": 55,
      "selecionado": 1
    },
    {
      "idVariavel": 56,
      "selecionado": 1
    }
  ]
}

*/
