--FUNCIONALIDADES
insert into funcionalidades values (1, 'USUARIO'), (2, 'TURMA'), (3, 'AULA');

--PERMISSOES DE ATENDIMENTO
insert into permissoes values (1, 'Cadastrar Usuario', 'USUARIO_INSERIR', 1), (2, 'Listar Usuario', 'USUARIO_LISTAR', 1), (3, 'Visualizar Usuario', 'USUARIO_VISUALIZAR', 1), 
(4, 'Atualizar Usuario', 'USUARIO_ATUALIZAR', 1), (15, 'Inativar Usuario','USUARIO_INATIVAR', 1);
insert into permissoes values (5, 'Atualizar Turma', 'TURMA_ATUALIZAR', 2), (6, 'Listar Turma', 'TURMA_LISTAR', 2), (7, 'Cadastrar Turma', 'TURMA_INSERIR', 2), (8, 'Visualizar Turma', 'TURMA_VISUALIZAR', 2),
(16, 'Inativar Turma', 'TURMA_INATIVAR', 2);
insert into permissoes values (9, 'Atualizar Aula', 'AULA_ATUALIZAR', 3), (10, 'Listar Aula', 'AULA_LISTAR', 3), (11, 'Cadastrar Aula', 'AULA_INSERIR', 3), (12, 'Avaliar Aula', 'AULA_AVALIAR', 3),
(13, 'Moderar Aula', 'AULA_MODERAR', 3), (14, 'Visualizar Aula', 'AULA_VISUALIZAR', 3), (17, 'Inativar Aula', 'AULA_INATIVAR', 3);
	
--PAPEIS
insert into papeis values (1, 0);--Aluno
insert into papeis values (2, 1);--Professor
insert into papeis values (3, 2);--Administrador

--CONFIGURACOES DE ATENDENTE
--Alunos & Usuario
insert into configuracoes values (1, 1), (1, 3), (1, 4), (1, 15);
--Alunos & Turma
insert into configuracoes values (1, 6), (1, 8);
--Alunos & Aula
insert into configuracoes values (1, 10), (1, 12), (1, 14);
--Professor & Usuario
insert into configuracoes values (2, 1), (2, 2), (2, 3), (2, 4), (2, 15);
--Professor & Turma
insert into configuracoes values (2, 5), (2, 6), (2, 7), (2, 8), (2, 16);
--Professor & Aula
insert into configuracoes values (2, 9), (2, 10), (2, 11), (2, 12), (2, 13), (2, 14), (2, 17);


--USUARIO ADM
insert into usuarios values (nextval('hibernate_sequence'), true, 'admin', 'Administrador', false, '3C9909AFEC25354D551DAE21590BB26E38D53F2173B8D3DC3EEE4C047E7AB1C1EB8B85103E3BE7BA613B31BB5C9C36214DC9F14A42FD7A2FDB84856BCA5C44C2');

--PERFIS DE ADM
insert into perfis values (1, 1), (1,2), (1, 3);