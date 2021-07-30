--Dados para inserir no banco de dados para conseguir rodar os testes com junit
delete from public.festa where id = 500;
insert into public.festa values(500,false,'convidadoTeste',2);
